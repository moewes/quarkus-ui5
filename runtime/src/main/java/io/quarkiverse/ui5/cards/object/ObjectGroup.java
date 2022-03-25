package io.quarkiverse.ui5.cards.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class ObjectGroup {

    private String title;
    @Builder.Default
    private List<ObjectGroupItem> items = new ArrayList<>();
    @Builder.Default
    private boolean visible = true;
    // private String alignment = "Default"; // TODO ENUM

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = new HashMap<>();
        result.put("items", items);
        if (title != null) {
            result.put("title", title);
        }
        if (!visible) {
            result.put("visible", false);
        }
        // TODO  alignment

        return result;
    }
}
