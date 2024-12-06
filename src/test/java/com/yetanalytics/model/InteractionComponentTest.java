package com.yetanalytics.model;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.xapi.model.InteractionComponent;
import com.yetanalytics.xapi.model.LangMap;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class InteractionComponentTest {
    private Validator validator;
    private InteractionComponent interactionComponent;

    @Before
    public void initValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        interactionComponent = new InteractionComponent();
    }

    @Test
    public void testInteractionComponent() {
        LangMap desc = new LangMap(new HashMap<>());
        desc.put("en-US", "Foo");
        interactionComponent.setId("foo");
        interactionComponent.setDescription(desc);
        assertTrue(validator.validate(interactionComponent).isEmpty());
    }

    @Test
    public void testEmpytInteractionComponent() {
        assertEquals(1, validator.validate(interactionComponent).size());
    }
}
