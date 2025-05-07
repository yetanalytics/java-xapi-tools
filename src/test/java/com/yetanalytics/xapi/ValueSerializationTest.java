package com.yetanalytics.xapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.flipkart.zjsonpatch.JsonDiff;
import com.yetanalytics.xapi.model.Agent;
import com.yetanalytics.xapi.model.Attachment;
import com.yetanalytics.xapi.model.LangMap;
import com.yetanalytics.xapi.model.Result;
import com.yetanalytics.xapi.model.Statement;
import com.yetanalytics.xapi.model.Verb;
import com.yetanalytics.xapi.util.Mapper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ValueSerializationTest extends TestCase {
    public ValueSerializationTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ValueSerializationTest.class);
    }

    public <T> ArrayNode reserializeAndDiff(String original, Class<T> toConvert) throws JsonProcessingException {
        ObjectMapper mapper = Mapper.getMapper();
        // Deserialize
        T value = mapper.readValue(original, toConvert);
        // Reserialize
        String reserialized = mapper.writeValueAsString(value);

        JsonNode before = mapper.readTree(original);
        JsonNode after = mapper.readTree(reserialized);
        
        // Get Diff
        return (ArrayNode) JsonDiff.asJson(before, after);
    }

    // TODO: Figure out how not have to wrap string properties in objects

    public void testUUID() throws JsonProcessingException {
        String uuidStr = "{\"id\": \"00000000-4000-8000-0000-000000000000\"}";
        ArrayNode diff = reserializeAndDiff(uuidStr, Statement.class);
        assertEquals(0, diff.size());
    }

    public void testUri() throws JsonProcessingException {
        String uriStr = "{\"id\": \"http://EXAMPLE.com\"}";
        ArrayNode diff = reserializeAndDiff(uriStr, Verb.class);
        assertEquals(0, diff.size());
    }

    public void testUri2() throws JsonProcessingException {
        String uriStr = "{\"id\": \"http://你好世界.com\"}";
        ArrayNode diff = reserializeAndDiff(uriStr, Verb.class);
        assertEquals(0, diff.size());
    }

    public void testTimestamp() throws JsonProcessingException {
        String timestampStr = "{\"timestamp\": \"2023-10-27T09:03:21.722Z\"}";
        ArrayNode diff = reserializeAndDiff(timestampStr, Statement.class);
        assertEquals(0, diff.size());
    }

    public void testDuration() throws JsonProcessingException {
        String durationStr = "{\"duration\": \"PT4H35M59.14S\"}";
        ArrayNode diff = reserializeAndDiff(durationStr, Result.class);
        assertEquals(0, diff.size());
    }

    public void testDuration2() throws JsonProcessingException {
        String durationStr = "{\"duration\": \"PT16559.14S\"}";
        ArrayNode diff = reserializeAndDiff(durationStr, Result.class);
        assertEquals(0, diff.size());
    }

    public void testMimeType() throws JsonProcessingException {
        // TODO: Deal with when there is whitespace after the semicolon
        String mimeTypeStr = "{\"contentType\": \"text/plain; charset=UTF-8\"}";
        ArrayNode diff = reserializeAndDiff(mimeTypeStr, Attachment.class);
        assertEquals(0, diff.size());
    }

    public void testLangTag() throws JsonProcessingException {
        String langTagStr = "{\"en-us\": \"foo\"}";
        ArrayNode diff = reserializeAndDiff(langTagStr, LangMap.class);
        assertEquals(0, diff.size());
    }

    public void testVersion() throws JsonProcessingException {
        String versionStr = "{\"version\": \"1.0.0\"}";
        ArrayNode diff = reserializeAndDiff(versionStr, Statement.class);
        assertEquals(0, diff.size());
    }

    public void testSHA1() throws JsonProcessingException {
        String sha1Str = "{\"mbox_sha1sum\": \"767e74eab7081c41e0b83630511139d130249666\"}";
        ArrayNode diff = reserializeAndDiff(sha1Str, Agent.class);
        assertEquals(0, diff.size());
    }

    public void testSHA2() throws JsonProcessingException {
        String sha2Str = "{\"sha2\": \"321ba197033e81286fedb719d60d4ed5cecaed170733cb4a92013811afc0e3b6\"}";
        ArrayNode diff = reserializeAndDiff(sha2Str, Attachment.class);
        assertEquals(0, diff.size());
    }
}
