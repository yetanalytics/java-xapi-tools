package com.yetanalytics.xapi.model.serializers;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.yetanalytics.xapi.util.Mapper;

/**
* Custom serializer for ZonedDateTime
*/
public class DateTimeSerializer extends StdSerializer<ZonedDateTime> {
    
    public DateTimeSerializer() {
        this(null);
    }
  
    public DateTimeSerializer(Class<ZonedDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(ZonedDateTime zdt, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        Mapper.getMapper().writeValue(gen, zdt.format(DateTimeFormatter.ISO_INSTANT));
    }
}
