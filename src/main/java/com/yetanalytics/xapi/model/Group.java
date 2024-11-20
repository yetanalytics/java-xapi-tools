package com.yetanalytics.xapi.model;

import java.util.List;

public class Group extends AbstractActor {

    private List<Agent> member;

    public List<Agent> getMember() {
        return member;
    }

    public void setMember(List<Agent> member) {
        this.member = member;
    }

    
}
