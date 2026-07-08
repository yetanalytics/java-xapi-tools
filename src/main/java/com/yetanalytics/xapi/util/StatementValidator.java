package com.yetanalytics.xapi.util;

import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yetanalytics.xapi.model.Statement;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class StatementValidator {

    private Validator validator;

    public StatementValidator() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public Validator getValidator() {
        return this.validator;
    }

    public StatementValidationResult validateStatement(String statementJson) {
        try {
            Statement statement = Mapper.getMapper().readValue(statementJson,Statement.class);
            return validateStatement(statement);
        } catch (JsonProcessingException e) {
            Set<String> errors = Set.of("Invalid JSON: " + e.getMessage());
            return new StatementValidationResult(false, errors, null);
        }
    }

    public StatementValidationResult validateStatement(Statement statement) {
        Set<String> errors = this.validator.validate(statement).stream()
            .map(v -> v.getPropertyPath() + " " + v.getMessage())
            .collect(java.util.stream.Collectors.toSet());
        boolean valid = errors.isEmpty();
        return new StatementValidationResult(valid, errors, valid ? statement : null);
    }

    public class StatementValidationResult {
        private boolean valid;
        private Set<String> errors;
        private Statement statement;

        public StatementValidationResult(boolean valid, Set<String> errors, Statement statement) {
            this.valid = valid;
            this.errors = errors;
            this.statement = statement;
        }

        public boolean isValid() {
            return valid;
        }
        
        public Set<String> getErrors() {
            return errors;
        }
        
        public Statement getStatement() {
            return statement;
        }
    }
}
