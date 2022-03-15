package io.quarkiverse.ui5.cards.list;

import io.quarkiverse.ui5.cards.IntegrationCard;
import io.quarkiverse.ui5.cards.common.CardContent;
import io.quarkiverse.ui5.cards.common.CardData;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifestCard;

public class ListCard extends IntegrationCard {

    private final CardContent content = new CardContent();

    public ListCard() {
        super();
        manifest.getCard().setType(Ui5CardManifestCard.Type.LIST);
        manifest.getCard().setContent(content);
    }

    public void setListItem(ListItem listItem) {
        content.add("item", listItem);
    }

    public void setMaxItems(Integer maxItems) {
        content.add("maxItems", maxItems);
    }
    /*
     * // TODO
     * public void setGroup(ListGroup listGroup) {
     * 
     * }
     */

    public void setListData(CardData data) {
        content.add("data", data);
    }
}
