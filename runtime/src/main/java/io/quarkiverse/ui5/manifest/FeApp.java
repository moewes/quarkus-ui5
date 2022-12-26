package io.quarkiverse.ui5.manifest;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import io.quarkiverse.ui5.manifest.datasources.Datasources;
import lombok.Builder;

@Builder
public class FeApp {

    private String id;
    @Builder.Default
    private String type = "application";
    // TODO i18n
    // applicationVersion
    private String title;
    // describtion

    private Datasources datasources;
    @Builder.Default
    private boolean offline = false;
    // ressources
    // sourceTemplate

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {

        Map<String, Object> result = new HashMap<>();
        if (id != null) {
            result.put("id", id);
        }
        if (type != null) {
            result.put("type", type);
        }
        if (title != null) {
            result.put("title", title);
        }
        result.put("dataSources", datasources);
        return result;
    }
}
