package com.yetanalytics.xapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;

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

    @JsonIgnore
    @AssertTrue
    public boolean isAnonymousOrIdentifiedGroup() {
        return (
            (countIFIs() == 0 && member != null) ||
            (countIFIs() == 1)
        );
    }
    
    private boolean isValidConsumer(Agent consumer) {
        return consumer.getAccount() != null;
    }

    @Override
    @JsonIgnore
    public boolean isValidAuthority() {
        return (
            member.size() == 2 &&
            (
                isValidConsumer(member.get(0)) ||
                isValidConsumer(member.get(1))
            )
        );
    }

    @Override
    @JsonIgnore
    @AssertFalse
    public boolean isEmpty() {
        // zero-length member arrays still count as non-empty
        return super.isEmpty() && member == null;
    }
}
