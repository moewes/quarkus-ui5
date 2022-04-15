package io.quarkiverse.ui5.config;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

@ConfigGroup
public class ThemeConfig {

    /**
     * xxx
     */
    @ConfigItem(defaultValue = "abc") // FIXME
    String name;
}
