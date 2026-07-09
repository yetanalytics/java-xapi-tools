package com.yetanalytics.model;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yetanalytics.xapi.util.ValidationUtils;
import com.yetanalytics.xapi.model.Activity;
import com.yetanalytics.xapi.model.ActivityDefinition;

import jakarta.validation.Validator;

public class ActivityTest {
    private Validator validator;
    private ActivityDefinition definition;
    private Activity activity;

    @BeforeEach
    public void init() {
        validator = ValidationUtils.getValidator();
        definition = new ActivityDefinition();
        activity = new Activity();
    }

    @Test
    public void testActivity() throws URISyntaxException {
        activity.setId(new URI("http://example.org/activity"));
        ValidationUtils.assertValid(validator, activity);

        activity.setDefinition(definition);
        definition.setMoreInfo(new URI("https://yetanalytics.com"));
        ValidationUtils.assertValid(validator, activity);
    }

    @Test
    public void testEmptyActivity() {
        // One error for empty object, one error for missing ID
        ValidationUtils.assertInvalid(validator, activity, 2);
    }
}
