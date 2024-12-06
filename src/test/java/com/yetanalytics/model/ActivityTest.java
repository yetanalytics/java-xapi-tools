package com.yetanalytics.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.xapi.model.Activity;
import com.yetanalytics.xapi.model.ActivityDefinition;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ActivityTest {
    private Validator validator;
    private ActivityDefinition definition;
    private Activity activity;

    @Before
    public void initValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        definition = new ActivityDefinition();
        activity = new Activity();
    }

    @Test
    public void testActivity() {
        activity.setId("http://example.org/activity");
        assertTrue(validator.validate(activity).isEmpty());

        // TODO: Enforce that definitions, as JSON objects, can't be empty
        // For now we allow them though
        activity.setDefinition(definition);
        assertTrue(validator.validate(activity).isEmpty());
    }

    @Test
    public void testEmptyActivity() {
        var violations = validator.validate(activity);
        assertEquals(1, violations.size());
    }
}
