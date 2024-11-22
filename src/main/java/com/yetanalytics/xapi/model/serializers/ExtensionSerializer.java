package com.yetanalytics.xapi.model.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.yetanalytics.xapi.model.Extensions;
import com.yetanalytics.xapi.util.Mapper;

public class ExtensionSerializer extends StdSerializer<Extensions> {
    
    public ExtensionSerializer() {
        this(null);
    }
  
    public ExtensionSerializer(Class<Extensions> t) {
        super(t);
    }

    @Override
    public void serialize(Extensions exts, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        Mapper.getMapper().writeValue(gen, exts.getMap());
    }
}