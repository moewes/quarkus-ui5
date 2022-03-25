package io.quarkiverse.ui5.cards.object;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Builder
public class ObjectGroupItem {

    @Builder.Default
    private Type type = Type.DEFAULT;
    //private Icon icon; // TODO
    private String label;
    @Builder.Default
    private boolean visible = true;
    private String value;
    // Actions // TOOO
    private int maxLines;
    // mainIndicator // TODO
    // sideIndicators
    // sideIndicatorsAlignment
    private String details;
    // state // TODO

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = new HashMap<>();

        result.put("type", type.text);
        if (label != null) {
            result.put("label", label);
        }
        if (value != null) {
            result.put("value", value);
        } else {
            result.put("value", "");
        }
        if (!visible) {
            result.put("visible", false);
        }
        if (maxLines > 0) {
            result.put("maxLines", maxLines);
        }
        if (details != null) {
            result.put("details", details);
        }

        return result;
    }

    public enum Type {
        DEFAULT("Default"),
        NUMERIC_DATA("NumericData"),
        STATUS("Status");

        @Getter
        private final String text;

        Type(String aDefault) {
            this.text = aDefault;
        }
    }
}
