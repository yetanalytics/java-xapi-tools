package com.yetanalytics.xapi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;

/**
* Class representation of the Interaction Component of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#interaction-components">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
public class InteractionComponent implements JSONObject {

    @NotNull
    private String id;

    private LangMap description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LangMap getDescription() {
        return description;
    }

    public void setDescription(LangMap description) {
        this.description = description;
    }

    // Validation

    @Override
    @JsonIgnore
    @AssertFalse
    public boolean isEmpty() {
        return id == null && description == null;
    }
}
