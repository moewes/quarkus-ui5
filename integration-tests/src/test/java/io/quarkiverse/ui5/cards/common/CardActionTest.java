package io.quarkiverse.ui5.cards.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;

class CardActionTest {

    @Test
    public void defaultValues() {
        CardAction item = CardAction.builder().build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertNotNull(attributesForJson);
        assertNotNull(attributesForJson.get("type"));
    }

    @Test
    public void allValues() {

        CardAction.CardActionType type = CardAction.CardActionType.SUBMIT;
        String enabled = "{...}";
        CardActionParameter parameter = CardActionParameter.builder().build();

        CardAction item = CardAction.builder().type(type).enabled(enabled).parameters(parameter).build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertEquals(type.getText(), attributesForJson.get("type"));
        assertEquals(enabled, attributesForJson.get("enabled"));
        assertEquals(parameter, attributesForJson.get("parameters"));
    }

    @Test
    public void allSetters() {

        CardAction.CardActionType type = CardAction.CardActionType.SUBMIT;
        String enabled = "{...}";
        CardActionParameter parameter = CardActionParameter.builder().build();

        CardAction item = CardAction.builder().build();
        item.setType(type);
        item.setEnabled(enabled);
        item.setParameters(parameter);

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertEquals(type.getText(), attributesForJson.get("type"));
        assertEquals(enabled, attributesForJson.get("enabled"));
        assertEquals(parameter, attributesForJson.get("parameters"));

    }

}
