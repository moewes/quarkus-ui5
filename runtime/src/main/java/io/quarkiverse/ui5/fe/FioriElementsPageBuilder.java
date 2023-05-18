package io.quarkiverse.ui5.fe;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class FioriElementsPageBuilder {

    @ConfigProperty(name = "quarkus.ui5.dev.path")
    String ui5BasePath;

    public String renderPage(Object app) {

        String appName = "";
        String entitySet = "";
        String namespace = "";
        if (app instanceof ElementsAppInfoProvider) {
            appName = ((ElementsAppInfoProvider) app).getName();
            entitySet = ((ElementsAppInfoProvider) app).getEntitySetName();
            namespace = ((ElementsAppInfoProvider) app).getNamespace();
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<!DOCTYPE html><html lang=\"en\"><head>");

        stringBuilder.append("<meta charset=\"UTF-8\">");
        stringBuilder.append("<meta name=\"viewport\" content=\"width=device-width, " +
                "initial-scale=1.0\">");
        stringBuilder.append("<title>" + appName + "</title>");

        stringBuilder.append("<style>\n" +
                "        html, body, body > div, #container, #container-uiarea {\n" +
                "            height: 98%;\n" +
                "        }\n" +
                "    </style>");

        stringBuilder.append("<script\n" +
                "            id=\"sap-ui-bootstrap\"\n" +
                "            src=\"" + ui5BasePath + "/resources/sap-ui-core.js\"\n" +
                "            data-sap-ui-flexibilityServices=\"/my/flex/service\"\n" +
                "            data-sap-ui-theme=\"sap_fiori_3\"\n" +
                "            data-sap-ui-resourceroots='{\n" +
                "            \"" + namespace + "\": \".\"\n" +
                "        }'\n" +
                "            data-sap-ui-oninit=\"module:sap/ui/core/ComponentSupport\"\n" +
                "            data-sap-ui-compatVersion=\"edge\"\n" +
                "            data-sap-ui-async=\"true\"\n" +
                "            data-sap-ui-preload=\"async\">\n" +
                "    </script>");

        stringBuilder.append("<script src=\"/webjars/cloud-ui-ui5/0.3.0/index.js\"></script>");

        stringBuilder.append("</head>");
        stringBuilder.append("<body class=\"sapUiBody sapUiSizeCompact\" id=\"content\">");

        stringBuilder.append("<ui5-shellbar primary-title=\"" + appName + "\"></ui5-shellbar>");

        stringBuilder.append(
                "<div data-sap-ui-component data-name=\"" + namespace + "\" data-id" +
                        "=\"container" +
                        "\" " +
                        "data-settings='{\"id\" : \"BerufsfelderApp_Subclass\"}'\n"
                        +
                        "     data-handle-validation=\"true\"></div>");

        stringBuilder.append("</body></html>");

        return stringBuilder.toString();
    }
}
