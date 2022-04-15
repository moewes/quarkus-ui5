package io.quarkiverse.ui5;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkiverse.ui5.runtime.Ui5Repository;
import io.quarkiverse.ui5.runtime.repository.IntegrationCardItem;

@ApplicationScoped
public class CardPageBuilder {

    @Inject
    Ui5Repository ui5Repository;

    @Inject
    PageBuilderHelper pageBuilderHelper;

    public String renderPage() {

        String result = pageBuilderHelper.getPageHeaderStart()
                + pageBuilderHelper.getIntegrationCardScript()
                + pageBuilderHelper.getIntegrationCardContainerStyle()
                + pageBuilderHelper.getPageHeaderEndBodyStart();

        result = result +
                "<div class=\"container\">";

        for (IntegrationCardItem card : ui5Repository.getIntegrationCards()) {
            result = result + "<ui-integration-card manifest=\"" + card.getPath() + "\"></ui-integration-card>";
        }

        result = result + "</div>" + pageBuilderHelper.getPageEnd();

        return result;
    }
}
