package io.quarkiverse.ui5.cards.manifest;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Ui5CardManifest {

    @JsonProperty("sap.app")
    private Ui5CardManifestApp app = new Ui5CardManifestApp();
    @JsonProperty("sap.card")
    private Ui5CardManifestCard card = new Ui5CardManifestCard();

}
