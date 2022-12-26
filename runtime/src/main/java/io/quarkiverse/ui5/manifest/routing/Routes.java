package io.quarkiverse.ui5.manifest.routing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;

@Builder
public class Routes {

    private List<Route> routes;

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {

        Map<String, Object> result = new HashMap<>();
        result.put("routes", routes);

        return result;
    }
}
