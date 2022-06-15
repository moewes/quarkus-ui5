package io.quarkiverse.ui5.cards.adaptive;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ActionHandler {
    private String url;
    private String mode; // TODO Enum
    private String method; // TODO Enum
    // parameter?
    //private headers
    // private withCredials // TODO

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = new HashMap<>();

        if (url != null) {
            result.put("url", url);
        }
        if (mode != null) {
            result.put("mode", mode);
        }
        if (method != null) {
            result.put("method", method);
        }
        return result;
    }
}
