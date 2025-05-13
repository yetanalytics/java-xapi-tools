package com.yetanalytics.xapi.model.deserializers;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yetanalytics.xapi.exception.XApiModelException;
import com.yetanalytics.xapi.model.ObjectType;
import com.yetanalytics.xapi.util.Mapper;

/**
* Custom deserializer for ObjectType Enum
*/
public class ObjectTypeDeserializer extends StdDeserializer<ObjectType> {
    
    public ObjectTypeDeserializer() {
        this(null);
    }

    public ObjectTypeDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public ObjectType deserialize(final JsonParser jp, final DeserializationContext context) {
        try {
            ObjectMapper mapper = Mapper.getMapper();
            return ObjectType.getByDisplayName(mapper.readValue(jp, String.class));
        } catch (IOException e) {
            throw new XApiModelException(
                "Could not deserialize ObjectType", e);
        }
    }
}
