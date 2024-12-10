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

        activity.setDefinition(definition);
        definition.setMoreInfo("https://yetanalytics.com");
        ValidationUtils.assertValid(validator, activity);
    }

    @Test
    public void testEmptyActivity() {
        // One error for empty object, one error for missing ID
        ValidationUtils.assertInvalid(validator, activity, 2);
    }
}
