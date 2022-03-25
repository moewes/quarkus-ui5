package io.quarkiverse.ui5.cards.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;

class DataRequestTest {

    @Test
    public void defaultValues() {

        DataRequest item = DataRequest.builder().build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertNotNull(attributesForJson);
        assertNotNull(attributesForJson.get("url"));
    }

    @Test
    public void allValues() {

        String url = "/url";
        DataRequest.Mode mode = DataRequest.Mode.NO_CORS;
        DataRequest.Method method = DataRequest.Method.DELETE;
        Integer retryAfter = 5;
        Boolean withCredentials = true;

        DataRequest item = DataRequest.builder()
                .url(url)
                .mode(mode)
                .method(method)
                .retryAfter(retryAfter)
                .withCredentials(withCredentials).build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertEquals(url, attributesForJson.get("url"));
        assertEquals(mode.getText(), attributesForJson.get("mode"));
        assertEquals(method.name(), attributesForJson.get("method"));
        assertEquals(retryAfter, attributesForJson.get("retryAfter"));
        assertEquals(withCredentials, attributesForJson.get("withCredentials"));
    }

    @Test
    public void allSetters() {

        String url = "/url";
        DataRequest.Mode mode = DataRequest.Mode.NO_CORS;
        DataRequest.Method method = DataRequest.Method.DELETE;
        Integer retryAfter = 5;
        Boolean withCredentials = true;

        DataRequest item = DataRequest.builder().build();
        item.setUrl(url);
        item.setMode(mode);
        item.setMethod(method);
        item.setRetryAfter(retryAfter);
        item.setWithCredentials(withCredentials);

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertEquals(url, attributesForJson.get("url"));
        assertEquals(mode.getText(), attributesForJson.get("mode"));
        assertEquals(method.name(), attributesForJson.get("method"));
        assertEquals(retryAfter, attributesForJson.get("retryAfter"));
        assertEquals(withCredentials, attributesForJson.get("withCredentials"));
    }

}
