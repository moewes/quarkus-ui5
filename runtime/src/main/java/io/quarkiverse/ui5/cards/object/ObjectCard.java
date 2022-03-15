package io.quarkiverse.ui5.cards.object;

import java.util.ArrayList;
import java.util.List;

import io.quarkiverse.ui5.cards.IntegrationCard;
import io.quarkiverse.ui5.cards.common.CardContent;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifestCard;

public class ObjectCard extends IntegrationCard {

    private final List<ObjectGroup> contentGroups = new ArrayList<>();

    public ObjectCard() {
        super();
        manifest.getCard().setType(Ui5CardManifestCard.Type.OBJECT);
        CardContent content = new CardContent();
        content.add("groups", contentGroups);
        manifest.getCard().setContent(content);
    }

    public void addContentGroup(ObjectGroup group) {
        contentGroups.add(group);
    }

}
