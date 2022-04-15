package io.quarkiverse.ui5.it;

import io.quarkiverse.ui5.annotations.UiSpace;
import io.quarkiverse.ui5.spaces.SpaceContentProvider;
import io.quarkiverse.ui5.spaces.SpaceParameterConsumer;
import io.vertx.core.MultiMap;

@UiSpace("Test")
public class Ui5TestSpace implements SpaceParameterConsumer, SpaceContentProvider {

    private String param;

    @Override
    public void setParameter(MultiMap parameterMap) {
        param = parameterMap.get("param");
    }

    @Override
    public String getContent() {
        return "Parameter: " + param;
    }
}
