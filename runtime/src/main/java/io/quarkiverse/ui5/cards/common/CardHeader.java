package io.quarkiverse.ui5.cards.common;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class CardHeader {

    @Builder.Default
    private String title = "Title";
    private String subTitle;
    // TODO ICON
    // TODO ACTIONS

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = new HashMap<>();

        result.put("title", title);
        if (subTitle != null) {
            result.put("subTitle", subTitle);
        }
        return result;
    }
}
