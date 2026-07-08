package com.yetanalytics.xapi.util;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.yetanalytics.xapi.model.Activity;
import com.yetanalytics.xapi.model.LangTag;
import com.yetanalytics.xapi.model.Statement;

public class StatementValidatorTest {

    private StatementValidator validator = new StatementValidator();

    @Test
    public void testValidStatement() {
        String validStatementJson = TestFileUtils.getJsonTestFileString("basic");
        StatementValidator.StatementValidationResult result = validator.validateStatement(validStatementJson);
        assert (result.isValid());
        Statement statement = result.getStatement();
        assert (statement != null);
        assertTrue(statement.getId() != null);
        Activity object = (Activity) statement.getObject();
        assert (object != null);
        assertTrue(object.getDefinition().getDescription().get(LangTag.parse("en-us"))
                .equals("The First Activity"));
    }

    @Test
    public void testInvalidJSONStatement() {
        String invalidStatementJson = TestFileUtils.getJsonTestFileString("invalid-json");
        StatementValidator.StatementValidationResult result = validator.validateStatement(invalidStatementJson);
        assert (!result.isValid());
        assert (result.getErrors().size() > 0);
        result.getErrors().stream().findFirst().ifPresent(error -> assertTrue(error.startsWith("Invalid JSON:")));
    }

    @Test
    public void testInvalidFieldStatement() {
        String invalidStatementJson = TestFileUtils.getJsonTestFileString("invalid-field");
        StatementValidator.StatementValidationResult result = validator.validateStatement(invalidStatementJson);
        assert (!result.isValid());
        assert (result.getStatement() == null);
        assert (result.getErrors().size() > 0);
        result.getErrors().stream().findFirst().ifPresent(error -> assertTrue(error.contains("descriptionz")));
    }

    @Test
    public void testInvalidSpecStatement() {
        String invalidStatementJson = TestFileUtils.getJsonTestFileString("invalid-spec");
        StatementValidator.StatementValidationResult result = validator.validateStatement(invalidStatementJson);
        assert (!result.isValid());
        assert (result.getStatement() == null);
        assert (result.getErrors().size() > 0);
        assertTrue(errorsContain(result.getErrors(), "verb may not be null"));
        assertTrue(errorsContain(result.getErrors(), "actor.identifiedAgent Agent must have exactly 1 Inverse Functional Identifier (IFI)"));
        assertTrue(errorsContain(result.getErrors(), "actor.empty Agent must not be empty"));
    }

    private boolean errorsContain(Set<String> errors, String substring) {
        return errors.stream().anyMatch(error -> error.contains(substring));
    }
}
