package com.yetanalytics.xapi.model.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.yetanalytics.xapi.util.Mapper;

import jakarta.activation.MimeType;
import jakarta.activation.MimeTypeParseException;

/**
* Custom serializer for the Jakart Activation MIME types.
*/
public class MimeTypeDeserializer extends StdDeserializer<MimeType> {
    
    public MimeTypeDeserializer() {
        this(null);
    }

    public MimeTypeDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public MimeType deserialize(final JsonParser jp, final DeserializationContext context) {
        try {
            ObjectMapper mapper = Mapper.getMapper();
            return new MimeType(mapper.readValue(jp, String.class));
        } catch (MimeTypeParseException e) {
            // Need special case since MimeTypeParseException does not
            // extend RuntimeException, so it angers the compiler.
            throw new IllegalArgumentException(e);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
