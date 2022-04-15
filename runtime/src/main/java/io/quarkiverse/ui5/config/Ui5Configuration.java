package io.quarkiverse.ui5.config;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import lombok.Getter;

@ConfigRoot(phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED)
@Getter
public class Ui5Configuration {

    ThemeConfig theme;

    DevConfig dev;

}
