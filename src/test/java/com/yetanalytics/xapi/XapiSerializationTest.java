package com.yetanalytics.xapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.flipkart.zjsonpatch.JsonDiff;
import com.yetanalytics.xapi.model.Statement;
import com.yetanalytics.xapi.model.StatementResult;
import com.yetanalytics.xapi.util.Mapper;
import com.yetanalytics.xapi.util.TestFileUtils;

public class XapiSerializationTest {

    
    @Test
    public <T> ArrayNode reserializeAndDiff(File original, Class<T> toConvert) throws IOException {

        ObjectMapper mapper = Mapper.getMapper();
        //Deserialize
        T stmt = mapper.readValue(original, toConvert);
        //Reserialize
        String reserialized = mapper.writeValueAsString(stmt);
        
        JsonNode before = mapper.readTree(original);
        JsonNode after = mapper.readTree(reserialized);
        //Get Diff
        return (ArrayNode) JsonDiff.asJson(before, after);
    }

    @Test
    public void testBasicStatement() throws IOException {
        File testFile = TestFileUtils.getJsonTestFile("basic");
        ArrayNode diff = reserializeAndDiff(testFile, Statement.class);
        assertEquals(diff.size(), 0);
    }

    @Test
    public void testContext() throws IOException {
        File testFile = TestFileUtils.getJsonTestFile("context");
        ArrayNode diff = reserializeAndDiff(testFile, Statement.class);

        // Exception on equality for context activity category where it came in as
        // a single object, not array, and came out as array
        assertEquals(diff.size(), 1);
        ObjectNode diffNode = (ObjectNode) diff.get(0);
        assertEquals(diffNode.get("op").toString(), "\"replace\"");
        assertEquals(diffNode.get("path").toString(), "\"/context/contextActivities/category\"");
    }

    @Test
    public void testExtensions() throws IOException {
        File testFile = TestFileUtils.getJsonTestFile("extensions");
        ArrayNode diff = reserializeAndDiff(testFile, Statement.class);
        assertEquals(diff.size(), 0);
    }

    @Test
    public void testAttachments() throws IOException {
        File testFile = TestFileUtils.getJsonTestFile("attachments");
        ArrayNode diff = reserializeAndDiff(testFile, Statement.class);
        assertEquals(diff.size(), 0);
    }

    @Test
    public void testGroupActor() throws IOException {
        File testFile = TestFileUtils.getJsonTestFile("group-actor");
        ArrayNode diff = reserializeAndDiff(testFile, Statement.class);
        assertEquals(diff.size(), 0);
    }

    @Test
    public void testResult() throws IOException {
        File testFile = TestFileUtils.getJsonTestFile("result");
        ArrayNode diff = reserializeAndDiff(testFile, Statement.class);
        assertEquals(diff.size(), 0);
    }

    @Test
    public void testInteractionActivity() throws IOException {
        File testFile = TestFileUtils.getJsonTestFile("interaction-activity");
        ArrayNode diff = reserializeAndDiff(testFile, Statement.class);
        assertEquals(diff.size(), 0);
    }

    @Test
    public void testStatementResults() throws IOException {
        File testFile = TestFileUtils.getJsonTestFile("statementresults");
        ArrayNode diff = reserializeAndDiff(testFile, StatementResult.class);
        assertEquals(diff.size(), 0);
    }
}
