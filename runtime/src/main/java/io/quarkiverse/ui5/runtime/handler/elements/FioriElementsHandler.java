package io.quarkiverse.ui5.runtime.handler.elements;

import java.util.logging.Logger;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;

import io.quarkiverse.ui5.runtime.Ui5Repository;
import io.quarkiverse.ui5.runtime.repository.SpaceItem;
import io.quarkus.arc.ManagedContext;
import io.quarkus.arc.runtime.BeanContainer;
import io.quarkus.security.identity.CurrentIdentityAssociation;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.vertx.http.runtime.security.QuarkusHttpUser;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.RoutingContext;

public abstract class FioriElementsHandler implements Handler<RoutingContext> {

    private static final Logger log = Logger.getLogger(FioriElementsHandler.class.getName());

    protected final BeanContainer beanContainer;
    protected final ClassLoader classLoader;
    protected final Ui5Repository repository;
    protected Object appBean;
    private CurrentIdentityAssociation association;

    public FioriElementsHandler(BeanContainer beanContainer,
            ClassLoader classLoader) {
        this.beanContainer = beanContainer;
        this.classLoader = classLoader;
        repository = CDI.current().select(Ui5Repository.class).get();

        Instance<CurrentIdentityAssociation> association = CDI.current().select(CurrentIdentityAssociation.class);
        this.association = association.isResolvable() ? association.get() : null;
    }

    @Override
    public void handle(RoutingContext routingContext) {

        Vertx vertx = routingContext.vertx();

        routingContext.response()
                .headers()
                .add(HttpHeaders.CONTENT_TYPE, getContentType());

        vertx.executeBlocking(promise -> {
            String result = dispatch(routingContext);
            promise.complete(result);
        }, asyncResult -> routingContext.response().end((String) asyncResult.result()));
    }

    protected String dispatch(RoutingContext routingContext) {
        String path = routingContext.request().path();
        log.info("handle " + path);

        ManagedContext requestContext = beanContainer.requestContext();
        requestContext.activate();

        if (association != null) {
            QuarkusHttpUser existing = (QuarkusHttpUser) routingContext.user();
            if (existing != null) {
                SecurityIdentity identity = existing.getSecurityIdentity();
                association.setIdentity(identity);
            } else {
                association.setIdentity(QuarkusHttpUser.getSecurityIdentity(routingContext, null));
            }
        }

        String spaceName = getAppName(path);
        SpaceItem elementsApp = repository.getElementsApp(spaceName);
        appBean = getAppInstance(elementsApp.getClassName());

        String result = getContent();
        requestContext.terminate();
        return result;
    }

    protected abstract String getContentType();

    protected abstract String getContent();

    protected String getAppName(String path) {

        String[] pathsegments = path.split("/");
        return pathsegments[pathsegments.length - 2];
    }

    protected Object getAppInstance(String spaceClassName) {

        Class<?> spaceClass = null;
        try {
            spaceClass = Class.forName(spaceClassName, true, classLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return CDI.current().select(spaceClass, Default.Literal.INSTANCE).get();
    }
}
