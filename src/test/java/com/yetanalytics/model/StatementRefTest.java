package com.yetanalytics.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.xapi.model.StatementRef;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class StatementRefTest {
    private Validator validator;
    private StatementRef statementRef;

    @Before
    public void initValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        statementRef = new StatementRef();
    }

    @Test
    public void testStatementRef() {
        String id = "00000000-4000-8000-0000-000000000000";
        statementRef.setId(UUID.fromString(id));
        assertTrue(validator.validate(statementRef).isEmpty());
    }

    @Test
    public void testEmptyStatementRef() {
        assertEquals(1, validator.validate(statementRef).size());
    }
}
