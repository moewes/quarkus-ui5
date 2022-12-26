package io.quarkiverse.ui5.manifest.datasources;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;

@Builder
public class Datasource {

    private String uri;
    @Builder.Default
    private String type = "OData";
    // Settings

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {

        Map<String, Object> result = new HashMap<>();
        if (uri != null) {
            result.put("uri", uri);
        }
        if (type != null) {
            result.put("type", type);
        }
        result.put("settings", new DatasourceSettings());

        return result;
    }
}
