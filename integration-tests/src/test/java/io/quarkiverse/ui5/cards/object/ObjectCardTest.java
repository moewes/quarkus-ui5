package io.quarkiverse.ui5.cards.object;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.quarkiverse.ui5.cards.common.CardContent;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifest;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifestCard;

class ObjectCardTest {

    @Test
    public void cardTest() {

        ObjectGroup objectGroup = ObjectGroup.builder().build();

        ObjectCard card = new ObjectCard();

        card.addContentGroup(objectGroup);

        Ui5CardManifest cardManifest = card.getCardManifest();

        assertEquals(Ui5CardManifestCard.Type.OBJECT.getText(),
                cardManifest.getCard().getAttributesForJson().get("type"));
        assertEquals(objectGroup,
                ((List<ObjectGroup>) ((CardContent) cardManifest.getCard()
                        .getAttributesForJson()
                        .get("content")).getAttributesForJson().get(
                                "groups")).get(0));

    }
}
