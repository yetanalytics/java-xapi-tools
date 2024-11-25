package com.yetanalytics.xapi.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.JsonNodeFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class Mapper {

    private static ObjectMapper INSTANCE;
    
    private Mapper() {}
    
    public synchronized static ObjectMapper getMapper() {
        if(INSTANCE == null) {
            ObjectMapper mapper = new ObjectMapper();

            //Add JSR310 Module for java.time support
            mapper.registerModule(new JavaTimeModule());

            //Decimals
            mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
            mapper.configure(JsonNodeFeature.STRIP_TRAILING_BIGDECIMAL_ZEROES, false);
            INSTANCE = mapper;
        }
        return INSTANCE;
    }
}
