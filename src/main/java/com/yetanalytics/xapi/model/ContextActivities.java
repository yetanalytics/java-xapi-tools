package com.yetanalytics.xapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yetanalytics.xapi.model.deserializers.ContextActivityListDeserializer;

import jakarta.validation.constraints.AssertFalse;

/**
* Class representation of the Context Activities Component of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#2462-contextactivities-property">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
public class ContextActivities implements JSONObject {

    @JsonDeserialize(using = ContextActivityListDeserializer.class)
    private List<Activity> parent;
    @JsonDeserialize(using = ContextActivityListDeserializer.class)
    private List<Activity> grouping;
    @JsonDeserialize(using = ContextActivityListDeserializer.class)
    private List<Activity> category;
    @JsonDeserialize(using = ContextActivityListDeserializer.class)
    private List<Activity> other;
    
    public List<Activity> getParent() {
        return parent;
    }
    public void setParent(List<Activity> parent) {
        this.parent = parent;
    }
    public List<Activity> getGrouping() {
        return grouping;
    }
    public void setGrouping(List<Activity> grouping) {
        this.grouping = grouping;
    }
    public List<Activity> getCategory() {
        return category;
    }
    public void setCategory(List<Activity> category) {
        this.category = category;
    }
    public List<Activity> getOther() {
        return other;
    }
    public void setOther(List<Activity> other) {
        this.other = other;
    }

    @Override
    @JsonIgnore
    @AssertFalse
    public boolean isEmpty() {
        return (
            parent == null && grouping == null &&
            category == null && other == null
        );
    }
}
