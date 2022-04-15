package io.quarkiverse.ui5.deployment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.DotName;
import org.jboss.jandex.IndexView;
import org.jboss.logging.Logger;

import io.quarkiverse.ui5.CardPageBuilder;
import io.quarkiverse.ui5.PageBuilderHelper;
import io.quarkiverse.ui5.Ui5Recorder;
import io.quarkiverse.ui5.annotations.IntegrationCard;
import io.quarkiverse.ui5.annotations.UiSpace;
import io.quarkiverse.ui5.config.Ui5Configuration;
import io.quarkiverse.ui5.runtime.Ui5Repository;
import io.quarkiverse.ui5.runtime.repository.IntegrationCardItem;
import io.quarkiverse.ui5.runtime.repository.SpaceItem;
import io.quarkus.arc.deployment.*;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.vertx.http.deployment.RouteBuildItem;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

class Ui5Processor {

    private static final Logger log = Logger.getLogger(Ui5Processor.class);
    private static final String FEATURE = "ui5";

    static private final DotName REQUEST_SCOPED = DotName.createSimple(RequestScoped.class.getName());
    static private final DotName INTEGRATIONCARD = DotName.createSimple(IntegrationCard.class.getName());
    static private final DotName UISPACE = DotName.createSimple(UiSpace.class.getName());

    Ui5Configuration config;

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    BeanDefiningAnnotationBuildItem registerIntegrationCardAsBean() {
        return new BeanDefiningAnnotationBuildItem(INTEGRATIONCARD, REQUEST_SCOPED, false);
    }

    @BuildStep
    BeanDefiningAnnotationBuildItem registerUiSpaceAsBean() {
        return new BeanDefiningAnnotationBuildItem(UISPACE, REQUEST_SCOPED, false);
    }

    @BuildStep
    void scanForIntegrationCards(BeanArchiveIndexBuildItem beanArchiveIndex,
            BuildProducer<IntegrationCardBuildItem> buildProducer) {

        IndexView indexView = beanArchiveIndex.getIndex();
        Collection<AnnotationInstance> integrationCards = indexView.getAnnotations(INTEGRATIONCARD);

        integrationCards.forEach(annotationInstance -> {
            String name = annotationInstance.value().asString();
            String className = annotationInstance.target().asClass().name().toString();
            log.info("found Card " + name + " ; " + className);
            buildProducer.produce(new IntegrationCardBuildItem(name, className));

        });
    }

    @BuildStep
    void scanForUiSpaces(BeanArchiveIndexBuildItem beanArchiveIndex,
            BuildProducer<SpaceBuildItem> buildProducer) {

        IndexView indexView = beanArchiveIndex.getIndex();
        Collection<AnnotationInstance> integrationCards = indexView.getAnnotations(UISPACE);

        integrationCards.forEach(annotationInstance -> {
            String name = annotationInstance.value().asString();
            String className = annotationInstance.target().asClass().name().toString();
            log.info("found Space " + name + " ; " + className);
            buildProducer.produce(new SpaceBuildItem(name, className));

        });
    }

    @BuildStep
    AdditionalBeanBuildItem beans() {
        return new AdditionalBeanBuildItem(
                Ui5Repository.class,
                PageBuilderHelper.class,
                CardPageBuilder.class);
    }

    @BuildStep
    UnremovableBeanBuildItem unremovableBeans() {
        return UnremovableBeanBuildItem.beanTypes(
                Ui5Repository.class,
                PageBuilderHelper.class,
                CardPageBuilder.class);
    }

    @BuildStep
    @Record(ExecutionTime.STATIC_INIT)
    void registerHandler(List<IntegrationCardBuildItem> integrationCards,
            BuildProducer<RouteBuildItem> routes,
            BeanContainerBuildItem beanContainer,
            Ui5Recorder recorder) {

        Handler<RoutingContext> integrationCardManifestHandler = recorder
                .getIntegrationCardManifestHandler(beanContainer.getValue());

        List<IntegrationCardItem> cardItemList = new ArrayList<>();
        integrationCards.forEach(integrationCardBuildItem -> {
            String name = integrationCardBuildItem.getName();
            IntegrationCardItem card = new IntegrationCardItem();
            card.setName(name);
            card.setClassName(integrationCardBuildItem.getClassName());
            card.setPath("/ui5/cards/manifests/" + name + ".json"); // TODO
            cardItemList.add(card);
            routes.produce(RouteBuildItem.builder()
                    .route(card.getPath())
                    .handler(integrationCardManifestHandler)
                    .build());
        });
        recorder.registerIntegrationCards(beanContainer.getValue(), cardItemList);

        Handler<RoutingContext> integrationCardPageHandler = recorder
                .getIntegrationCardPageHandler(beanContainer.getValue());

        if (config.getDev().isAllCardsPage()) {
            routes.produce(RouteBuildItem.builder()
                    .route("/ui5/cards") // TODO
                    .handler(integrationCardPageHandler)
                    .build());
        }
    }

    @BuildStep
    @Record(ExecutionTime.STATIC_INIT)
    void registerSpaceHandler(List<SpaceBuildItem> foundSpaces,
            BuildProducer<RouteBuildItem> routes,
            BeanContainerBuildItem beanContainer,
            Ui5Recorder recorder) {

        Handler<RoutingContext> spaceHandler = recorder
                .getSpaceHandler(beanContainer.getValue());

        List<SpaceItem> spaceItems = new ArrayList<>();
        foundSpaces.forEach(spaceBuildItem -> {
            String name = spaceBuildItem.getName();
            SpaceItem spaceItem = new SpaceItem();
            spaceItem.setName(name);
            spaceItem.setClassName(spaceBuildItem.getClassName());
            spaceItem.setPath("/ui5/spaces/" + name); // TODO
            spaceItems.add(spaceItem);
            routes.produce(RouteBuildItem.builder()
                    .route(spaceItem.getPath())
                    .handler(spaceHandler)
                    .displayOnNotFoundPage()
                    .build());
        });
        recorder.registerSpaces(beanContainer.getValue(), spaceItems);
    }
}
