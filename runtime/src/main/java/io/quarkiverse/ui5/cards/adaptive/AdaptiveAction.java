package io.quarkiverse.ui5.cards.adaptive;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class AdaptiveAction {

    private String type;

    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = new HashMap<>();
        result.put("type", getType());
        return result;
    }
}
