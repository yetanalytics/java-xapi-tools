package com.yetanalytics.xapi.model;

import java.net.URI;
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

    private Map<URI, Object> extMap = new HashMap<>();

    public Extensions(Map<URI, Object> input) {
        extMap = input;
    }

    /**
     * Sets an entry in the Extensions Map
     * @param key the URI key of the extension
     * @param value The Collections API representation of the JSON Data
     */
    public void put(URI key, Object value) {
        extMap.put(key, value);
    }

    /**
     * Sets an entry in the Extensions Map
     * @param key the IRI String key of the extension
     * @param value The Collections API representation of the JSON Data
     * @throws IllegalArgumentException
     */
    public void put(String key, Object value) throws IllegalArgumentException {
        put(URI.create(key), value);
    }

    /**
     * Retrieve extension data
     * @param key The URI key of the extension
     * @return The Collections API representation of the JSON Data
     */
    public Object get(URI key) {
        return extMap.get(key);
    }

    /**
     * Retrieve extension data
     * @param key The IRI string key of the extension
     * @return The Collections API representation of the JSON Data
     * @throws IllegalArgumentException
     */
    public Object get(String key) throws IllegalArgumentException {
        return get(URI.create(key));
    }

    /**
     * Attempt a JSONPath query of the Extension data.
     * @param key The URI key of the extension in which to perform the query
     * @param jsonPathExpression A JSONPath query to perform in the Extension data
     * @param typeKey The typereference for the type that the query is expecting to retrieve
     * @param <T> The type that the query is expecting to convert the results to
     * @return Object of type T that is the result of deserialization from the query
     */
    @SuppressWarnings("unchecked")
    public <T> T read(URI key, String jsonPathExpression, Class<T> typeKey) {
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

    /**
     * Attempt a JSONPath query of the Extension data.
     * @param key The IRI String key of the extension in which to perform the query
     * @param jsonPathExpression A JSONPath query to perform in the Extension data
     * @param typeKey The typereference for the type that the query is expecting to retrieve
     * @param <T> The type that the query is expecting to convert the results to
     * @return Object of type T that is the result of deserialization from the query
     * @throws IllegalArgumentException
     */
    public <T> T read(String key, String jsonPathExpression, Class<T> typeKey) throws IllegalArgumentException {
        return read(URI.create(key), jsonPathExpression, typeKey);
    }

    /**
     * Remove an extension by IRI key
     * @param key the URI key of the extension to remove
     */
    public void remove(URI key) {
        extMap.remove(key);
    }

    /**
     * Remove an extension by IRI key
     * @param key the IRI String key of the extension to remove
     * @throws IllegalArgumentException
     */
    public void remove(String key) throws IllegalArgumentException {
        remove(URI.create(key));        
    }

    /**
     * Returns a set of all IRI Extension keys
     * @return Set of IRI keys
     */
    public Set<URI> getKeys() {
        return extMap.keySet();
    }
    /**
     * Returns the full raw Extension Map as a HashMap&lt;URI, Object&gt;
     * @return The raw Extensions Map
     */
    public Map<URI, Object> getMap() {
        return extMap;
    }
}
