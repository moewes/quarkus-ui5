package io.quarkiverse.ui5.manifest.datasources;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class DatasourceSettings {

    private final List<String> annotations = Arrays.asList("annotation");
    private final String odataVersion = "4.0";
}
