package io.quarkiverse.ui5.cards.timeline;

import io.quarkiverse.ui5.cards.IntegrationCard;
import io.quarkiverse.ui5.cards.common.CardContent;
import io.quarkiverse.ui5.cards.common.CardData;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifestCard;

public class TimelineCard extends IntegrationCard {

    private final CardContent content = new CardContent();

    public TimelineCard() {
        super();
        manifest.getCard().setType(Ui5CardManifestCard.Type.TIMELINE);
        manifest.getCard().setContent(content);
    }

    public void setTimelineItem(TimelineItem item) {
        content.add("item", item);
    }

    public void setMaxItems(Integer maxItems) {
        content.add("maxItems", maxItems);
    }

    public void setTimelineData(CardData data) {
        content.add("data", data);
    }
}
