package com.yetanalytics.xapi.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yetanalytics.xapi.model.deserializers.LangMapDeserializer;
import com.yetanalytics.xapi.model.serializers.LangMapSerializer;

/**
 * Java wrapper object for the 
 * <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#42-language-maps">xAPI Language Map object</a>.
 * The Language Map is a dictionary where the keys are 
 * <a href="http://tools.ietf.org/html/rfc5646">RFC 5646 Language Tags</a> and
 * the value is a String in the language specified by the tag.
 */
@JsonDeserialize(using = LangMapDeserializer.class)
@JsonSerialize(using = LangMapSerializer.class)
public class LangMap {

    private HashMap<LangTag,String> languageHashMap = new HashMap<>();

    /**
     * Create a new langmap from a HashMap
     * @param input a HashMap of RFC5646 Language Tags, and corresponding value strings
     */
    public LangMap(HashMap<LangTag, String> input) {
        languageHashMap = input;
    }

    /**
     * Sets an entry in the Language Map
     * @param languageCode the RFC 5646 Language Tag of the specified Language
     * @param value a string in the language specified by languageCode
     */
    public void put(LangTag languageCode, String value) {
        languageHashMap.put(languageCode, value);
    }

    /**
     * Retrieve the value for a specific language.
     * @param languageCode RFC 5646 Language Tag
     * @return The value in the language specified by the tag.
     */
    public String get(LangTag languageCode) {
        return languageHashMap.get(languageCode);
    }

    /**
     * Remove an Entry from the Language Map
     * @param languageCode RFC 5646 Language Tag
     */
    public void remove(LangTag languageCode) {
        languageHashMap.remove(languageCode);
    }

    /**
     * Retrieves the full set of RFC 5646 Language Tags contained in the Map
     * @return A set RFC 5646 Language Tag Strings
     */
    public Set<LangTag> getLanguageCodes() {
        return languageHashMap.keySet();
    }

    /**
     * Retrieves the full Language Map in the form of a HashMap&lt;String, String&gt;
     * @return The full Language Map
     */
    public Map<LangTag, String> getMap() {
        return languageHashMap;
    }
}
