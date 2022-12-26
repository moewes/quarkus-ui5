package io.quarkiverse.ui5.deployment;

import io.quarkus.builder.item.MultiBuildItem;

public final class ElementsAppBuildItem extends MultiBuildItem {

    final String name;
    final String className;

    public ElementsAppBuildItem(String name, String className) {
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
