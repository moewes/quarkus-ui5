package io.quarkiverse.ui5.runtime.handler.elements;

import java.util.logging.Logger;

import io.quarkiverse.ui5.fe.ElementsAppInfoProvider;
import io.quarkus.arc.runtime.BeanContainer;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class FioriElementsAppComponentHandler extends FioriElementsHandler
        implements Handler<RoutingContext> {

    private static final Logger log = Logger.getLogger(FioriElementsAppComponentHandler.class.getName());

    public FioriElementsAppComponentHandler(BeanContainer beanContainer,
            ClassLoader classLoader) {
        super(beanContainer, classLoader);
    }

    @Override
    protected String getContentType() {
        return "text/javascript;charset=UTF-8";
    }

    @Override
    protected String getContent() {

        String appName = "";
        String entitySet = "";
        String namespace = "";
        if (appBean instanceof ElementsAppInfoProvider) {
            appName = ((ElementsAppInfoProvider) appBean).getName();
            entitySet = ((ElementsAppInfoProvider) appBean).getEntitySetName();
            namespace = ((ElementsAppInfoProvider) appBean).getNamespace();
        }

        StringBuilder sb = new StringBuilder();

        sb.append("sap.ui.define([\"sap/fe/core/AppComponent\"], function(AppComponent) {\n" +
                "    \"use strict\";\n" +
                "\n" +
                "    return AppComponent.extend(\"" + namespace + ".Component\", {\n" +
                "        metadata: {\n" +
                "            manifest: \"json\"\n" +
                "        }\n" +
                "    });\n" +
                "});");

        String result = sb.toString();
        return result;
    }
}
