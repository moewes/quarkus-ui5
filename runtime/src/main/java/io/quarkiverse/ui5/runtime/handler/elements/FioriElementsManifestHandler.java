package io.quarkiverse.ui5.runtime.handler.elements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.quarkiverse.ui5.fe.ElementsAppInfoProvider;
import io.quarkiverse.ui5.manifest.FeApp;
import io.quarkiverse.ui5.manifest.FeUi5;
import io.quarkiverse.ui5.manifest.Ui5Manifest;
import io.quarkiverse.ui5.manifest.datasources.Datasource;
import io.quarkiverse.ui5.manifest.datasources.Datasources;
import io.quarkiverse.ui5.manifest.models.MainModel;
import io.quarkiverse.ui5.manifest.models.Models;
import io.quarkiverse.ui5.manifest.routing.*;
import io.quarkus.arc.runtime.BeanContainer;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class FioriElementsManifestHandler extends FioriElementsHandler
        implements Handler<RoutingContext> {

    public FioriElementsManifestHandler(BeanContainer beanContainer,
            ClassLoader classLoader) {
        super(beanContainer, classLoader);
    }

    @Override
    protected String getContentType() {
        return "application/json";
    }

    protected String getContent() {

        String appName = "";
        String entitySet = "";
        String namespace = "";
        if (appBean instanceof ElementsAppInfoProvider) {
            appName = ((ElementsAppInfoProvider) appBean).getName();
            entitySet = ((ElementsAppInfoProvider) appBean).getEntitySetName();
            namespace = ((ElementsAppInfoProvider) appBean).getNamespace();
        }

        // TODO Context
        Datasource datasource = Datasource.builder().uri("http://localhost:8080/odata/").build();
        Map<String, Datasource> datasources = new HashMap<>();
        datasources.put("mainService", datasource);

        Map<String, Object> navigation = new HashMap<>();

        Map<String, Object> detailBerufsfelder = new HashMap<>();
        detailBerufsfelder.put("detail", TargetOptionNavigation.builder().route(
                "Detail").build());

        navigation.put(entitySet, detailBerufsfelder);

        TargetOptions listOptions = new TargetOptions();
        listOptions.getSettings().put("entitySet", entitySet);
        listOptions.getSettings().put("navigation", navigation);
        listOptions.getSettings().put("initialLoad", "Enabled");
        listOptions.getSettings().put("variantManagement", "None");

        TargetOptions detailOptions = new TargetOptions();
        detailOptions.getSettings().put("entitySet", entitySet);

        Map<String, Target> targets = new HashMap<>();
        targets.put("List",
                Target.builder().id("List")
                        .name("sap.fe.templates.ListReport")
                        .type("Component")
                        .options(listOptions).build());
        targets.put("Detail",
                Target.builder().id("Detail")
                        .name("sap.fe.templates.ObjectPage")
                        .type("Component")
                        .options(detailOptions).build());

        Routing routing = Routing.builder()
                .routes(Arrays.asList(
                        Route.builder()
                                .name("List")
                                .target("List")
                                .pattern(":?query:")
                                .build(),
                        Route.builder()
                                .name("Detail")
                                .target("Detail")
                                .pattern(entitySet + "({key}):?query:")
                                .build()))
                .targets(Targets.builder().targets(targets).build()).build();

        Models models = Models.builder()
                .mainModel(MainModel.builder().dataSource("mainService").build())
                .build();

        Ui5Manifest manifest = Ui5Manifest.builder()
                .app(FeApp.builder()
                        .id(namespace)
                        .title(appName)
                        .datasources(Datasources.builder().datasources(datasources).build())
                        .build())
                .ui5(FeUi5.builder().routing(routing).models(models).build())
                .build();

        JsonObject json = JsonObject.mapFrom(manifest);

        return json.encode();
    }

}
