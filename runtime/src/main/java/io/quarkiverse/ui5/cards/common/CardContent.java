package io.quarkiverse.ui5.cards.common;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class CardContent {

    private final Map<String, Object> content = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {
        return content;
    }

    public void add(String key, Object item) {
        content.put(key, item);
    }
}
