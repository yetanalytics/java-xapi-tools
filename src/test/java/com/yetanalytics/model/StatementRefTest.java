package com.yetanalytics.model;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.util.ValidationUtils;
import com.yetanalytics.xapi.model.StatementRef;

import jakarta.validation.Validator;

public class StatementRefTest {
    private Validator validator;
    private StatementRef statementRef;

    @Before
    public void init() {
        validator = ValidationUtils.getValidator();
        statementRef = new StatementRef();
    }

    @Test
    public void testStatementRef() {
        String id = "00000000-4000-8000-0000-000000000000";
        statementRef.setId(UUID.fromString(id));
        ValidationUtils.assertValid(validator, statementRef);
    }

    @Test
    public void testEmptyStatementRef() {
        ValidationUtils.assertInvalid(validator, statementRef);
    }
}
