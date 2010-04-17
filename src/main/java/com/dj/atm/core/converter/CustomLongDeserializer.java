package com.dj.atm.core.converter;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;

/**
 * Deserializer for Long types.
 * <p/>
 * Difference with provided Jackson Long de-serializer is that this
 * one assumes empty values as valid before converting the same to 0L.
 *
 * @author Script Runner
 * @since 0.0.1
 */
public class CustomLongDeserializer extends JsonDeserializer<Long> {
    private static final String EMPTY_STRING = "";

    @Override
    public Long deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String valueString = jp.getText();
        if (valueString == null || valueString.trim().equals(EMPTY_STRING)) {
            return Long.valueOf(0L);
        }
        return Long.valueOf(valueString);
    }
}
