package com.yetanalytics.xapi.model.deserializers;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yetanalytics.xapi.model.InteractionType;
import com.yetanalytics.xapi.util.Mapper;

public class InteractionTypeDeserializer extends StdDeserializer<InteractionType> {
    
    public InteractionTypeDeserializer() {
        this(null);
    }

    public InteractionTypeDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public InteractionType deserialize(final JsonParser jp, final DeserializationContext context) {
        try {
            ObjectMapper mapper = Mapper.getMapper();
            return InteractionType.getByDisplayName(mapper.readValue(jp, String.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


