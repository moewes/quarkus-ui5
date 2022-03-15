package io.quarkiverse.ui5.cards;

import io.quarkiverse.ui5.cards.common.CardData;
import io.quarkiverse.ui5.cards.common.CardHeader;
import io.quarkiverse.ui5.cards.manifest.Ui5CardManifest;
import lombok.Getter;

public abstract class IntegrationCard implements Ui5IntegrationCard {

    @Getter
    protected final Ui5CardManifest manifest = new Ui5CardManifest();

    public void setHeader(CardHeader header) {
        getCardManifest().getCard().setHeader(header);
    }

    public void setData(CardData data) {
        manifest.getCard().setData(data);
    }

    @Override
    public Ui5CardManifest getCardManifest() {
        return manifest;
    }
}
