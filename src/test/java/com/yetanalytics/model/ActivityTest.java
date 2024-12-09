package com.yetanalytics.model;

import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.util.ValidationUtils;
import com.yetanalytics.xapi.model.Activity;
import com.yetanalytics.xapi.model.ActivityDefinition;

import jakarta.validation.Validator;

public class ActivityTest {
    private Validator validator;
    private ActivityDefinition definition;
    private Activity activity;

    @Before
    public void init() {
        validator = ValidationUtils.getValidator();
        definition = new ActivityDefinition();
        activity = new Activity();
    }

    @Test
    public void testActivity() {
        activity.setId("http://example.org/activity");
        ValidationUtils.assertValid(validator, activity);

        // TODO: Enforce that definitions, as JSON objects, can't be empty
        // For now we allow them though
        activity.setDefinition(definition);
        ValidationUtils.assertValid(validator, activity);
    }

    @Test
    public void testEmptyActivity() {
        ValidationUtils.assertInvalid(validator, activity);
    }
}
