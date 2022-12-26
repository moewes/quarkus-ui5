package io.quarkiverse.ui5.manifest.routing;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class Target {

    private String id;
    @Builder.Default
    private String type = "Component";
    private String name;
    private TargetOptions options;

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {

        Map<String, Object> result = new HashMap<>();
        if (id != null) {
            result.put("id", id);
        }
        if (name != null) {
            result.put("name", name);
        }
        if (type != null) {
            result.put("type", type);
        }
        if (options != null) {
            result.put("options", options);
        }
        return result;
    }
}
