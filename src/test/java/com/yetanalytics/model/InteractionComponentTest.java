package com.yetanalytics.model;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yetanalytics.xapi.util.ValidationUtils;
import com.yetanalytics.xapi.model.InteractionComponent;
import com.yetanalytics.xapi.model.LangMap;

import jakarta.validation.Validator;

public class InteractionComponentTest {
    private Validator validator;
    private InteractionComponent interactionComponent;

    @BeforeEach
    public void init() {
        validator = ValidationUtils.getValidator();
        interactionComponent = new InteractionComponent();
    }

    @Test
    public void testInteractionComponent() {
        LangMap desc = new LangMap(new HashMap<>());
        desc.put("en-US", "Foo");
        interactionComponent.setId("foo");
        interactionComponent.setDescription(desc);
        ValidationUtils.assertValid(validator, interactionComponent);
    }

    @Test
    public void testEmpytInteractionComponent() {
        // One error for empty component, one error for missing ID
        ValidationUtils.assertInvalid(validator, interactionComponent, 2);
    }
}
