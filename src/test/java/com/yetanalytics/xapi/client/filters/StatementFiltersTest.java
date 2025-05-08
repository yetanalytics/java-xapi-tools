package com.yetanalytics.xapi.client.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yetanalytics.xapi.model.Agent;


public class StatementFiltersTest {

    private static final String BASE_URI = "http://localhost:8080/xapi/statements";

    @Test    
    public void testStatementFiltersBuilder() throws JsonProcessingException{
        UUID reg = UUID.fromString("23a0652e-9365-4c14-b9bd-4d83fbb701e5");
        UUID statementId = UUID.fromString("23a0652e-9365-4c14-b9bd-4d83fbb701e6");
        UUID voidedStatementId = UUID.fromString("23a0652e-9365-4c14-b9bd-4d83fbb701e7");

        StatementFilters filters = new StatementFilters();
        Agent agent = new Agent();
        agent.setMbox(URI.create("mailto:test@yetanalytics.com"));
        filters.setAgent(agent);
        filters.setVerb(URI.create("https://yetanalytics.com/verbs/test"));
        filters.setActivity(URI.create("https://yetanalytics.com/activities/test"));
        filters.setRegistration(reg);
        filters.setStatementId(statementId);
        filters.setVoidedStatementId(voidedStatementId);
        filters.setRelatedActivities(true);
        filters.setRelatedAgents(true);
        filters.setSince(ZonedDateTime.parse("2025-05-07T00:00:00Z"));
        filters.setUntil(ZonedDateTime.parse("2025-05-07T23:59:59Z"));
        filters.setLimit(1000);
        filters.setFormat(StatementFormat.CANONICAL);
        filters.setAscending(true);

        URI uri = URI.create(BASE_URI);
        uri = filters.addQueryToUri(uri);

        String expected = BASE_URI
            + "?verb=https%3A%2F%2Fyetanalytics.com%2Fverbs%2Ftest"
            + "&agent=%7B%22mbox%22%3A%22mailto%3Atest%40yetanalytics.com%22%7D"
            + "&activity=https%3A%2F%2Fyetanalytics.com%2Factivities%2Ftest"
            + "&statementId=23a0652e-9365-4c14-b9bd-4d83fbb701e6"
            + "&voidedStatementId=23a0652e-9365-4c14-b9bd-4d83fbb701e7"
            + "&registration=23a0652e-9365-4c14-b9bd-4d83fbb701e5"
            + "&related_activities=true&related_agents=true"
            + "&since=2025-05-07T00%3A00Z"
            + "&until=2025-05-07T23%3A59%3A59Z"
            + "&limit=1000&format=canonical&ascending=true";
        
        assertEquals(uri.toString(), expected);
    }

    @Test
    public void testStatementNoFiltersBuilder() throws JsonProcessingException{
        StatementFilters filters = new StatementFilters();

        URI uri = URI.create(BASE_URI);
        uri = filters.addQueryToUri(uri);
        assertNotNull(uri);
        assertEquals(uri.toString(), BASE_URI);
    }
    
}
