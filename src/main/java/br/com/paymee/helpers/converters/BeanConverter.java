package br.com.paymee.helpers.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class BeanConverter {

    public static String toJSONString(Object classx) {
        try {
            return new ObjectMapper().writeValueAsString(classx);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJSON(String data, Class<T> classx) {
        try {
            return new ObjectMapper().readValue(data, classx);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
