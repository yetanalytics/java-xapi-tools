package com.yetanalytics;

import java.io.IOException;
import java.math.BigDecimal;
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
        assertEquals(stmt.getId(), UUID.fromString("6fbd600f-d87c-4c74-801a-2ec2e53231c8"));
        assertEquals(stmt.getVersion(), "1.0.3");

        Agent actor = (Agent) stmt.getActor();
        assertEquals(actor.getName(), "Cliff Casey");
        assertEquals(actor.getAccount().getName(), "23897525");
        assertEquals(actor.getAccount().getHomePage(), "https://users.training.com");

        Verb verb = stmt.getVerb();
        assertEquals(verb.getId(), "https://www.yetanalytics.com/profiles/thing/1.0/concepts/verbs/set");
        assertEquals(verb.getDisplay().get("en-us"), "Set");

        Activity object = (Activity) stmt.getObject();
        assertEquals(object.getId(), "https://www.yetanalytics.com/profiles/thing/1.0/concepts/activities/act1");

        ActivityDefinition def = object.getDefinition();
        Set<String> nameLangCodes = def.getName().getLanguageCodes();
        String nameLangCode = nameLangCodes.iterator().next();
        assertEquals(def.getName().get(nameLangCode), "Activity 1");

        Set<String> descLangCodes = def.getDescription().getLanguageCodes();
        String descLangCode = descLangCodes.iterator().next();
        assertEquals(def.getDescription().get(descLangCode), "The First Activity");

        AbstractActor authority = stmt.getAuthority();
        assertEquals(authority.getName(), "Yet Analytics Inc");
        assertEquals(authority.getMbox(), "mailto:authority@yetanalytics.com");
    }

    public void testAttachments() throws StreamReadException, DatabindException, IOException {
        File testFile = TestFileUtils.getJsonTestFile("attachments");
        Statement stmt = Mapper.getMapper().readValue(testFile, Statement.class);
        assertEquals(stmt.getAttachments().size(), 1);
        Attachment att1 = stmt.getAttachments().get(0);
        assertEquals(att1.getUsageType(), "https://www.yetanalytics.com/usagetypes/1");
        assertEquals(att1.getDisplay().get("en-us"), "Attachment 1");
        assertEquals(att1.getDescription().get("en-us"), "The First Attachment");
        assertEquals(att1.getContentType(), "application/json");
        assertEquals(att1.getLength(), Integer.valueOf(450));
        assertEquals(att1.getSha2(), "426cf3a8b2864dd91201b989ba5728181da52bfff9a0489670e54cd8ec8b3a50");
        assertEquals(att1.getFileUrl(), "https://www.yetanalytics.com/files/file1.json");
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
        assertEquals(res.getDuration().getValue().toMillis(), 16559140);

        Score score = res.getScore();
        assertEquals(score.getMin(), new BigDecimal("0"));
        assertEquals(score.getMax(), new BigDecimal("100.010"));
        assertEquals(score.getRaw(), new BigDecimal("50"));
        assertEquals(score.getScaled(), new BigDecimal("0.0"));
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
        assertEquals(ctx.getLanguage(), "en-us");
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

    public void testInteractionActivity() throws StreamReadException, DatabindException, IOException {
        File testFile = TestFileUtils.getJsonTestFile("interaction-activity");
        Statement stmt = Mapper.getMapper().readValue(testFile, Statement.class);

        Activity act = (Activity) stmt.getObject();
        assertEquals(act.getId(), "https://www.yetanalytics.com/activities/act1/question1");
        
        ActivityDefinition def = act.getDefinition();
        assertEquals(def.getType(), "http://adlnet.gov/expapi/activities/cmi.interaction");
        assertEquals(def.getName().get("en"), "Multichoice Question");
        assertEquals(def.getCorrectResponsesPattern().get(0), "a");
        assertEquals(def.getInteractionType(), InteractionType.CHOICE);
        InteractionComponent choice = def.getChoices().iterator().next();
        assertEquals(choice.getId(), "a");
        assertEquals(choice.getDescription().get("en"), "A");
    }

    public void testGroupActor() throws StreamReadException, DatabindException, IOException {
        File testFile = TestFileUtils.getJsonTestFile("group-actor");
        Statement stmt = Mapper.getMapper().readValue(testFile, Statement.class);

        Group group = (Group) stmt.getActor();
        assertEquals(group.getMbox(), "mailto:group@group.com");
        assertEquals(group.getName(), "Relevant Group");
        assertEquals(group.getMember().size(), 1);
        assertEquals(group.getMember().get(0).getName(), "Cliff Casey");
    }

    public void testStatementResults() throws StreamReadException, DatabindException, IOException {
        File testFile = TestFileUtils.getJsonTestFile("statementresults");
        StatementResult stmtRes = Mapper.getMapper().readValue(testFile, StatementResult.class);
        assertEquals(stmtRes.getMore(), "/xapi/statements?limit=2&from=6fbd600f-d17c-4c74-801a-2ec2e53231c9");

        assertEquals(stmtRes.getStatements().get(0).getVerb().getId(),
            "https://www.yetanalytics.com/profiles/thing/1.0/concepts/verbs/did");
        Agent actor2 = (Agent) stmtRes.getStatements().get(1).getActor();
        assertEquals(actor2.getName(), "Student User 2");
    }
}
