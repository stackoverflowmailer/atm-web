package com.dj.atm.core.converter;

//~--- non-JDK imports --------------------------------------------------------

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

//~--- JDK imports ------------------------------------------------------------

import java.io.IOException;

/**
 * Deserializer for Long types.
 * <p/>
 * Difference with provided Jackson Long de-serializer is that this
 * one converts empty values to 0L.
 *
 * @author Script Runner
 * @since 0.0.1
 */
public class CustomLongDeserializer extends JsonDeserializer<Long> {
    @Override
    public Long deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return deserializeInternal(jp, ctxt);
    }

    private Long deserializeInternal(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        String valueString = jp.getText().trim();

        if ((null == valueString) || (0 == valueString.length())) {
            return Long.valueOf(0);
        }

        return Long.valueOf(valueString);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
