package io.quarkiverse.ui5.cards.manifest;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import io.quarkiverse.ui5.cards.common.CardContent;
import io.quarkiverse.ui5.cards.common.CardData;
import io.quarkiverse.ui5.cards.common.CardHeader;
import lombok.Getter;
import lombok.Setter;

@Setter
public class Ui5CardManifestCard {

    private Type type = Type.LIST; // TODO
    private CardHeader header;

    private CardContent content;
    private CardData data;

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = new HashMap<>();

        result.put("type", type.text);
        if (header != null) {
            result.put("header", header);
        }
        result.put("content", content);
        if (data != null) {
            result.put("data", data);
        }
        return result;
    }

    public enum Type {
        LIST("List"),
        OBJECT("Object"),
        TABLE("Table"),
        CALENDER("Calender"),
        TIMELINE("Timeline");
        // Adaptiv
        // Component
        // WebPage

        @Getter
        private final String text;

        Type(String text) {
            this.text = text;
        }
    }
}
