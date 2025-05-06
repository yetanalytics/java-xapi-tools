package com.yetanalytics.xapi.model;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
* Class representation of the Activity Object Type of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#2441-when-the-objecttype-is-activity">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
@JsonDeserialize
public class Activity extends AbstractObject {
    private URI id;

    private ActivityDefinition definition;

    public URI getId() {
        return id;
    }
    public void setId(URI id) {
        this.id = id;
    }

    public ActivityDefinition getDefinition() {
        return definition;
    }
    public void setDefinition(ActivityDefinition definition) {
        this.definition = definition;
    }
}
