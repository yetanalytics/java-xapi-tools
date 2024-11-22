package com.yetanalytics;

import java.io.IOException;
import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.flipkart.zjsonpatch.JsonDiff;
import com.yetanalytics.util.TestFileUtils;
import com.yetanalytics.xapi.model.*;
import com.yetanalytics.xapi.util.Mapper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class XapiSerializationTest extends TestCase {

    public XapiSerializationTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( XapiSerializationTest.class );
    }

    public <T> ArrayNode reserializeAndDiff(File original, Class<T> toConvert) throws IOException {
        //Deserialize
        T stmt = Mapper.getMapper().readValue(original, toConvert);
        //Reserialize
        String reserialized = Mapper.getMapper().writeValueAsString(stmt);
        System.out.println(reserialized);
        
        JsonNode before = Mapper.getMapper().readTree(original);
        JsonNode after = Mapper.getMapper().readTree(reserialized);
        //Get Diff
        return (ArrayNode) JsonDiff.asJson(before, after);
    }

    public void testBasicStatement() throws IOException {
        File testFile = TestFileUtils.getJsonTestFile("basic");
        ArrayNode diff = reserializeAndDiff(testFile, Statement.class);
        assertEquals(diff.size(), 0);
    }

    public void testContext() throws IOException {
        File testFile = TestFileUtils.getJsonTestFile("context");
        ArrayNode diff = reserializeAndDiff(testFile, Statement.class);
        assertEquals(diff.size(), 1);
    }

    public void testExtensions() throws IOException {
        File testFile = TestFileUtils.getJsonTestFile("extensions");
        ArrayNode diff = reserializeAndDiff(testFile, Statement.class);
        assertEquals(diff.size(), 0);
    }

    public void testGroup() throws IOException {
        File testFile = TestFileUtils.getJsonTestFile("group-actor");
        ArrayNode diff = reserializeAndDiff(testFile, Statement.class);
        assertEquals(diff.size(), 0);
    }

    public void testResult() throws IOException {
        File testFile = TestFileUtils.getJsonTestFile("result");
        ArrayNode diff = reserializeAndDiff(testFile, Statement.class);
        assertEquals(diff.size(), 0);
    }
}
