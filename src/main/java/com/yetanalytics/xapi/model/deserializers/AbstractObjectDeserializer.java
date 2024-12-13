package com.yetanalytics.xapi.model.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.yetanalytics.xapi.model.AbstractObject;
import com.yetanalytics.xapi.model.Activity;
import com.yetanalytics.xapi.model.Agent;
import com.yetanalytics.xapi.model.Group;
import com.yetanalytics.xapi.model.ObjectType;
import com.yetanalytics.xapi.model.Statement;
import com.yetanalytics.xapi.model.StatementRef;
import com.yetanalytics.xapi.util.Mapper;

/**
* Custom deserializer for xAPI Objects. Determines their type based on
* component properties.
*/
public class AbstractObjectDeserializer extends StdDeserializer<AbstractObject> {

    public AbstractObjectDeserializer() {
        this(null);
    }

    public AbstractObjectDeserializer(final Class<?> vc) {
        super(vc);
    }

    private Class<? extends AbstractObject> getObjectType(JsonNode n) {
        String objType = n.has("objectType") ? n.get("objectType").asText() : null;
        boolean hasActor = n.has("actor");
        boolean hasId = n.has("id");

        if (ObjectType.GROUP.toString().equals(objType)){
            return Group.class;
        } else if (ObjectType.STATEMENT_REF.matches(objType)) {
            return StatementRef.class;
        } else if (ObjectType.AGENT.matches(objType) || (objType == null && !hasId)){
            return Agent.class;
        } else if (ObjectType.SUB_STATEMENT.matches(objType) 
                || (objType == null && hasActor)) {
            return Statement.class;
        } else {
            return Activity.class;
        }
    }

    @Override
    public AbstractObject deserialize(final JsonParser jp, final DeserializationContext context) throws IOException {
        JsonNode node = jp.readValueAsTree();
        return Mapper.getMapper().convertValue(node, getObjectType(node));
    }
}
