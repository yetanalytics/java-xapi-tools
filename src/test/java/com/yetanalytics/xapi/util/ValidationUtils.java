package com.yetanalytics.xapi.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ValidationUtils {

    public static Validator getValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static void assertValid(Validator validator, Object object) {
        assertTrue(validator.validate(object).isEmpty());
    }

    public static void assertInvalid(Validator validator, Object object) {
        assertInvalid(validator, object, 1);
    }

    public static void assertInvalid(Validator validator, Object object, int numErrors) {
        assertEquals(numErrors, validator.validate(object).size());
    }
}
