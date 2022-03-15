package io.quarkiverse.ui5.cards.timeline;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.quarkiverse.ui5.cards.common.CardContent;
import io.quarkiverse.ui5.cards.common.CardData;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifest;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifestCard;

class TimelineCardTest {

    @Test
    public void cardTest() {

        TimelineItem timelineItem = TimelineItem.builder().build();
        CardData listData = CardData.builder().build();
        Integer maxItems = 5;

        TimelineCard card = new TimelineCard();
        card.setTimelineItem(timelineItem);
        card.setMaxItems(maxItems);
        card.setTimelineData(listData);

        Ui5CardManifest cardManifest = card.getCardManifest();

        assertEquals(Ui5CardManifestCard.Type.TIMELINE.getText(),
                cardManifest.getCard().getAttributesForJson().get("type"));
        assertEquals(maxItems,
                ((CardContent) cardManifest.getCard()
                        .getAttributesForJson()
                        .get("content")).getAttributesForJson().get(
                                "maxItems"));
        assertEquals(timelineItem,
                ((CardContent) cardManifest.getCard()
                        .getAttributesForJson()
                        .get("content")).getAttributesForJson().get(
                                "item"));
        assertEquals(listData,
                ((CardContent) cardManifest.getCard()
                        .getAttributesForJson()
                        .get("content")).getAttributesForJson().get(
                                "data"));
    }

}
