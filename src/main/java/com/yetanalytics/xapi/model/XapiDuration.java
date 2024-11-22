package com.yetanalytics.xapi.model;

import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class XapiDuration {

    @JsonCreator 
    public XapiDuration(String duration){
        this.original = duration;
        this.duration = Duration.parse(duration);
    }

    private String original;

    private Duration duration;

    @JsonValue
    public String getOriginal() {
        return original;
    }

    public Duration getValue() {
        return duration;
    }

}
