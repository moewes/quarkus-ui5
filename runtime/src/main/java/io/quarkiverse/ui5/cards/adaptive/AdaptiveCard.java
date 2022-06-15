package io.quarkiverse.ui5.cards.adaptive;

import java.util.ArrayList;
import java.util.List;

import io.quarkiverse.ui5.cards.IntegrationCard;
import io.quarkiverse.ui5.cards.common.CardContent;
import io.quarkiverse.ui5.cards.manifest.CardConfiguration;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifestCard;

public class AdaptiveCard extends IntegrationCard {

    private final List<AdaptiveElement> body = new ArrayList<>();
    private final List<AdaptiveAction> actions = new ArrayList<>();

    public AdaptiveCard() {
        super();
        manifest.getCard().setType(Ui5CardManifestCard.Type.ADAPTIVE);
        CardContent content = new CardContent();
        //content.add("$schema", "http://adaptivecards.io/schemas/adaptive-card.json");
        content.add("type", "AdaptiveCard");
        //content.add("version", "1.0");

        content.add("body", body);
        content.add("actions", actions);
        manifest.getCard().setContent(content);
    }

    public void addElement(AdaptiveElement element) {
        body.add(element);
    }

    public void addAction(AdaptiveAction action) {
        actions.add(action);
    }

    public void setActionHandler(ActionHandler actionHandler) {
        CardConfiguration configuration = new CardConfiguration();
        configuration.getActionHandlers().put("submit", actionHandler);
        manifest.getCard().setConfiguration(configuration);
    }
}
