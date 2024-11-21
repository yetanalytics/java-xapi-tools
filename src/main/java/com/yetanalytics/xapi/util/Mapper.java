package com.yetanalytics.xapi.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {

    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper;
    }
    
}
