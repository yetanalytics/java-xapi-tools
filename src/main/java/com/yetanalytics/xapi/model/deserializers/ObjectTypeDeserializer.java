package com.yetanalytics.xapi.model.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
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
    public ObjectType deserialize(final JsonParser jp, final DeserializationContext context) throws IOException {
        ObjectMapper mapper = Mapper.getMapper();
        String objType = mapper.readValue(jp, String.class);
        return ObjectType.getByDisplayName(objType);
    }
}
