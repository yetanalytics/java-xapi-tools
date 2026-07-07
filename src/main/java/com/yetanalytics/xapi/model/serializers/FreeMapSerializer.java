package com.yetanalytics.xapi.model.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.yetanalytics.xapi.model.IFreeMap;
import com.yetanalytics.xapi.util.Mapper;

public class FreeMapSerializer<K, V> extends StdSerializer<IFreeMap<K, V>> {
    public FreeMapSerializer() {
        this(null);
    }

    public FreeMapSerializer(Class<IFreeMap<K, V>> t) {
        super(t);
    }

    @Override
    public void serialize(IFreeMap<K, V> map, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        Mapper.getMapper().writeValue(gen, map.getMap());
    }
}
