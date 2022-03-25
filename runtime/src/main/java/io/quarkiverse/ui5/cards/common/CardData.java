package io.quarkiverse.ui5.cards.common;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Builder;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Builder
public class CardData {

    private DataRequest request;
    private Object json;
    // extension // TODO
    private String name;
    private String path;
    private Integer updateInterval;

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = new HashMap<>();

        if (json != null) {
            result.put("json", json);
        }
        if (updateInterval != null) {
            result.put("updateInterval", updateInterval);
        }
        if (name != null) {
            result.put("name", name);
        }
        if (path != null) {
            result.put("path", path);
        }
        if (request != null) {
            result.put("request", request);
        }
        return result;
    }
}
