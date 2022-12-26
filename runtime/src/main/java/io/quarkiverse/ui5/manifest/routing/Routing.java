package io.quarkiverse.ui5.manifest.routing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class Routing {

    private List<Route> routes;
    private Targets targets;

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {

        Map<String, Object> result = new HashMap<>();
        if (routes != null) {
            result.put("routes", routes);
        }
        if (targets != null) {
            result.put("targets", targets);
        }
        return result;
    }
}
