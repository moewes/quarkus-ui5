package io.quarkiverse.ui5.cards.common;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CardDataTest {

    @Test
    public void defaultValues() {

        CardData item = CardData.builder().build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertNotNull(attributesForJson);
    }

    @Test
    public void allValues() {

        Object json = "json";
        String name = "name";
        String path = " path";
        Integer updateInterval = 5;
        DataRequest request = DataRequest.builder().build();

        CardData item = CardData.builder()
                .json(json)
                .name(name)
                .path(path)
                .updateInterval(updateInterval)
                .request(request)
                .build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertEquals(attributesForJson.get("json"), json);
        assertEquals(attributesForJson.get("name"), name);
        assertEquals(attributesForJson.get("path"), path);
        assertEquals(attributesForJson.get("updateInterval"), updateInterval);
        assertEquals(attributesForJson.get("request"), request);

    }

    @Test
    public void allSetters() {
        Object json = "json";
        String name = "name";
        String path = " path";
        Integer updateInterval = 5;
        DataRequest request = DataRequest.builder().build();

        CardData item = CardData.builder().build();

        item.setJson(json);
        item.setName(name);
        item.setPath(path);
        item.setUpdateInterval(updateInterval);
        item.setRequest(request);

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertEquals(attributesForJson.get("json"), json);
        assertEquals(attributesForJson.get("name"), name);
        assertEquals(attributesForJson.get("path"), path);
        assertEquals(attributesForJson.get("updateInterval"), updateInterval);
        assertEquals(attributesForJson.get("request"), request);
    }
}
