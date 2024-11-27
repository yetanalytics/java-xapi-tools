package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
* Class representation of the Result component of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#245-result">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
public class Result {
    
    private Score score;
    private Boolean success;
    private Boolean completion;
    private String response;
    private XapiDuration duration;
    private Extensions extensions;
    
    public Score getScore() {
        return score;
    }
    public void setScore(Score score) {
        this.score = score;
    }
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public Boolean getCompletion() {
        return completion;
    }
    public void setCompletion(Boolean completion) {
        this.completion = completion;
    }
    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }
    public XapiDuration getDuration() {
        return duration;
    }
    public void setDuration(XapiDuration duration) {
        this.duration = duration;
    }
    public Extensions getExtensions() {
        return extensions;
    }
    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }
}
