package com.yetanalytics.xapi.model.deserializers;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yetanalytics.xapi.model.ActorObjectType;

public class ActorObjectTypeDeserializer extends StdDeserializer<ActorObjectType> {
    
    public ActorObjectTypeDeserializer() {
        this(null);
    }

    public ActorObjectTypeDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public ActorObjectType deserialize(final JsonParser jp, final DeserializationContext context) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return ActorObjectType.getByDisplayName(mapper.readValue(jp, String.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
