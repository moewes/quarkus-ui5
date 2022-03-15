package io.quarkiverse.ui5.cards.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.quarkiverse.ui5.cards.common.CardContent;
import io.quarkiverse.ui5.cards.common.CardData;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifest;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifestCard;

class ListCardTest {

    @Test
    public void cardTest() {

        ListItem listItem = ListItem.builder().build();
        Integer maxItems = 5;
        CardData listData = CardData.builder().build();

        ListCard card = new ListCard();
        card.setMaxItems(maxItems);
        card.setListItem(listItem);
        card.setListData(listData);

        Ui5CardManifest cardManifest = card.getCardManifest();

        assertEquals(Ui5CardManifestCard.Type.LIST.getText(),
                cardManifest.getCard().getAttributesForJson().get("type"));
        assertEquals(maxItems,
                ((CardContent) cardManifest.getCard()
                        .getAttributesForJson()
                        .get("content")).getAttributesForJson().get(
                                "maxItems"));
        assertEquals(listItem,
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
