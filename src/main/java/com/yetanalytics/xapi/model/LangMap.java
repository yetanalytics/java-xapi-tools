package com.yetanalytics.xapi.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yetanalytics.xapi.model.deserializers.LangMapDeserializer;
import com.yetanalytics.xapi.model.serializers.LangMapSerializer;

@JsonDeserialize(using = LangMapDeserializer.class)
@JsonSerialize(using = LangMapSerializer.class)
public class LangMap {

    private HashMap<String,String> languageHashMap = new HashMap<String, String>();

    public LangMap(HashMap<String,String> input) {
        languageHashMap = input;
    }

    public void put(String languageCode, String value) {
        languageHashMap.put(languageCode, value);
    }

    public String get(String languageCode) {
        return languageHashMap.get(languageCode);
    }

    public void remove(String languageCode) {
        languageHashMap.remove(languageCode);
    }

    public Set<String> getLanguageCodes() {
        return languageHashMap.keySet();
    }

    public Map<String, String> getMap() {
        return languageHashMap;
    }
}
