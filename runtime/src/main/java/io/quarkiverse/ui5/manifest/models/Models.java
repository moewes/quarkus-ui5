package io.quarkiverse.ui5.manifest.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;

@Builder
public class Models {

    private MainModel mainModel;
    // TODO i18l

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {

        Map<String, Object> result = new HashMap<>();
        if (mainModel != null) {
            result.put("", mainModel);
        }
        return result;
    }
}
