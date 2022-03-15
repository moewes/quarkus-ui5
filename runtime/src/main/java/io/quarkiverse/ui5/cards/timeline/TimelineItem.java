package io.quarkiverse.ui5.cards.timeline;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class TimelineItem {

    @SuppressWarnings("UnusedAssignment")
    @Builder.Default
    private String title = "TimelineItem";

    private String description;
    private LocalDateTime dateTime;
    private String owner;
    private String ownerImage;
    //private Icon icon; // TODO
    //private List<Action> actions; // TODO

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = new HashMap<>();
        result.put("title", getMapWithValue(title));
        if (dateTime != null) {
            result.put("dateTime", dateTime);
        }
        if (description != null) {
            result.put("description", getMapWithValue(description));
        }
        if (owner != null) {
            result.put("owner", owner);
        }
        if (ownerImage != null) {
            result.put("ownerImage", ownerImage);
        }
        return result;
    }

    private Map<String, Object> getMapWithValue(Object value) {
        Map<String, Object> result = new HashMap<>();
        result.put("value", value);
        return result;
    }
}
