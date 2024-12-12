package com.yetanalytics.xapi.model;

import java.util.HashMap;
import java.util.IllformedLocaleException;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yetanalytics.xapi.model.deserializers.LangMapDeserializer;
import com.yetanalytics.xapi.model.serializers.FreeMapSerializer;

/**
 * Java wrapper object for the 
 * <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#42-language-maps">xAPI Language Map object</a>.
 * The Language Map is a dictionary where the keys are 
 * <a href="http://tools.ietf.org/html/rfc5646">RFC 5646 Language Tags</a> and
 * the value is a String in the language specified by the tag.
 */
@JsonDeserialize(using = LangMapDeserializer.class)
@JsonSerialize(using = FreeMapSerializer.class)
public class LangMap implements IFreeMap<LangTag, String> {

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
     * @param languageCode the RFC 5646 LangTag of the specified Language
     * @param value a string in the language specified by languageCode
     */
    @Override
    public void put(LangTag languageCode, String value) {
        languageHashMap.put(languageCode, value);
    }

    /**
     * Sets an entry in the Language Map
     * @param languageCode the RFC 5646 language tag String of the specified language
     * @param value a string in the language specified by languageCode
     * @throws IllformedLocaleException
     */
    @Override
    public void put(String languageCode, String value) throws IllformedLocaleException {
        put(LangTag.parse(languageCode), value);
    }

    /**
     * Retrieve the value for a specific language.
     * @param languageCode RFC 5646 LangTag
     * @return The value in the language specified by the tag.
     */
    @Override
    public String get(LangTag languageCode) {
        return languageHashMap.get(languageCode);
    }

    /**
     * Retrieve the value for a specific language.
     * @param languageCode RFC 5646 language tag String
     * @return The value in the language specified by the tag
     * @throws IllformedLocaleException
     */
    @Override
    public String get(String languageCode) throws IllformedLocaleException {
        return get(LangTag.parse(languageCode));
    }

    /**
     * Remove an entry from the Language Map
     * @param languageCode RFC 5646 LangTag
     */
    @Override
    public void remove(LangTag languageCode) {
        languageHashMap.remove(languageCode);
    }

    /**
     * Remove an entry from the Language Map
     * @param languageCode RFC 5646 language tag String
     * @throws IllformedLocaleException
     */
    @Override
    public void remove(String languageCode) throws IllformedLocaleException {
        remove(LangTag.parse(languageCode));
    }

    /**
     * Retrieves the full set of RFC 5646 Language Tags contained in the Map
     * @return A set of RFC 5646 LangTag instances
     */
    @Override
    public Set<LangTag> getKeys() {
        return languageHashMap.keySet();
    }

    /**
     * Retrieves the full Language Map in the form of a HashMap&lt;String, String&gt;
     * @return The full Language Map
     */
    @Override
    public Map<LangTag, String> getMap() {
        return languageHashMap;
    }
}
