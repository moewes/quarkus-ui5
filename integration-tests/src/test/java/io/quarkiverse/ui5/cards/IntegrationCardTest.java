package io.quarkiverse.ui5.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import io.quarkiverse.ui5.cards.common.CardData;
import io.quarkiverse.ui5.cards.common.CardHeader;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifest;

class IntegrationCardTest {

    @Test
    public void test() {

        CardHeader cardHeader = CardHeader.builder().build();
        CardData cardData = CardData.builder().build();

        IntegrationCard card = new IntegrationCard() {

        };
        card.setHeader(cardHeader);
        card.setData(cardData);

        Ui5CardManifest manifest = card.getCardManifest();

        assertNotNull(manifest);
        assertEquals(cardHeader, manifest.getCard().getAttributesForJson().get("header"));
        assertEquals(cardData, manifest.getCard().getAttributesForJson().get("data"));
    }
}
