package com.dj.atm.core.converter;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ScriptRunner
 * Date: Mar 20, 2010
 * Time: 4:51:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomLongDeserializer extends JsonDeserializer<Long>{
    private static final String EMPTY_STRING = "";

    @Override
    public Long deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String valueString = jp.getText();
        if(valueString==null || valueString.trim().equals(EMPTY_STRING)){
            return Long.valueOf(0L);
        }
        return Long.valueOf(valueString);
    }
}
