package io.quarkiverse.ui5.manifest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class FeFiori {

    private final String version = "1.1.0";
    private final List<String> registrationIds = new ArrayList<>();
    private final String archeType = "transactional";

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {

        Map<String, Object> result = new HashMap<>();
        result.put("_version", version);
        result.put("registrationIds", registrationIds);
        result.put("archeType", archeType);

        return result;
    }
}
