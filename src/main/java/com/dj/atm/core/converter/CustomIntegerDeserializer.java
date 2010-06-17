package com.dj.atm.core.converter;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;

/**
 * @author Script Runner
 */
public class CustomIntegerDeserializer extends JsonDeserializer<Integer> {


    public Integer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return deserializeInternal(jp, ctxt);
    }


    private Integer deserializeInternal(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String valueString = jp.getText().trim();
        if (null == valueString || 0 == valueString.length()) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(valueString);
    }
}

