package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yetanalytics.xapi.model.deserializers.InteractionTypeDeserializer;

/**
* Enumeration representing all Interaction Types in the
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#interaction-types">9274.1.1 xAPI Specification</a>.
*/
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

    /**
     * @return Display name of the InteractionType (used in JSON/Serialization)
     */
    @Override
    @JsonValue
    public String toString() { return displayName; }

    /**
     * Retrieves the InteractionType for a given displayName
     * @param name displayName to look up correct InteractionType
     * @return Appropriate InteractionType for the displayName
     */
    public static InteractionType getByDisplayName(String name) {
        for(InteractionType t : values()){
            if(t.toString().equals(name)){
                return t;
            }
        }
        throw new IllegalArgumentException("Bad InteractionType Value");
    }

    /**
     * Helper method for testing string equivalence to an InteractionType
     * @param match string to test for InteractionType equivalence
     * @return boolean representing equivalence
     */
    public boolean matches(String match) {
        return this.toString().equals(match);
    }
}
