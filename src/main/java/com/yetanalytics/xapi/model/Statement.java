package com.yetanalytics.xapi.model;

import java.util.UUID;
import java.time.ZonedDateTime;

public class Statement {
    
    private UUID id;
    
    private AbstractActor actor;

    private Verb verb;
    
    private AbstractActor authority;
    
    private ZonedDateTime timestamp;
    
    private ZonedDateTime stored;
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    
    public AbstractActor getActor() {
        return actor;
    }
    public void setActor(AbstractActor actor) {
        this.actor = actor;
    }

    public Verb getVerb() {
        return verb;
    }
    public void setVerb(Verb verb) {
        this.verb = verb;
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
