package io.quarkiverse.ui5;

import java.util.List;

import io.quarkiverse.ui5.runtime.Ui5Repository;
import io.quarkiverse.ui5.runtime.repository.IntegrationCardItem;
import io.quarkiverse.ui5.runtime.repository.SpaceItem;
import io.quarkus.arc.runtime.BeanContainer;
import io.quarkus.runtime.annotations.Recorder;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

@Recorder
public class Ui5Recorder {

    public Handler<RoutingContext> getIntegrationCardManifestHandler(BeanContainer beanContainer) {
        return new IntegrationCardManifestHandler(beanContainer,
                Thread.currentThread().getContextClassLoader());
    }

    public Handler<RoutingContext> getSpaceHandler(BeanContainer beanContainer) {
        return new SpaceHandler(beanContainer,
                Thread.currentThread().getContextClassLoader());
    }

    public void registerIntegrationCards(BeanContainer beanContainer,
            List<IntegrationCardItem> cardItemList) {

        Ui5Repository ui5Repository = beanContainer.instance(Ui5Repository.class);
        cardItemList.forEach(ui5Repository::addIntegrationCard);
    }

    public void registerSpaces(BeanContainer beanContainer,
            List<SpaceItem> spaceItemList) {

        Ui5Repository ui5Repository = beanContainer.instance(Ui5Repository.class);
        spaceItemList.forEach(ui5Repository::addSpace);
    }

    public Handler<RoutingContext> getIntegrationCardPageHandler(BeanContainer beanContainer) {
        return new CardPageHandler(beanContainer);
    }
}
