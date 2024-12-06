package com.yetanalytics.model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.xapi.model.Activity;
import com.yetanalytics.xapi.model.Agent;
import com.yetanalytics.xapi.model.Statement;
import com.yetanalytics.xapi.model.StatementResult;
import com.yetanalytics.xapi.model.Verb;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class StatementResultTest {
    private Validator validator;
    private StatementResult statementResult;

    @Before
    public void initValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        statementResult = new StatementResult();
    }

    @Test
    public void testStatementResult() {
        Agent actor = new Agent();
        actor.setMbox("mailto:foo@example.com");

        Verb verb = new Verb();
        verb.setId("http://example.org/verb");

        Activity object = new Activity();
        object.setId("http://example.org/object");

        Statement statement = new Statement();
        statement.setActor(actor);
        statement.setVerb(verb);
        statement.setObject(object);
        
        List<Statement> statements = new ArrayList<>();
        statements.add(statement);
        statementResult.setStatements(statements);

        assertTrue(validator.validate(statements).isEmpty());
    }

    @Test
    public void testEmptyStatementResult() {
        assertEquals(1, validator.validate(statementResult).size());
    }
}
