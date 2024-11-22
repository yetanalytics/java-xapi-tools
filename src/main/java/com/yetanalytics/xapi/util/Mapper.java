package com.yetanalytics.xapi.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Mapper {

    private static ObjectMapper INSTANCE;
    
    private Mapper() {}
    
    public synchronized static ObjectMapper getMapper() {
        if(INSTANCE == null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            mapper.setSerializationInclusion(Include.NON_NULL);
            INSTANCE = mapper;
        }
        return INSTANCE;
    }
}