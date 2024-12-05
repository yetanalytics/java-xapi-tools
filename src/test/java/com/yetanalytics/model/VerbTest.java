package com.yetanalytics.model;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.xapi.model.LangMap;
import com.yetanalytics.xapi.model.Verb;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class VerbTest {
    private Validator validator;
    private Verb verb;

    @Before
    public void initValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        verb = new Verb();
    }

    @Test
    public void testVerb() {
        LangMap display = new LangMap(new HashMap<>());
        display.put("en-US", "Example Verb");

        verb.setId("http://example.com/verb");
        verb.setDisplay(display);
        assertTrue(validator.validate(verb).isEmpty());
    }

    @Test
    public void testEmptyVerb() {
        var violations = validator.validate(verb);
        assertEquals(1, violations.size());
    }
}
