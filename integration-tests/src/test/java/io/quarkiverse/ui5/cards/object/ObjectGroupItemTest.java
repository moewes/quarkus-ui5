package io.quarkiverse.ui5.cards.object;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.wildfly.common.Assert.assertNotNull;

class ObjectGroupItemTest {

    @Test
    public void defaultValues() {

        ObjectGroupItem item = ObjectGroupItem.builder().build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertNotNull(attributesForJson.get("value"));
        assertNull(attributesForJson.get("visible"));
        assertNull(attributesForJson.get("maxLines"));
    }

    @Test
    public void allValues() {

        String label = "label";
        String details = "details";
        String value = "value";
        ObjectGroupItem.Type type = ObjectGroupItem.Type.DEFAULT;
        int maxLines = 5;
        boolean visible = false;

        ObjectGroupItem item = ObjectGroupItem.builder()
                .label(label)
                .details(details)
                .value(value)
                .type(type)
                .maxLines(maxLines)
                .visible(visible)
                .build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertEquals(attributesForJson.get("label"), label);
        assertEquals(attributesForJson.get("details"),
                details);
        assertEquals(attributesForJson.get("value"), value);
        assertEquals(attributesForJson.get("type"), type.getText());
        assertEquals(attributesForJson.get("maxLines"), maxLines);
        assertEquals(attributesForJson.get("visible"), visible);
    }

    @Test
    public void allSetters() {
        String label = "label";
        String details = "details";
        String value = "value";
        ObjectGroupItem.Type type = ObjectGroupItem.Type.DEFAULT;
        int maxLines = 5;
        boolean visible = false;

        ObjectGroupItem item = ObjectGroupItem.builder().build();
        item.setLabel(label);
        item.setDetails(details);
        item.setValue(value);
        item.setType(type);
        item.setMaxLines(maxLines);
        item.setVisible(visible);

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertEquals(attributesForJson.get("label"), label);
        assertEquals(attributesForJson.get("details"),
                details);
        assertEquals(attributesForJson.get("value"), value);
        assertEquals(attributesForJson.get("type"), type.getText());
        assertEquals(attributesForJson.get("maxLines"), maxLines);
        assertEquals(attributesForJson.get("visible"), visible);
    }

}