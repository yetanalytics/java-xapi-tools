package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import java.net.URI;

/**
* Class representation of the Activity Object Type of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#2441-when-the-objecttype-is-activity">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
@JsonDeserialize
public class Activity extends AbstractObject {

    @NotNull
    private URI id;

    @Valid
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

    @Override
    @JsonIgnore
    @AssertFalse
    public boolean isEmpty() {
        return id == null && definition == null;
    }
}
