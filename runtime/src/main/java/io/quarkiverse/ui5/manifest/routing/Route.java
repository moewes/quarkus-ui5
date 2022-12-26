package io.quarkiverse.ui5.manifest.routing;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class Route {

    private String name;
    private String pattern;
    private String target;

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {

        Map<String, Object> result = new HashMap<>();
        if (name != null) {
            result.put("name", name);
        }
        if (target != null) {
            result.put("target", target);
        }
        if (pattern != null) {
            result.put("pattern", pattern);
        }
        return result;
    }
}
