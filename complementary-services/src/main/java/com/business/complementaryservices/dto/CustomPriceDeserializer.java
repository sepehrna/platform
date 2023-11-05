package com.business.complementaryservices.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class CustomPriceDeserializer extends JsonDeserializer<Float> {

    @Override
    public Float deserialize(JsonParser p, DeserializationContext context) throws IOException {
        if (p.currentToken().isNumeric()) {
            return p.getFloatValue();
        } else if (p.currentToken().isScalarValue()) {
            // Convert the String to Double
            String stringValue = p.getText();
            try {
                return Float.parseFloat(stringValue);
            } catch (NumberFormatException e) {
                throw new IOException("Failed to parse 'price' value: " + stringValue, e);
            }
        }
        throw new IOException("Unexpected token: " + p.currentToken());
    }
}
