package com.yetanalytics.model;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.util.ValidationUtils;
import com.yetanalytics.xapi.model.LangMap;
import com.yetanalytics.xapi.model.Verb;

import jakarta.validation.Validator;

public class VerbTest {
    private Validator validator;
    private Verb verb;

    @Before
    public void init() {
        validator = ValidationUtils.getValidator();
        verb = new Verb();
    }

    @Test
    public void testVerb() {
        LangMap display = new LangMap(new HashMap<>());
        display.put("en-US", "Example Verb");

        verb.setId("http://example.com/verb");
        verb.setDisplay(display);
        ValidationUtils.assertValid(validator, verb);
    }

    @Test
    public void testEmptyVerb() {
        ValidationUtils.assertInvalid(validator, verb);
    }
}
