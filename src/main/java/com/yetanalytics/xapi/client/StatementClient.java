package com.yetanalytics.xapi.client;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.yetanalytics.xapi.client.filters.StatementFilters;
import com.yetanalytics.xapi.model.Statement;
import com.yetanalytics.xapi.model.StatementResult;
import com.yetanalytics.xapi.util.Mapper;

public class StatementClient {

    private static final String STATEMENT_ENDPOINT = "statements";

    private LRS lrs;
    private CloseableHttpClient client;

    public StatementClient (LRS lrs) {
        this.lrs = lrs;
  
        String encodedCreds = Base64.getEncoder().encodeToString(
            String.format("%s:%s", lrs.getKey(), lrs.getSecret()).getBytes());
        
        //TODO: Version headers
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("X-Experience-API-Version","1.0.3"));
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
        headers.add(new BasicHeader(HttpHeaders.ACCEPT, "application/json"));
        headers.add(new BasicHeader("Authorization",
            String.format("Basic %s", encodedCreds)));

        this.client = HttpClients.custom()
            .setDefaultHeaders(headers)
            .build();
    }

    private List<UUID> doPost(List<Statement> statements, URI endpoint) 
            throws ParseException, IOException {
        HttpPost post = new HttpPost(endpoint);
        post.setEntity(new StringEntity(
            Mapper.getMapper().writeValueAsString(statements)));

        CloseableHttpResponse response = client.execute(post);
        
        if (response.getStatusLine().getStatusCode() == 200) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return Mapper.getMapper().readValue(
                responseBody, 
                new TypeReference<List<UUID>>(){});
        } else {
            //TODO: custom and more codes
            throw new RuntimeException("Non-200 Status");
        }
    }

    public List<UUID> postStatement(Statement stmt) {
        return postStatements(new ArrayList<>(List.of(stmt)));
    }

    public List<UUID> postStatements(List<Statement> stmts) {
        try {
            List<UUID> result = new ArrayList<UUID>();
            for (List<Statement> p : Lists.partition(stmts, lrs.getBatchSize())) {
                result.addAll(doPost(p, lrs.getHost().resolve(STATEMENT_ENDPOINT)));
            }
            return result;
        } catch (ParseException | IOException e) {
            throw new RuntimeException("Error posting Statements", e);
        }
    }



    private StatementResult doGetStatementResult(URI endpoint) 
            throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(endpoint);
        CloseableHttpResponse response = client.execute(get);

        if (response.getStatusLine().getStatusCode() == 200) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return Mapper.getMapper().readValue(responseBody, StatementResult.class);
        } else {
            //TODO: custom and more codes
            throw new RuntimeException("Non-200 Status");
        }
    }

    private Statement doGetStatement(URI endpoint) 
            throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(endpoint);
        CloseableHttpResponse response = client.execute(get);

        if (response.getStatusLine().getStatusCode() == 200) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return Mapper.getMapper().readValue(responseBody, Statement.class);
        } else {
            //TODO: custom and more codes
            throw new RuntimeException("Non-200 Status");
        }
    }

    private URI resolveMore(URI moreLink) {
        if (moreLink == null || moreLink.toString().equals("")) 
            return null;
        URI uri = lrs.getHost().resolve(STATEMENT_ENDPOINT);
        return uri.resolve(moreLink.toString());
    }

    public List<Statement> getStatements(StatementFilters filters) {
        List<Statement> statements = new ArrayList<Statement>();

        URI target = lrs.getHost().resolve(STATEMENT_ENDPOINT);
        if (filters != null) {
            target = filters.addQueryToUri(target);
        }

        try {
            while(target != null) {
                if (filters != null && 
                        (filters.getStatementId() != null 
                        || filters.getVoidedStatementId() != null)) {
                    statements.add(doGetStatement(target));
                    target = null;
                } else {
                    StatementResult result = doGetStatementResult(target);
                    statements.addAll(result.getStatements());
                    target = resolveMore(result.getMore());
                }
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Error getting Statements", e);
        }
        return statements;
    }

    public List<Statement> getStatements() {
        return getStatements(null);
    }
    
}
