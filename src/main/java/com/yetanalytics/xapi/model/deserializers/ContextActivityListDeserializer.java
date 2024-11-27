package com.yetanalytics.xapi.model.deserializers;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.yetanalytics.xapi.model.Activity;
import com.yetanalytics.xapi.util.Mapper;

/**
* Custom deserializer for the Activity Lists in ContextActivity.
*/
public class ContextActivityListDeserializer extends StdDeserializer<List<Activity>> {
    public ContextActivityListDeserializer() {
        this(null);
    }

    public ContextActivityListDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public List<Activity> deserialize(final JsonParser jp, final DeserializationContext context) {
        try {
            ObjectMapper mapper = Mapper.getMapper();
            JsonNode node = mapper.readTree(jp);
            //Sometimes a contextactivity list is actually a single entry, but
            //should be converted to a list
            if (node instanceof ArrayNode){
                TypeReference<List<Activity>> typeRef = new TypeReference<List<Activity>>() {};
                return mapper.convertValue(node, typeRef);
            } else {
                ArrayList<Activity> ctxActList = new ArrayList<Activity>();
                ctxActList.add(mapper.convertValue(node, Activity.class));
                return ctxActList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
