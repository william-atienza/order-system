package org.project.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public enum Mapper {
    INSTANCE;

    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
    public String toString(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
