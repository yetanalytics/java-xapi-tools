package com.yetanalytics.xapi;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yetanalytics.xapi.util.Mapper;

public class Deserializer {

    private final ObjectMapper mapper;
    private final Logger logger;

    public Deserializer() {
        mapper = Mapper.getMapper();
        logger = LoggerFactory.getLogger(Serializer.class);
    }

    public <T> T deserialize(File file, Class<T> clazz) {
        try {
            return mapper.readValue(file, clazz);
        } catch (JsonProcessingException e) {
            logger.warn("Cannot parse JSON data!");
            logger.warn(e.getOriginalMessage());
            return null;
        } catch (IOException e) {
            logger.error("Cannot read JSON file!");
            logger.error(e.getMessage());
            return null;
        }
    }
}
