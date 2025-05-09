package com.yetanalytics.xapi.model.deserializers;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import com.yetanalytics.xapi.model.Group;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yetanalytics.xapi.exception.XApiModelException;
import com.yetanalytics.xapi.model.AbstractActor;
import com.yetanalytics.xapi.model.Agent;
import com.yetanalytics.xapi.model.ObjectType;
import com.yetanalytics.xapi.util.Mapper;

/**
* Custom deserializer for xAPI Actors. Determines their type based on
* component properties.
*/
public class AbstractActorDeserializer extends StdDeserializer<AbstractActor> {
    public AbstractActorDeserializer() {
        this(null);
    }

    public AbstractActorDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public AbstractActor deserialize(final JsonParser jp, final DeserializationContext context) {
        try {
            ObjectMapper mapper = Mapper.getMapper();
            JsonNode node;
            node = mapper.readTree(jp);
            String objType = node.has("objectType") ? 
                node.get("objectType").asText() : null;
            Class<? extends AbstractActor> instanceClass = 
                ObjectType.GROUP.matches(objType) ? Group.class : Agent.class;
            return mapper.convertValue(node, instanceClass);
        } catch (IOException e) {
            throw new XApiModelException(
                "Could not deserialize AbstractActor", e);
        }
    }
}
