package com.yetanalytics.xapi.model;

import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
* Class representation of <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#46-iso-8601-durations">ISO 8601 Duration</a> 
* allowing for retrieval of the original String.
*/
@JsonInclude(Include.NON_NULL)
public class XapiDuration {

    /**
    * This constructor takes an 8601 formatted String and converts it to an
    * XapiDuration object consisting of java.time.Duration object and the 
    * original String.
    * @param duration The duration field value from an xAPI Statement.
    */
    @JsonCreator 
    public XapiDuration(String duration){
        this.original = duration;
        this.duration = Duration.parse(duration);
    }

    private String original;

    private Duration duration;

    /**
     * Returns the original String version of the Duration
     * @return Duration as a String
     */
    @JsonValue
    public String getOriginal() {
        return original;
    }

    /** 
     * Returns the java.time.Duration version of the Duration
     * @return The Duration
     */
    public Duration getValue() {
        return duration;
    }

}
