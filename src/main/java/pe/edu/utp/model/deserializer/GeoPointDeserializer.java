package pe.edu.utp.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.cloud.firestore.GeoPoint;

import java.io.IOException;

public class GeoPointDeserializer extends JsonDeserializer<GeoPoint> {
    @Override
    public GeoPoint deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // Leer el objeto JSON que representa las coordenadas
        double latitude = 0.0;
        double longitude = 0.0;

        // Suponiendo que el JSON tiene un formato como: {"latitude": ..., "longitude": ...}
        p.nextToken(); // Avanza al primer token
        while (p.nextToken() != null) {
            String fieldName = p.getCurrentName();
            p.nextToken(); // Avanza al valor
            if ("latitude".equals(fieldName)) {
                latitude = p.getDoubleValue();
            } else if ("longitude".equals(fieldName)) {
                longitude = p.getDoubleValue();
            }
        }

        return new GeoPoint(latitude, longitude);
    }
}
