package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;

/**
* Class representation of the Account Component of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#2424-account-object">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
public class Account implements JSONObject {

    @NotNull
    private URI homePage;
    
    @NotNull
    private String name;
    
    public URI getHomePage() {
        return homePage;
    }
    public void setHomePage(URI homePage) {
        this.homePage = homePage;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @JsonIgnore
    @AssertFalse(message = "Account must not be empty")
    public boolean isEmpty() {
        return homePage == null && name == null;
    }
}
