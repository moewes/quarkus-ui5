package io.quarkiverse.ui5.cards.list;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ListItemTest {

    @Test
    public void defaultValue() {

        ListItem item = ListItem.builder().build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertNotNull(attributesForJson);
    }

    @Test
    public void allValues() {

        String title = "title";
        String description = "description";
        ;
        ListItem item = ListItem.builder()
                .title(title)
                .description(description)
                .build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertEquals(title, attributesForJson.get("title"));
        assertEquals(description, attributesForJson.get("description"));
    }

}