package io.quarkiverse.ui5;

import java.util.logging.Logger;

import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.spi.CDI;

import io.quarkiverse.ui5.runtime.Ui5Repository;
import io.quarkiverse.ui5.runtime.repository.SpaceItem;
import io.quarkiverse.ui5.spaces.SpaceContentProvider;
import io.quarkiverse.ui5.spaces.SpaceParameterConsumer;
import io.quarkus.arc.ManagedContext;
import io.quarkus.arc.runtime.BeanContainer;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.RoutingContext;

public class SpaceHandler implements Handler<RoutingContext> {

    private static final Logger log = Logger.getLogger(SpaceHandler.class.getName());

    protected final BeanContainer beanContainer;
    protected final ClassLoader classLoader;
    private final Ui5Repository repository;

    public SpaceHandler(BeanContainer beanContainer,
            ClassLoader classLoader) {
        this.beanContainer = beanContainer;
        this.classLoader = classLoader;
        repository = CDI.current().select(Ui5Repository.class).get();
    }

    @Override
    public void handle(RoutingContext routingContext) {

        Vertx vertx = routingContext.vertx();

        //routingContext.response().headers().add(HttpHeaders.CONTENT_TYPE, "application/json");
        // FIXME
        vertx.executeBlocking(promise -> {
            String result = dispatch(routingContext);
            promise.complete(result);
        }, asyncResult -> routingContext.response().end((String) asyncResult.result()));
    }

    private String dispatch(RoutingContext routingContext) {

        ManagedContext requestContext = beanContainer.requestContext();
        requestContext.activate();

        String spaceName = getSpaceName(routingContext.request().path());

        SpaceItem spaceItem = repository.getSpace(spaceName);
        if (spaceItem != null) {
            String result;
            Object spaceInstance = getSpaceInstance(spaceItem.getClassName());

            if (spaceInstance instanceof SpaceParameterConsumer) {
                MultiMap entries = routingContext.queryParams();
                ((SpaceParameterConsumer) spaceInstance).setParameter(entries);
            }
            if (spaceInstance instanceof SpaceContentProvider) {
                result = ((SpaceContentProvider) spaceInstance).getContent();
                routingContext.response().headers().add(HttpHeaders.CONTENT_TYPE,
                        ((SpaceContentProvider) spaceInstance).getContentType());
            } else {
                result = "Space: " + spaceItem.getName();
            }
            requestContext.terminate();
            return result;
        }
        requestContext.terminate();
        return "not found";
    }

    private Object getSpaceInstance(String spaceClassName) {

        Class<?> spaceClass = null;
        try {
            spaceClass = Class.forName(spaceClassName, true, classLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return CDI.current().select(spaceClass, Default.Literal.INSTANCE).get();
    }

    private String getSpaceName(String path) {

        String[] pathsegments = path.split("/");
        return pathsegments[pathsegments.length - 1];
    }
}
