package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yetanalytics.xapi.model.deserializers.InteractionTypeDeserializer;

@JsonDeserialize(using = InteractionTypeDeserializer.class)
public enum InteractionType {
    TRUE_FALSE("true-false"), 
    CHOICE("choice"), 
    FILL_IN("fill-in"), 
    LONG_FILL_IN("long-fill-in"), 
    MATCHING("matching"), 
    PERFORMANCE("performance"), 
    SEQUENCING("sequencing"), 
    LIKERT("likert"), 
    NUMERIC("numeric"), 
    OTHER("other");

    private String displayName;

    InteractionType(String displayName) {
        this.displayName = displayName;
    }

    @Override 
    public String toString() { return displayName; }

    public static InteractionType getByDisplayName(String name) {
        for(InteractionType t : values()){
            if(t.toString().equals(name)){
                return t;
            }
        }
        throw new IllegalArgumentException("Bad InteractionType Value");
    }

    public boolean matches(String match) {
        return this.toString().equals(match);
    }
}