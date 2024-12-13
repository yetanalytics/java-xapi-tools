package com.yetanalytics.xapi.model.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.yetanalytics.xapi.model.AbstractActor;
import com.yetanalytics.xapi.model.Agent;
import com.yetanalytics.xapi.model.Group;
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
    public AbstractActor deserialize(final JsonParser jp, final DeserializationContext context) throws IOException {
        ObjectMapper mapper = Mapper.getMapper();
        JsonNode node = mapper.readTree(jp);
        String objType = node.has("objectType") ? node.get("objectType").asText() : null;
        Class<? extends AbstractActor> instanceClass = 
            ObjectType.GROUP.matches(objType) ? Group.class : Agent.class;
        return mapper.convertValue(node, instanceClass);
    }
}
