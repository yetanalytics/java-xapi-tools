package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
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
