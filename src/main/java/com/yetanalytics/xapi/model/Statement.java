package com.yetanalytics.xapi.model;

import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yetanalytics.xapi.model.serializers.DateTimeSerializer;

import java.time.ZonedDateTime;

@JsonDeserialize
public class Statement extends AbstractObject {
    
    private UUID id;
    
    private AbstractObject actor;

    private Verb verb;

    private Result result;

    private Context context;

    private AbstractObject object;
    
    private AbstractActor authority;
    
    @JsonSerialize(using = DateTimeSerializer.class)
    private ZonedDateTime timestamp;
    
    @JsonSerialize(using = DateTimeSerializer.class)
    private ZonedDateTime stored;

    private String version;
    
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

    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
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
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
}
