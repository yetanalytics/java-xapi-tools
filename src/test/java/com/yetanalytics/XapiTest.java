package com.yetanalytics;

import java.io.IOException;
import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yetanalytics.xapi.model.Statement;
import com.yetanalytics.xapi.util.Mapper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class XapiTest extends TestCase {

    ObjectMapper mapper;

    private ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = Mapper.getMapper();
        }
        return mapper;
    }

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public XapiTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( XapiTest.class );
    }

    public static File getJsonTestFile(String filename){
        String path = String.format("src/test/resources/statements/%s.json", filename);
        return new File(path);
    }

    public void testBasicStatement() throws StreamReadException, DatabindException, IOException {
        File testFile = getJsonTestFile("basic");
        Statement stmt = getMapper().readValue(testFile, Statement.class);

        assertEquals(stmt.getTimestamp().format(DateTimeFormatter.ISO_INSTANT), "2023-10-27T09:03:21.723Z");
        assertEquals(stmt.getStored().format(DateTimeFormatter.ISO_INSTANT), "2023-10-27T09:03:21.722Z");
        
    }
}
