package io.quarkiverse.ui5.manifest.datasources;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;

@Builder
public class Datasources {

    @Builder.Default
    private Map<String, Datasource> datasources = new HashMap<>();
    // TODO Annotation

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {

        Map<String, Object> result = new HashMap<>();
        for (String key : datasources.keySet()) {
            result.put(key, datasources.get(key));
        }
        result.put("annotation", new Annotation());
        return result;
    }

}
