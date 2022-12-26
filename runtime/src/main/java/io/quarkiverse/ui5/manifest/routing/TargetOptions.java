package io.quarkiverse.ui5.manifest.routing;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class TargetOptions {

    private Map<String, Object> settings = new HashMap<>();
}
