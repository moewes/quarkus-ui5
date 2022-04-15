package io.quarkiverse.ui5;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PageBuilderHelper {

    @ConfigProperty(name = "quarkus.ui5.theme.name")
    String themeName;

    public String getIntegrationCardScript() {
        return "<script src=\"https://ui5.sap.com/resources/sap-ui-integration.js\"" +
                "id=\"sap-ui-bootstrap\"" +
                "data-sap-ui-theme=\"" + themeName + "\">" +
                "</script> ";
    }

    public String getIntegrationCardContainerStyle() {
        return "<style> .container { display: grid; " +
                "grid-template-columns: repeat(4, 1fr);" +
                "gap: 1em;" +
                "padding-top: 1em; " +
                "padding-left: 1em; " +
                "padding-right: 1em; } " +
                "</style>";
    }

    public String getMinMaxContainerStyle(String containerName, String minmax) {
        return "<style> ." + containerName + " { display: grid; " +
                "grid-template-columns: repeat(auto-fill, minmax(" + minmax + "px, 1fr));" +
                "padding-top: 1em; " +
                "padding-left: 1em; " +
                "padding-right: 1em; } " +
                "</style>";
    }

    public String getContainerStyle(String containerName) {
        return "<style> ." + containerName + " { " +
                "padding-top: 1em; " +
                "padding-left: 1em; " +
                "padding-right: 1em; } " +
                "</style>";
    }

    public String getPageHeaderStart() {
        return "<!doctype html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"utf-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">";
    }

    public String getPageHeaderEndBodyStart() {
        return "</head><body class=\"sapUiBody\">";
    }

    public String getPageEnd() {
        return "</body></html>";
    }

    public String getIntegrationCardItem(String manifestUrl) {
        return "<ui-integration-card manifest=\"" + manifestUrl +
                "\"></ui-integration-card>";
    }

    public String getQuickLinkItem(String url, String title, String subTitle, String icon,
            String target) {
        String result = "<ui5-product-switch-item ";

        if (title != null) {
            result = result + " title-text=\"" + title + "\"";
        }
        if (subTitle != null) {
            result = result + " subtitle-text=\"" + subTitle + "\"";
        }
        if (icon != null) {
            result = result + " icon=\"" + icon + "\"";
        }
        if (url != null) {
            result = result + " target-src=\"" + url + "\"";
        }
        if (target != null) {
            result = result + " target=\"" + target + "\"";
        }
        return result + "></ui5-product-switch-item>";
    }
}
