package com.yetanalytics.xapi.model.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.yetanalytics.xapi.model.InteractionType;
import com.yetanalytics.xapi.util.Mapper;

/**
* Custom deserializer for InteractionType Enum
*/
public class InteractionTypeDeserializer extends StdDeserializer<InteractionType> {
    
    public InteractionTypeDeserializer() {
        this(null);
    }

    public InteractionTypeDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public InteractionType deserialize(final JsonParser jp, final DeserializationContext context) throws IOException {
        ObjectMapper mapper = Mapper.getMapper();
        String intType = mapper.readValue(jp, String.class);
        return InteractionType.getByDisplayName(intType);
    }
}
