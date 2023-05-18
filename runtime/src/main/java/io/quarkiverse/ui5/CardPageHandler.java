package io.quarkiverse.ui5;

import java.util.logging.Logger;

import jakarta.enterprise.inject.spi.CDI;

import io.quarkus.arc.ManagedContext;
import io.quarkus.arc.runtime.BeanContainer;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;

public class CardPageHandler implements Handler<RoutingContext> {

    private static final Logger log = Logger.getLogger(CardPageHandler.class.getName());
    protected final BeanContainer beanContainer;
    private final CardPageBuilder pageBuilder;

    public CardPageHandler(BeanContainer beanContainer) {
        this.beanContainer = beanContainer;
        pageBuilder = CDI.current().select(CardPageBuilder.class).get();
    }

    @Override
    public void handle(RoutingContext routingContext) {

        Vertx vertx = routingContext.vertx();

        vertx.executeBlocking(promise -> {
            String result = dispatch(routingContext);
            promise.complete(result);
        }, asyncResult -> routingContext.response().end((String) asyncResult.result()));
    }

    private String dispatch(RoutingContext routingContext) {

        String path = routingContext.request().path();
        log.info("handle " + path);

        ManagedContext requestContext = beanContainer.requestContext();
        requestContext.activate();

        String result = pageBuilder.renderPage();
        requestContext.terminate();
        return result;

    }
}
