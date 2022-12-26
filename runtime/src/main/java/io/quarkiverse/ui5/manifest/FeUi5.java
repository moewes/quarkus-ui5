package io.quarkiverse.ui5.manifest;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import io.quarkiverse.ui5.manifest.models.Models;
import io.quarkiverse.ui5.manifest.routing.Routing;
import lombok.Builder;

@Builder
public class FeUi5 {

    private Routing routing;

    private Models models;

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {

        Map<String, Object> result = new HashMap<>();
        if (routing != null) {
            result.put("routing", routing);
        }
        if (models != null) {
            result.put("models", models);
        }
        return result;
    }
}
