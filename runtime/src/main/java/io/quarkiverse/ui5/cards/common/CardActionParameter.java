package io.quarkiverse.ui5.cards.common;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;

@Builder
public class CardActionParameter {

    @Builder.Default
    private String url = "";
    private String target;

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = new HashMap<>();

        result.put("url", url);
        if (target != null) {
            result.put("target", target);
        }
        return result;
    }
}
