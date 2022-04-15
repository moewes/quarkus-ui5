package io.quarkiverse.ui5.cards.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkiverse.ui5.cards.common.CardAction;

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
        List<CardAction> actions = Arrays.asList(CardAction.builder().build());

        ListItem item = ListItem.builder()
                .title(title)
                .description(description)
                .actions(actions)
                .build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertEquals(title, attributesForJson.get("title"));
        assertEquals(description, attributesForJson.get("description"));
        assertEquals(actions, attributesForJson.get("actions"));
    }

}
