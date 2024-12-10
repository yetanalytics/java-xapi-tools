package com.yetanalytics.xapi.model;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yetanalytics.xapi.model.deserializers.LangMapDeserializer;

/**
* Class representation of the Verb component of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#243-verb">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
public class Verb {

    private URI id;

    @JsonDeserialize(using = LangMapDeserializer.class)
    private LangMap display;

    public URI getId() {
        return id;
    }

    public void setId(URI id) {
        this.id = id;
    }

    public LangMap getDisplay() {
        return display;
    }

    public void setDisplay(LangMap display) {
        this.display = display;
    }
    
}
