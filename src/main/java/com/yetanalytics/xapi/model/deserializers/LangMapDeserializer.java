package com.yetanalytics.xapi.model.deserializers;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.yetanalytics.xapi.model.LangMap;
import com.yetanalytics.xapi.util.Mapper;

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
            TypeReference<HashMap<String,String>> typeRef 
                = new TypeReference<HashMap<String,String>>() {};
            
            return new LangMap(mapper.readValue(jp, typeRef));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
