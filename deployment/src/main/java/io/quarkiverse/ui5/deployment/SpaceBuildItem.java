package io.quarkiverse.ui5.deployment;

import io.quarkus.builder.item.MultiBuildItem;

public final class SpaceBuildItem extends MultiBuildItem {

    final String name;
    final String className;

    public SpaceBuildItem(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }
}
