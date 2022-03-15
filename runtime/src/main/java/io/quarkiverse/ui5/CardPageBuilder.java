package io.quarkiverse.ui5;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkiverse.ui5.runtime.Ui5Repository;
import io.quarkiverse.ui5.runtime.repository.IntegrationCardItem;

@ApplicationScoped
public class CardPageBuilder {

    @Inject
    Ui5Repository ui5Repository;

    public String renderPage() {

        String result = "<!doctype html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"utf-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">";

        result = result + "<script src=\"https://ui5.sap.com/resources/sap-ui-integration.js\"" +
                "id=\"sap-ui-bootstrap\"" +
                "data-sap-ui-theme=\"sap_fiori_3\">" +
                "</script> ";
        result = result + "<style> .container { display: grid; " +
                "grid-template-columns: repeat(4, 1fr);" +
                "gap: 1em; } " +
                "</style>";

        result = result +
                "</head><body><div class=\"container\">";

        for (IntegrationCardItem card : ui5Repository.getIntegrationCards()) {
            result = result + "<ui-integration-card manifest=\"" + card.getPath() + "\"></ui-integration-card>";
        }

        result = result + "</div></body></html>";

        return result;

    }
}
