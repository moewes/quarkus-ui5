package io.quarkiverse.ui5.cards.object;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ObjectGroupTest {

    @Test
    public void defaultValues() {

        ObjectGroup item = ObjectGroup.builder().build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertNotNull(attributesForJson.get("items"));
        assertNull(attributesForJson.get("visible"));
    }

    @Test
    public void allValues() {

        String title = "title";
        List<ObjectGroupItem> items = new ArrayList<>();
        String alignment = "alignment";
        boolean visible = false;

        ObjectGroup item = ObjectGroup.builder()
                .title(title)
                .items(items)
                //  .alignment(alignment)
                .visible(visible)
                .build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertEquals(attributesForJson.get("title"), title);
        assertEquals(attributesForJson.get("items"), items);
        //  assertEquals(attributesForJson.get("alignment"), alignment);
        assertEquals(attributesForJson.get("visible"), visible);
    }

    @Test
    public void allSetters() {
        String title = "title";
        List<ObjectGroupItem> items = new ArrayList<>();
        String alignment = "alignment";
        boolean visible = false;

        ObjectGroup item = ObjectGroup.builder().build();
        item.setTitle(title);
        item.setItems(items);
        // item.setAlignment(alignment);
        item.setVisible(visible);

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertEquals(attributesForJson.get("title"), title);
        assertEquals(attributesForJson.get("items"), items);
        // assertEquals(attributesForJson.get("alignment"), alignment);
        assertEquals(attributesForJson.get("visible"), visible);
    }
}
