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

/**
 * A wrapper object for using <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#41-extensions">xAPI Extensions</a>.
 * 
 * The extension JSON data is stored in a combination of LinkedHashMap and 
 * ArrayList depending on the JSON elements. It can be accessed directly
 * or through a JSONPath API.
 */
@JsonDeserialize(using = ExtensionDeserializer.class)
@JsonSerialize(using = ExtensionSerializer.class)
public class Extensions {

    private Map<String,Object> extMap = new HashMap<>();

    public Extensions(Map<String, Object> input) {
        extMap = input;
    }

    /**
     * Sets an entry in the Extensions Map
     * @param key the IRI key of the extension
     * @param value The Collections API representation of the JSON Data
     */
    public void put(String key, Object value) {
        extMap.put(key, value);
    }

    /**
     * Retrieve extension data
     * @param key The IRI of the extension
     * @return The Collections API representation of the JSON Data
     */
    public Object get(String key) {
        return extMap.get(key);
    }

    /**
     * Attempt a JSONPath query of the Extension data.
     * @param key The IRI key of the extension in which to perform the query
     * @param jsonPathExpression A JSONPath query to perform in the Extension data
     * @param typeKey The typereference for the type that the query is expecting to retrieve
     * @param <T> The type that the query is expecting to convert the results to
     * @return Object of type T that is the result of deserialization from the query
     */
    @SuppressWarnings("unchecked")
    public <T> T read(String key, String jsonPathExpression, Class<T> typeKey) {
        try {
            Object jsonObject = extMap.get(key);
            if (jsonObject == null) return null;
            String json = Mapper.getMapper().writeValueAsString(jsonObject);
            return (T) JsonPath.read(json, jsonPathExpression);
        } catch (PathNotFoundException e) {
            //TODO: logging framework
            System.err.println("Path not found");
            // e.printStackTrace();
        } catch (JsonProcessingException e) {
            System.err.println("JSON cannot be processed");
            // e.printStackTrace();
        }
        return null;
    }

    /**
     * Remove an extension by IRI key
     * @param key the IRI of the extension to remove
     */
    public void remove(String key) {
        extMap.remove(key);
    }

    /**
     * Returns a set of all IRI Extension keys
     * @return Set of IRI keys
     */
    public Set<String> getKeys() {
        return extMap.keySet();
    }
    /**
     * Returns the full raw Extension Map as a HashMap&lt;String, Object&gt;
     * @return The raw Extensions Map
     */
    public Map<String, Object> getMap() {
        return extMap;
    }
}
