package com.yetanalytics.xapi.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
* Class representation of the Group Component of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#2422-when-the-actor-objecttype-is-group">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
@JsonDeserialize
public class Group extends AbstractActor {

    private List<Agent> member;

    public List<Agent> getMember() {
        return member;
    }

    public void setMember(List<Agent> member) {
        this.member = member;
    }

    
}
