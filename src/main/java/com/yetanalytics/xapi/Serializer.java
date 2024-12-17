package com.yetanalytics.xapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yetanalytics.xapi.util.Mapper;

public class Serializer {
    private final ObjectMapper mapper;
    private final Logger logger;

    public Serializer() {
        mapper = Mapper.getMapper();
        logger = LoggerFactory.getLogger(Serializer.class);
    }

    public <T> String serialize(T data) {
        try {
            return mapper.writeValueAsString(data);   
        } catch (JsonProcessingException e) {
            logger.warn("Cannot write JSON data!");
            logger.warn(e.getOriginalMessage());
            return null;
        }
    }
}
