package com.yetanalytics.xapi.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.testcontainers.containers.GenericContainer;


import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.yetanalytics.xapi.client.filters.StatementFilters;
import com.yetanalytics.xapi.model.Agent;
import com.yetanalytics.xapi.model.Statement;
import com.yetanalytics.xapi.util.Mapper;
import com.yetanalytics.xapi.util.TestFileUtils;

public class StatementClientTest {

    private static final String HOST = "http://localhost:8333/xapi";
    private static final String KEY = "username";
    private static final String SECRET = "password";

    private static Map<String, String> getContainerEnv(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("LRSQL_API_KEY_DEFAULT", KEY);
        map.put("LRSQL_API_SECRET_DEFAULT", SECRET);
        map.put("LRSQL_ADMIN_USER_DEFAULT", "my_username");
        map.put("LRSQL_ADMIN_PASS_DEFAULT", "my_password");
        map.put("LRSQL_ALLOW_ALL_ORIGINS", "true");
        map.put("LRSQL_HTTP_PORT", "8333");
        return map;
    }

    @ClassRule
    @SuppressWarnings("resource")
    public static GenericContainer simpleLRS
        = new GenericContainer("yetanalytics/lrsql:latest")
            .withEnv(getContainerEnv())
            .withExposedPorts(8333);

    @Test
    @EnabledIfSystemProperty(named = "lrs.integration.tests", matches = "true")
    public void testSinglePostAndGet() 
            throws StreamReadException, DatabindException, IOException {
        UUID testId = UUID.randomUUID();
        File testFile = TestFileUtils.getJsonTestFile("basic");
        Statement stmt = Mapper.getMapper().readValue(testFile, Statement.class);
        stmt.setId(testId);
        
        LRS lrs = new LRS(HOST, KEY, SECRET, null);
        StatementClient client = new StatementClient(lrs);
        List<UUID> ids = client.postStatement(stmt);
        assertEquals(ids.get(0), testId);

        //GET
        StatementFilters filter = new StatementFilters();
        filter.setStatementId(testId);
        List<Statement> result = client.getStatements(filter);
        assertTrue(result != null);
        assertEquals(result.size(), 1);
        Agent agent = (Agent) result.get(0).getActor();
        assertEquals(agent.getAccount().getName(), "23897525");
    }

    @Test
    @EnabledIfSystemProperty(named = "lrs.integration.tests", matches = "true")
    public void testBatchPost() throws StreamReadException, DatabindException, IOException {
        
        UUID testId1 = UUID.randomUUID();
        File testFile1 = TestFileUtils.getJsonTestFile("basic");
        Statement stmt1 = Mapper.getMapper().readValue(testFile1, Statement.class);
        stmt1.setId(testId1);

        UUID testId2 = UUID.randomUUID();
        File testFile2 = TestFileUtils.getJsonTestFile("context");
        Statement stmt2 = Mapper.getMapper().readValue(testFile2, Statement.class);
        stmt2.setId(testId2);

        List<Statement> stmts = new ArrayList<>(List.of(stmt1, stmt2));
        
        LRS lrs = new LRS(HOST, KEY, SECRET, null);
        StatementClient client = new StatementClient(lrs);
        List<UUID> ids = client.postStatements(stmts);
        assertEquals(2, ids.size());
        assertEquals(ids.get(0), testId1);
        assertEquals(ids.get(1), testId2);

        //GET
        List<Statement> result = client.getStatements(null);
        assertTrue(result != null);
        assertTrue(result.size() >= 2);
    }

    @Test
    @EnabledIfSystemProperty(named = "lrs.integration.tests", matches = "true")
    public void testLargeBatchPost() throws StreamReadException, DatabindException, IOException {
        File testFile = TestFileUtils.getJsonTestFile("context");
        List<UUID> ids = new ArrayList<UUID>();
        List<Statement> stmts = new ArrayList<Statement>();
        UUID sessionId = UUID.randomUUID();
        for(int i = 0; i < 200; i++) {
            UUID testId = UUID.randomUUID();
            Statement stmt = Mapper.getMapper().readValue(testFile, Statement.class);
            stmt.setId(testId);
            stmt.getContext().setRegistration(sessionId);
            stmts.add(stmt);
            ids.add(testId);
        }
        
        LRS lrs = new LRS(HOST, KEY, SECRET, null);
        StatementClient client = new StatementClient(lrs);
        List<UUID> resultIds = client.postStatements(stmts);
        assertEquals(ids, resultIds);

        //GET
        StatementFilters filter = new StatementFilters();
        filter.setRegistration(sessionId);
        List<Statement> result = client.getStatements(filter);
        assertTrue(result != null);
        assertEquals(result.size(), 200);
        assertEquals(result.get(0).getContext().getRegistration(), sessionId);
    }
}
