package io.quarkiverse.ui5.manifest.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MainModel {

    private String dataSource;
    @Builder.Default
    private boolean preload = true;
    @Builder.Default
    private MainModelSettings settings = new MainModelSettings();

}
