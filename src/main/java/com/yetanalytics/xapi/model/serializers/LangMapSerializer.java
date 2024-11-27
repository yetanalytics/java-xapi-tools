package com.yetanalytics.xapi.model.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.yetanalytics.xapi.model.LangMap;
import com.yetanalytics.xapi.util.Mapper;

/**
* Custom serializer for the Language Map Wrapper
*/
public class LangMapSerializer extends StdSerializer<LangMap> {
    
    public LangMapSerializer() {
        this(null);
    }
  
    public LangMapSerializer(Class<LangMap> t) {
        super(t);
    }

    @Override
    public void serialize(LangMap langMap, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        Mapper.getMapper().writeValue(gen, langMap.getMap());
    }
}
