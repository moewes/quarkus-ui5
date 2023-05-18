package io.quarkiverse.ui5;

import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.spi.CDI;

import io.quarkiverse.ui5.cards.Ui5IntegrationCard;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifest;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifestApp;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifestCard;
import io.quarkiverse.ui5.runtime.Ui5Repository;
import io.quarkiverse.ui5.runtime.repository.IntegrationCardItem;
import io.quarkus.arc.ManagedContext;
import io.quarkus.arc.runtime.BeanContainer;
import io.quarkus.security.identity.CurrentIdentityAssociation;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.vertx.http.runtime.security.QuarkusHttpUser;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class IntegrationCardManifestHandler implements Handler<RoutingContext> {

    private static final Logger log = Logger.getLogger(IntegrationCardManifestHandler.class.getName());

    protected final BeanContainer beanContainer;
    protected final ClassLoader classLoader;
    private final Ui5Repository repository;
    private CurrentIdentityAssociation association; // TODO Lift up

    public IntegrationCardManifestHandler(BeanContainer beanContainer,
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

        routingContext.response().headers().add(HttpHeaders.CONTENT_TYPE, "application/json");

        vertx.executeBlocking(promise -> {
            String result = dispatch(routingContext);
            promise.complete(result);
        }, asyncResult -> routingContext.response().end((String) asyncResult.result()));
    }

    private String dispatch(RoutingContext routingContext) {

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

        Optional<String> cardName = getCardName(routingContext.request().path());

        if (cardName.isPresent()) {
            IntegrationCardItem card = repository.getIntegrationCard(cardName.get());
            Ui5IntegrationCard cardInstance = getCardInstance(card.getClassName());
            JsonObject json = JsonObject.mapFrom(cardInstance.getCardManifest());
            return json.encode();
        }

        // TODO Context
        Ui5CardManifest manifest = new Ui5CardManifest();

        Ui5CardManifestCard manifestCard = new Ui5CardManifestCard();
        Ui5CardManifestApp manifestApp = new Ui5CardManifestApp();

        manifest.setCard(manifestCard);
        manifest.setApp(manifestApp);

        JsonObject json = JsonObject.mapFrom(manifest);

        requestContext.terminate();
        return json.encode();
    }

    private Ui5IntegrationCard getCardInstance(String cardClassName) {

        Class<?> cardClass = null;
        try {
            cardClass = Class.forName(cardClassName, true, classLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (Ui5IntegrationCard) CDI.current().select(cardClass, Default.Literal.INSTANCE).get();
    }

    private Optional<String> getCardName(String path) {
        String result = null;

        Optional<String> json = Arrays.stream(path.split("/")).filter(item -> item.endsWith(".json")).findFirst();

        if (json.isPresent()) {
            String text = json.get();
            result = text.replaceAll(".json", " ").stripTrailing();
        }
        return Optional.ofNullable(result);
    }
}
