package com.yetanalytics.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yetanalytics.xapi.util.ValidationUtils;
import com.yetanalytics.xapi.model.LangMap;
import com.yetanalytics.xapi.model.Verb;

import jakarta.validation.Validator;

public class VerbTest {
    private Validator validator;
    private Verb verb;

    @BeforeEach
    public void init() {
        validator = ValidationUtils.getValidator();
        verb = new Verb();
    }

    @Test
    public void testVerb() throws URISyntaxException {
        LangMap display = new LangMap(new HashMap<>());
        display.put("en-US", "Example Verb");

        verb.setId(new URI("http://example.com/verb"));
        verb.setDisplay(display);
        ValidationUtils.assertValid(validator, verb);
    }

    @Test
    public void testEmptyVerb() {
        // One error for empty verb, one error for missing ID
        ValidationUtils.assertInvalid(validator, verb, 2);
    }
}
