package com.yetanalytics.xapi.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.yetanalytics.xapi.model.deserializers.ExtensionDeserializer;
import com.yetanalytics.xapi.model.serializers.ExtensionSerializer;
import com.yetanalytics.xapi.util.Mapper;

@JsonDeserialize(using = ExtensionDeserializer.class)
@JsonSerialize(using = ExtensionSerializer.class)
public class Extensions {

    private Map<String,Object> extMap = new HashMap<String,Object>();

    public Extensions(Map<String, Object> input) {
        extMap = input;
    }

    public void put(String key, Object value) {
        extMap.put(key, value);
    }

    public Object get(String key) {
        return extMap.get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T read(String key, String jsonPathExpression, Class<T> typeKey) {
        try {
            Object jsonObject = extMap.get(key);
            if (jsonObject == null) return null;
            String json = Mapper.getMapper().writeValueAsString(jsonObject);
            return (T) JsonPath.read(json, jsonPathExpression);
        } catch (PathNotFoundException e) {
            //TODO: logging framework
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(String key) {
        extMap.remove(key);
    }

    public Set<String> getKeys() {
        return extMap.keySet();
    }

    public Map<String, Object> getMap() {
        return extMap;
    }
}
