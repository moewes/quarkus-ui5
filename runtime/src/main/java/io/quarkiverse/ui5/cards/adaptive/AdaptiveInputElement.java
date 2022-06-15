package io.quarkiverse.ui5.cards.adaptive;

import java.util.Map;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class AdaptiveInputElement extends AdaptiveElement {

    protected String label;
    protected String id;

    @Override
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = super.getAttributesForJson();
        if (id != null) {
            result.put("id", id);
        }
        if (label != null) {
            result.put("label", label);
        }
        return result;
    }
}
