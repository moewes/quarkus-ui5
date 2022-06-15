package io.quarkiverse.ui5.cards.adaptive;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class NumberInputElement extends AdaptiveInputElement {

    //private String style;

    @Builder.Default
    private Number value = 0;
    @Builder.Default
    private Number min = 0;
    @Builder.Default
    private Number max = 100;

    @Override
    public String getType() {
        return "Input.Number";
    }

    @JsonAnyGetter
    @Override
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = super.getAttributesForJson();

        result.put("value", value);
        result.put("min", min);
        result.put("max", max);
        return result;
    }
}
