package com.yetanalytics.xapi.model.serializers;

import java.io.IOException;

import org.semver4j.Semver;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.yetanalytics.xapi.util.Mapper;

/**
* Custom serializer for Semver4j Semver class.
*/
public class SemverSerializer extends StdSerializer<Semver> {
    public SemverSerializer() {
        this(null);
    }

    public SemverSerializer(Class<Semver> t) {
        super(t);
    }

    @Override
    public void serialize(Semver ver, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        Mapper.getMapper().writeValue(gen, ver.getVersion());
    }
}
