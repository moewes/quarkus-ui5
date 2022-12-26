package io.quarkiverse.ui5.config;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;
import lombok.Getter;

@ConfigGroup
public class DevConfig {

    /**
     * yyy
     */
    @ConfigItem(defaultValue = "false")
    @Getter
    boolean allCardsPage;

    /**
     * xxxx
     */
    @ConfigItem(defaultValue = "https://ui5.sap.com")
    String path;
}
