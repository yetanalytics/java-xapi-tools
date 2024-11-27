package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yetanalytics.xapi.model.deserializers.ObjectTypeDeserializer;

/**
* Enumeration representing all Object Types in the
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md">9274.1.1 xAPI Specification</a>.
*/
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

    /**
     * @return Display name of the ObjectType (used in JSON/Serialization)
     */
    @Override
    @JsonValue
    public String toString() { return displayName; }

    /**
     * @param name display name to look up correct ObjectType
     * @return Appropriate ObjectType for the displayName
     */
    public static ObjectType getByDisplayName(String name) {
        for(ObjectType t : values()){
            if(t.toString().equals(name)){
                return t;
            }
        }
        throw new IllegalArgumentException("Bad ObjectType Value");
    }

    /**
     * Helper method for testing string equivalence to an ObjectType
     * @param match string to test for ObjectType equivalence
     * @return boolean representing equivalence
     */
    public boolean matches(String match) {
        return this.toString().equals(match);
    }
}
