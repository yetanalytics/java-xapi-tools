package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yetanalytics.xapi.model.deserializers.AbstractObjectDeserializer;

/**
* Abstract Class for serialization and deserialization of xAPI Objects
*/
@JsonDeserialize(using = AbstractObjectDeserializer.class)
public class AbstractObject {

    private ObjectType objectType;

    public ObjectType getObjectType() {
        return objectType;
    }
    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }

}
