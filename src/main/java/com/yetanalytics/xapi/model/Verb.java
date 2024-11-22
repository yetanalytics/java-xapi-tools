package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yetanalytics.xapi.model.deserializers.LangMapDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Verb {

    private String id;

    @JsonDeserialize(using = LangMapDeserializer.class)
    private LangMap display;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LangMap getDisplay() {
        return display;
    }

    public void setDisplay(LangMap display) {
        this.display = display;
    }
    
}
