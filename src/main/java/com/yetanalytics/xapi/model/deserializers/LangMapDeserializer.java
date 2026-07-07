package com.yetanalytics.xapi.model.deserializers;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.yetanalytics.xapi.exception.XApiModelException;
import com.yetanalytics.xapi.model.LangMap;
import com.yetanalytics.xapi.model.LangTag;
import com.yetanalytics.xapi.util.Mapper;

/**
* Custom deserializer for the Language Map Wrapper
*/
public class LangMapDeserializer extends StdDeserializer<LangMap> {
    
    public LangMapDeserializer() {
        this(null);
    }

    public LangMapDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public LangMap deserialize(final JsonParser jp, final DeserializationContext context) {
        try {
            ObjectMapper mapper = Mapper.getMapper();
            TypeReference<HashMap<LangTag, String>> typeRef 
                = new TypeReference<HashMap<LangTag, String>>() {};
            
            return new LangMap(mapper.readValue(jp, typeRef));
        } catch (IOException e) {
            throw new XApiModelException(
                "Could not deserialize LangMap", e);
        }
    }
}
