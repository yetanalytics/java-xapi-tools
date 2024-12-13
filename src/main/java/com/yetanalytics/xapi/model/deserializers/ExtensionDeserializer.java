package com.yetanalytics.xapi.model.deserializers;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.yetanalytics.xapi.model.Extensions;
import com.yetanalytics.xapi.util.Mapper;

/**
* Custom deserializer for the Extension Map Wrapper
*/
public class ExtensionDeserializer extends StdDeserializer<Extensions> {
    
    public ExtensionDeserializer() {
        this(null);
    }

    public ExtensionDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Extensions deserialize(final JsonParser jp, final DeserializationContext context) throws IOException {
        TypeReference<HashMap<URI, Object>> typeRef 
            = new TypeReference<HashMap<URI,Object>>() {};

        JsonNode node = Mapper.getMapper().readTree(jp);

        return new Extensions(Mapper.getMapper().convertValue(node, typeRef));
    }
}
