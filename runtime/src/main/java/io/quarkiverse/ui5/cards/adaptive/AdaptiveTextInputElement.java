package io.quarkiverse.ui5.cards.adaptive;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class AdaptiveTextInputElement extends AdaptiveInputElement {

    private String style;

    @Override
    public String getType() {
        return "Input.Text";
    }

    @JsonAnyGetter
    @Override
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = super.getAttributesForJson();

        return result;
    }
}
