package io.quarkiverse.ui5.manifest.models;

import lombok.Getter;

@Getter
public class MainModelSettings {

    private String synchronizationMode = "None";
    private String operationMode = "Server";
    private boolean autoExpandSelect = true;
    private boolean earlyRequests = true;
}
