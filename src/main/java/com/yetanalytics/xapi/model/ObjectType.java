package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yetanalytics.xapi.model.deserializers.ObjectTypeDeserializer;

@JsonDeserialize(using = ObjectTypeDeserializer.class)
public enum ObjectType {
    STATEMENT_REF("StatementRef"),
    ACTIVITY("Activity"),
    SUB_STATEMENT("SubStatement"),
    AGENT("Agent"),
    GROUP("Group");

    private String displayName;

    ObjectType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    @JsonValue
    public String toString() { return displayName; }

    public static ObjectType getByDisplayName(String name) {
        for(ObjectType t : values()){
            if(t.toString().equals(name)){
                return t;
            }
        }
        throw new IllegalArgumentException("Bad ObjectType Value");
    }

    public boolean matches(String match) {
        return this.toString().equals(match);
    }
}