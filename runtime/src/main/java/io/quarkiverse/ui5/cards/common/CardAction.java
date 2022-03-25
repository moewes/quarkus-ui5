package io.quarkiverse.ui5.cards.common;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
public class CardAction {

    @Builder.Default
    private CardActionType type = CardActionType.NAVIGATION;
    private CardActionParameter parameters;
    private String enabled;

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = new HashMap<>();

        result.put("type", type.getText());
        if (enabled != null) {
            result.put("enabled", enabled);
        }
        if (parameters != null) {
            result.put("parameters", parameters);
        }

        return result;
    }

    @AllArgsConstructor
    public enum CardActionType {
        NAVIGATION("Navigation"),
        SUBMIT("Submit"),
        CUSTOM("Custom"),
        DATE_CHANGE("DateChange"),
        MONTH_CHANGE("MonthChange");

        @Getter
        private final String text;
    }
}
