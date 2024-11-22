package com.yetanalytics.xapi.model.deserializers;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.yetanalytics.xapi.model.Extensions;

public class ExtensionDeserializer extends StdDeserializer<Extensions> {
    
    public ExtensionDeserializer() {
        this(null);
    }

    public ExtensionDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Extensions deserialize(final JsonParser jp, final DeserializationContext context) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<HashMap<String, Object>> typeRef 
                = new TypeReference<HashMap<String,Object>>() {};

            JsonNode node = mapper.readTree(jp);

            
            return new Extensions(mapper.convertValue(node, typeRef));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

