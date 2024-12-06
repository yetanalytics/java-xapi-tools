package com.yetanalytics.xapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.AssertTrue;

/**
* Class representation of the Activity Definition Component of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#activity-definition">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
public class ActivityDefinition {

    private LangMap name;
    private LangMap description;
    private String type;
    private String moreInfo;
    private Extensions extensions;

    //cmi.interaction specific properties
    private InteractionType interactionType;
    private List<String> correctResponsesPattern;

    //cmi.interaction Components
    private List<InteractionComponent> choices;
    private List<InteractionComponent> scale;
    private List<InteractionComponent> source;
    private List<InteractionComponent> target;
    private List<InteractionComponent> steps;
    
    public LangMap getName() {
        return name;
    }
    public void setName(LangMap name) {
        this.name = name;
    }
    public LangMap getDescription() {
        return description;
    }
    public void setDescription(LangMap description) {
        this.description = description;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMoreInfo() {
        return moreInfo;
    }
    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
    public Extensions getExtensions() {
        return extensions;
    }
    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }
    public InteractionType getInteractionType() {
        return interactionType;
    }
    public void setInteractionType(InteractionType interactionType) {
        this.interactionType = interactionType;
    }
    public List<String> getCorrectResponsesPattern() {
        return correctResponsesPattern;
    }
    public void setCorrectResponsesPattern(List<String> correctResponsesPattern) {
        this.correctResponsesPattern = correctResponsesPattern;
    }
    public List<InteractionComponent> getChoices() {
        return choices;
    }
    public void setChoices(List<InteractionComponent> choices) {
        this.choices = choices;
    }
    public List<InteractionComponent> getScale() {
        return scale;
    }
    public void setScale(List<InteractionComponent> scale) {
        this.scale = scale;
    }
    public List<InteractionComponent> getSource() {
        return source;
    }
    public void setSource(List<InteractionComponent> source) {
        this.source = source;
    }
    public List<InteractionComponent> getTarget() {
        return target;
    }
    public void setTarget(List<InteractionComponent> target) {
        this.target = target;
    }
    public List<InteractionComponent> getSteps() {
        return steps;
    }
    public void setSteps(List<InteractionComponent> steps) {
        this.steps = steps;
    }

    // Validation

    private boolean isChoices() {
        return (
            // choices is allowed
            scale == null &&
            source == null &&
            target == null &&
            steps == null
        );
    }

    private boolean isScale() {
        return (
            // scale is allowed
            choices == null &&
            source == null &&
            target == null &&
            steps == null
        );
    }

    private boolean isSourceTarget() {
        return (
            // source and target are allowed
            choices == null &&
            scale == null &&
            steps == null
        );
    }

    private boolean isSteps() {
        return (
            // steps is allowed
            choices == null &&
            scale == null &&
            source == null &&
            target == null
        );
    }

    private boolean isNoInteractionComponents() {
        return (
            choices == null &&
            scale == null &&
            source == null &&
            target == null &&
            steps == null
        );
    }

    private boolean isNoInteraction() {
        return (
            isNoInteractionComponents() &&
            correctResponsesPattern == null
        );
    }

    @AssertTrue
    public boolean isValidInteractionActivity() {
        if (interactionType == null) {
            return isNoInteraction();
        }
        switch (interactionType) {
            case CHOICE:
                return isChoices();
            case SEQUENCING:
                return isChoices();
            case LIKERT:
                return isScale();
            case MATCHING:
                return isSourceTarget();
            case PERFORMANCE:
                return isSteps();
            default:
                return isNoInteractionComponents();
        }
    }
}
