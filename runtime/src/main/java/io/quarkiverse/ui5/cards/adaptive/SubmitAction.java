package io.quarkiverse.ui5.cards.adaptive;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class SubmitAction extends AdaptiveAction {

    private String title;

    @Override
    public String getType() {
        return "Action.Submit";
    }

    @JsonAnyGetter
    @Override
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = super.getAttributesForJson();

        if (title != null) {
            result.put("title", title);
        }
        return result;
    }
}
