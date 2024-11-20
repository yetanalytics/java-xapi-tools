package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yetanalytics.xapi.model.deserializers.ActorObjectTypeDeserializer;

@JsonDeserialize(using = ActorObjectTypeDeserializer.class)
public enum ActorObjectType {
    AGENT("Agent"),
    GROUP("Group");

    private String displayName;

    ActorObjectType(String displayName) {
        this.displayName = displayName;
    }

    @Override 
    public String toString() { return displayName; }

    public static ActorObjectType getByDisplayName(String name) {
        for(ActorObjectType t : values()){
            if(t.toString().equals(name)){
                return t;
            }
        }
        throw new IllegalArgumentException("Bad ObjectType Value");
    }
}
