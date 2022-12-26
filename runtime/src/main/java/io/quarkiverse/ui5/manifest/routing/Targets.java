package io.quarkiverse.ui5.manifest.routing;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;

@Builder
public class Targets {

    private Map<String, Target> targets;

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {

        Map<String, Object> result = new HashMap<>();
        for (String key : targets.keySet()) {
            result.put(key, targets.get(key));
        }
        return result;
    }
}
