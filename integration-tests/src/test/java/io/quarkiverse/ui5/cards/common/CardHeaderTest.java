package io.quarkiverse.ui5.cards.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;

class CardHeaderTest {

    @Test
    public void defaultValues() {
        CardHeader cardHeader = CardHeader.builder().build();
        Map<String, Object> attributesForJson = cardHeader.getAttributesForJson();
        assertNotNull(attributesForJson.get("title"));
    }

    @Test
    public void allValues() {

        String title = "title";
        String subTitle = "subTitle";

        CardHeader cardHeader = CardHeader.builder().title(title).subTitle(subTitle).build();

        Map<String, Object> attributesForJson = cardHeader.getAttributesForJson();
        assertEquals(title, attributesForJson.get("title"));
        assertEquals(subTitle, attributesForJson.get("subTitle"));
    }

    @Test
    public void allSetters() {

        String title = "title";
        String subTitle = "subTitle";

        CardHeader cardHeader = CardHeader.builder().build();
        cardHeader.setTitle(title);
        cardHeader.setSubTitle(subTitle);
        Map<String, Object> attributesForJson = cardHeader.getAttributesForJson();

        assertEquals(title, attributesForJson.get("title"));
        assertEquals(subTitle, attributesForJson.get("subTitle"));
    }
}
