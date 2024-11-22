package com.yetanalytics;

import java.io.IOException;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.yetanalytics.util.TestFileUtils;
import com.yetanalytics.xapi.model.*;
import com.yetanalytics.xapi.util.Mapper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class XapiDeserializationTest extends TestCase {

    public XapiDeserializationTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( XapiDeserializationTest.class );
    }

    public void testBasicStatement() throws StreamReadException, DatabindException, IOException {
        File testFile = TestFileUtils.getJsonTestFile("basic");
        Statement stmt = Mapper.getMapper().readValue(testFile, Statement.class);

        assertEquals(stmt.getTimestamp().format(DateTimeFormatter.ISO_INSTANT), "2023-10-27T09:03:21.723Z");
        assertEquals(stmt.getStored().format(DateTimeFormatter.ISO_INSTANT), "2023-10-27T09:03:21.722Z");
        
        Agent actor = (Agent) stmt.getActor();
        assertEquals(actor.getName(), "Cliff Casey");
        assertEquals(actor.getAccount().getName(), "23897525");
        assertEquals(actor.getAccount().getHomePage(), "https://users.training.com");

        Verb verb = stmt.getVerb();
        assertEquals(verb.getId(), "https://www.yetanalytics.com/profiles/thing/1.0/concepts/verbs/set");

        Activity object = (Activity) stmt.getObject();
        assertEquals(object.getId(), "https://www.yetanalytics.com/profiles/thing/1.0/concepts/activities/act1");

        ActivityDefinition def = object.getDefinition();
        Set<String> nameLangCodes = def.getName().getLanguageCodes();
        String nameLangCode = nameLangCodes.iterator().next();
        assertEquals(def.getName().get(nameLangCode), "Activity 1");

        Set<String> descLangCodes = def.getDescription().getLanguageCodes();
        String descLangCode = descLangCodes.iterator().next();
        assertEquals(def.getDescription().get(descLangCode), "The First Activity");
    }

    public void testExtensions() throws StreamReadException, DatabindException, IOException {
        File testFile = TestFileUtils.getJsonTestFile("extensions");
        Statement stmt = Mapper.getMapper().readValue(testFile, Statement.class);

        Activity object = (Activity) stmt.getObject();
        assertEquals(object.getId(), "https://www.yetanalytics.com/profiles/thing/1.0/concepts/activities/act1");

        Extensions ext = object.getDefinition().getExtensions();
        String extKey = "http://www.yetanalytics.com/profiles/thing/1.0/concepts/extensions/ext1";
        
        //collections API
        @SuppressWarnings("unchecked")
        HashMap<String, Object> ext1 = (LinkedHashMap<String, Object>) ext.get(extKey);
        String singleString = (String) ext1.get("singleString");
        assertEquals(singleString, "blah blah");

        //JSONPath API
        singleString = ext.read(extKey, "$.singleString", String.class);
        assertEquals(singleString, "blah blah");
        Integer elementNumber = ext.read(extKey, "$.listOfThings[1].number", Integer.class);
        assertEquals(elementNumber, Integer.valueOf(2));
        Double decimalEntry = ext.read(extKey, "$.decimalEntry", Double.class);
        assertEquals(decimalEntry, Double.valueOf(3.14159));
        Boolean boolEntry = ext.read(extKey, "$.boolEntry", Boolean.class);
        assertEquals(boolEntry, Boolean.TRUE);
        String nullEntry = ext.read(extKey, "$.nullEntry", String.class);
        assertNull(nullEntry);
        String miss = ext.read(extKey, "$.miss", String.class);
        assertNull(miss);
        String badKey = ext.read("badKey", "$.doesnt.matter", String.class);
        assertNull(badKey);
    }

    public void testResult() throws StreamReadException, DatabindException, IOException {
        File testFile = TestFileUtils.getJsonTestFile("result");
        Statement stmt = Mapper.getMapper().readValue(testFile, Statement.class);

        Result res = stmt.getResult();
        assertTrue(res.getCompletion());
        assertFalse(res.getSuccess());
        assertEquals(res.getResponse(), "Eh");
        assertEquals(res.getDuration().toMillis(), 16559140);

        Score score = res.getScore();
        assertEquals(score.getMin(), Double.valueOf(0));
        assertEquals(score.getMax(), Double.valueOf(100));
        assertEquals(score.getRaw(), Double.valueOf(50));
        assertEquals(score.getScaled(), Double.valueOf(0));
    }

    public void testContext() throws StreamReadException, DatabindException, IOException {
        File testFile = TestFileUtils.getJsonTestFile("context");
        Statement stmt = Mapper.getMapper().readValue(testFile, Statement.class);

        Context ctx = stmt.getContext();
        assertEquals(ctx.getRegistration(), UUID.fromString("6fbd600f-d17c-4c74-801a-2ec2e53231c7"));
        assertEquals(ctx.getInstructor().getName(), "Teach McLearney");
        assertEquals(ctx.getTeam().getName(), "Class B");
        assertEquals(ctx.getTeam().getMember().get(1).getName(), "Student Smith");
        assertEquals(ctx.getRevision(), "v0.0.1");
        assertEquals(ctx.getPlatform(), "JUnit Testing");
        assertEquals(ctx.getStatement().getId(), UUID.fromString("6fbd600f-d17c-4c74-801a-2ec2e53231c6"));
        String extKey = "https://www.yetanalytics.com/extensions/ext3";
        assertEquals(ctx.getExtensions().read(extKey, "$.thing", String.class), "stuff");
        ContextActivities ctxActs = ctx.getContextActivities();
        assertEquals(ctxActs.getParent().get(1).getId(), "https://www.yetanalytics.com/activities/parent2");
        //grouping came in as a single activity and was converted to a list
        assertEquals(ctxActs.getGrouping().get(0).getId(), "https://www.yetanalytics.com/activities/grouping1");
        assertEquals(ctxActs.getCategory().get(0).getId(), "https://www.yetanalytics.com/activities/category1");
        assertEquals(ctxActs.getOther().get(0).getId(), "https://www.yetanalytics.com/activities/other1");
    }
}
