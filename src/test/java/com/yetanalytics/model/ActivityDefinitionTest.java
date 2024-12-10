package com.yetanalytics.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.util.ValidationUtils;
import com.yetanalytics.xapi.model.ActivityDefinition;
import com.yetanalytics.xapi.model.Extensions;
import com.yetanalytics.xapi.model.InteractionComponent;
import com.yetanalytics.xapi.model.InteractionType;
import com.yetanalytics.xapi.model.LangMap;

import jakarta.validation.Validator;

public class ActivityDefinitionTest {
    private Validator validator;
    private ActivityDefinition definition;

    private List<InteractionComponent> makeComp(String id, String descStr) {
        LangMap desc = new LangMap(new HashMap<>());
        desc.put("en-US", descStr);

        InteractionComponent component = new InteractionComponent();
        component.setId(id);
        component.setDescription(desc);

        List<InteractionComponent> components = new ArrayList<>();
        components.add(component);

        return components;
    }

    @Before
    public void init() {
        validator = ValidationUtils.getValidator();
        definition = new ActivityDefinition();
    }

    @Test
    public void testEmptyDefinition() {
        ValidationUtils.assertInvalid(validator, definition);
    }

    @Test
    public void testDefinition() {
        LangMap name = new LangMap(new HashMap<>());
        name.put("en-US", "Example Definition");

        LangMap desc = new LangMap(new HashMap<>());
        desc.put("en-US", "This is an example Activity Definition");

        String type = "http://example.com/activity-type";

        String moreInfo = "http://yetanalytics.com";
        
        Extensions ext = new Extensions(new HashMap<>());
        ext.put("http://example.org/string-value", "Foo Bar");

        definition.setName(name);
        definition.setDescription(desc);
        definition.setType(type);
        definition.setMoreInfo(moreInfo);
        definition.setExtensions(ext);

        ValidationUtils.assertValid(validator, definition);
        assertTrue(validator.validate(definition).isEmpty());

        List<InteractionComponent> choices = makeComp("component", "Interaction Component");
        definition.setChoices(choices);

        List<String> correctResponsesPattern = new ArrayList<>();
        correctResponsesPattern.add("Response 1");
        correctResponsesPattern.add("Response 2");
        definition.setCorrectResponsesPattern(correctResponsesPattern);

        ValidationUtils.assertInvalid(validator, definition);
    }

    @Test
    public void testChoiceDefinition() {
        definition.setInteractionType(InteractionType.CHOICE);
        ValidationUtils.assertValid(validator, definition);

        List<InteractionComponent> choices = makeComp("choice", "Choice");
        definition.setChoices(choices);
        ValidationUtils.assertValid(validator, definition);

        definition.setInteractionType(InteractionType.TRUE_FALSE);
        ValidationUtils.assertInvalid(validator, definition);
    }

    @Test
    public void testSequencingDefinition() {
        definition.setInteractionType(InteractionType.SEQUENCING);
        ValidationUtils.assertValid(validator, definition);

        List<InteractionComponent> choices = makeComp("choice", "Choice");
        definition.setChoices(choices);
        ValidationUtils.assertValid(validator, definition);

        definition.setInteractionType(InteractionType.FILL_IN);
        ValidationUtils.assertInvalid(validator, definition);
    }

    @Test
    public void testLikertDefinition() {
        definition.setInteractionType(InteractionType.LIKERT);
        ValidationUtils.assertValid(validator, definition);

        List<InteractionComponent> scale = makeComp("scale", "Scale");
        definition.setScale(scale);
        ValidationUtils.assertValid(validator, definition);

        definition.setInteractionType(InteractionType.LONG_FILL_IN);
        ValidationUtils.assertInvalid(validator, definition);
    }

    @Test
    public void testMatchingDefinition() {
        definition.setInteractionType(InteractionType.MATCHING);
        ValidationUtils.assertValid(validator, definition);

        List<InteractionComponent> source = makeComp("source", "Source");
        List<InteractionComponent> target = makeComp("target", "Target");
        definition.setSource(source);
        definition.setTarget(target);
        ValidationUtils.assertValid(validator, definition);

        definition.setInteractionType(InteractionType.NUMERIC);
        ValidationUtils.assertInvalid(validator, definition);
    }

    @Test
    public void testPerformanceDefinition() {
        definition.setInteractionType(InteractionType.PERFORMANCE);
        ValidationUtils.assertValid(validator, definition);

        List<InteractionComponent> steps = makeComp("steps", "Steps");
        definition.setSteps(steps);
        ValidationUtils.assertValid(validator, definition);

        definition.setInteractionType(InteractionType.OTHER);
        ValidationUtils.assertInvalid(validator, definition);
    }
}
