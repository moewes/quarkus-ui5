package io.quarkiverse.ui5.cards.timeline;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TimelineItemTest {

    @Test
    public void defaultValues() {

        TimelineItem item = TimelineItem.builder().build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertNotNull(attributesForJson.get("title"));
    }

    @Test
    public void allValues() {

        String title = "title";
        String description = "description";
        LocalDateTime dateTime = LocalDateTime.now();
        String owner = "owner";
        String ownerImage = "ownerImage";

        TimelineItem item = TimelineItem.builder()
                .title(title)
                .description(description)
                .dateTime(dateTime)
                .owner(owner)
                .ownerImage(ownerImage)
                .build();

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertEquals(((Map<String, Object>) attributesForJson.get("title")).get("value"), title);
        assertEquals(((Map<String, Object>) attributesForJson.get("description")).get("value"),
                description);
        assertEquals(attributesForJson.get("dateTime"), dateTime);
        assertEquals(attributesForJson.get("owner"), owner);
        assertEquals(attributesForJson.get("ownerImage"), ownerImage);

    }

    @Test
    public void allSetters() {
        String title = "title";
        String description = "description";
        LocalDateTime dateTime = LocalDateTime.now();
        String owner = "owner";
        String ownerImage = "ownerImage";

        TimelineItem item = TimelineItem.builder().build();
        item.setTitle(title);
        item.setDescription(description);
        item.setDateTime(dateTime);
        item.setOwner(owner);
        item.setOwnerImage(ownerImage);

        Map<String, Object> attributesForJson = item.getAttributesForJson();

        assertEquals(((Map<String, Object>) attributesForJson.get("title")).get("value"), title);
        assertEquals(((Map<String, Object>) attributesForJson.get("description")).get("value"),
                description);
        assertEquals(attributesForJson.get("dateTime"), dateTime);
        assertEquals(attributesForJson.get("owner"), owner);
        assertEquals(attributesForJson.get("ownerImage"), ownerImage);
    }

}
