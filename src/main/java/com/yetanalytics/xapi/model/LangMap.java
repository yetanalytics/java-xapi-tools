package com.yetanalytics.xapi.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yetanalytics.xapi.model.deserializers.LangMapDeserializer;
import com.yetanalytics.xapi.model.serializers.LangMapSerializer;

/**
 * Java wrapper object for the xAPI Language Map object.
 * The Language Map is a dictionary where the keys are 
 * <a href="http://tools.ietf.org/html/rfc5646">RFC 5646 Language Tags</a> and
 * the value is a String in the language specified by the tag.
 */
@JsonDeserialize(using = LangMapDeserializer.class)
@JsonSerialize(using = LangMapSerializer.class)
public class LangMap {

    private HashMap<String,String> languageHashMap = new HashMap<String, String>();

    /**
     * Create a new langmap from a HashMap
     * @param input a HashMap of RFC5646 Language Tags, and corresponding value strings
     */
    public LangMap(HashMap<String,String> input) {
        languageHashMap = input;
    }

    /**
     * Sets an entry in the Language Map
     * @param languageCode the RFC 5646 Language Tag of the specified Language
     * @param value a string in the language specified by languageCode
     */
    public void put(String languageCode, String value) {
        languageHashMap.put(languageCode, value);
    }

    /**
     * Retrieve the value for a specific language.
     * @param languageCode RFC 5646 Language Tag
     * @return The value in the language specified by the tag.
     */
    public String get(String languageCode) {
        return languageHashMap.get(languageCode);
    }

    /**
     * Remove an Entry from the Language Map
     * @param languageCode RFC 5646 Language Tag
     */
    public void remove(String languageCode) {
        languageHashMap.remove(languageCode);
    }

    /**
     * @return A set of RFC 5646 Language Tags contained in the Map
     */
    public Set<String> getLanguageCodes() {
        return languageHashMap.keySet();
    }

    /**
     * @return The full Language Map in the form of a HashMap
     */
    public Map<String, String> getMap() {
        return languageHashMap;
    }
}
