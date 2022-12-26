package io.quarkiverse.ui5.manifest;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;

@Builder
public class Ui5Manifest {

    @Builder.Default
    private String version = "1.32.0";
    private FeApp app;
    private FeUi5 ui5;

    @Builder.Default
    private FeFiori fiori = new FeFiori();

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {

        Map<String, Object> result = new HashMap<>();
        result.put("_version", version);
        if (app != null) {
            result.put("sap.app", app);
        }
        if (ui5 != null) {
            result.put("sap.ui5", ui5);
        }
        result.put("sap.fiori", fiori);
        return result;
    }
}
