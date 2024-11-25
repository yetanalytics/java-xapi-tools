package com.yetanalytics.xapi.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
