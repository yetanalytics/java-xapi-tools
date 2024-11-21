package com.yetanalytics.xapi.model;

import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.ZonedDateTime;

@JsonDeserialize
public class Statement extends AbstractObject {
    
    private UUID id;
    
    private AbstractObject actor;

    private Verb verb;

    private AbstractObject object;
    
    private AbstractActor authority;
    
    private ZonedDateTime timestamp;
    
    private ZonedDateTime stored;
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    
    public AbstractObject getActor() {
        return actor;
    }
    public void setActor(AbstractObject actor) {
        this.actor = actor;
    }

    public Verb getVerb() {
        return verb;
    }
    public void setVerb(Verb verb) {
        this.verb = verb;
    }

    public AbstractObject getObject() {
        return object;
    }
    public void setObject(AbstractObject object) {
        this.object = object;
    }
    
    public AbstractActor getAuthority() {
        return authority;
    }
    public void setAuthority(AbstractActor authority) {
        this.authority = authority;
    }
    
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public ZonedDateTime getStored() {
        return stored;
    }
    public void setStored(ZonedDateTime stored) {
        this.stored = stored;
    }
}
