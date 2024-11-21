package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize
public class Activity extends AbstractObject {
    private String id;

    private ActivityDefinition definition;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public ActivityDefinition getDefinition() {
        return definition;
    }
    public void setDefinition(ActivityDefinition definition) {
        this.definition = definition;
    }
}
