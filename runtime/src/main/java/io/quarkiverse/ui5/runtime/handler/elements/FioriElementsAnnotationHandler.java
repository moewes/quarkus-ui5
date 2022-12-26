package io.quarkiverse.ui5.runtime.handler.elements;

import io.quarkiverse.ui5.fe.*;
import io.quarkiverse.ui5.fe.annotationelements.AdditionalAnnotationsProvider;
import io.quarkiverse.ui5.fe.annotationelements.AnnotationsTargetElement;
import io.quarkiverse.ui5.fe.annotationelements.FieldElement;
import io.quarkiverse.ui5.fe.annotationelements.common.DraftRootElement;
import io.quarkus.arc.runtime.BeanContainer;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class FioriElementsAnnotationHandler extends FioriElementsHandler
        implements Handler<RoutingContext> {

    public FioriElementsAnnotationHandler(BeanContainer beanContainer,
            ClassLoader classLoader) {
        super(beanContainer, classLoader);
    }

    @Override
    protected String getContent() {

        String appName = "";
        String entitySet = "";
        String namespace = "";
        String entityName = "";
        if (appBean instanceof ElementsAppInfoProvider) {
            appName = ((ElementsAppInfoProvider) appBean).getName();
            entitySet = ((ElementsAppInfoProvider) appBean).getEntitySetName();
            namespace = ((ElementsAppInfoProvider) appBean).getNamespace();
            entityName = ((ElementsAppInfoProvider) appBean).getEntityName();
        }

        StringBuilder sb = new StringBuilder();

        sb.append(
                "<edmx:Edmx xmlns:edmx=\"http://docs.oasis-open.org/odata/ns/edmx\" Version=\"4.0\">\n" +
                        "    <edmx:Reference Uri=\"https://sap.github.io/odata-vocabularies/vocabularies/Common.xml\">\n" +
                        "        <edmx:Include Namespace=\"com.sap.vocabularies.Common.v1\" Alias=\"Common\"/>\n" +
                        "    </edmx:Reference>\n" +
                        "    <edmx:Reference Uri=\"https://sap.github.io/odata-vocabularies/vocabularies/UI.xml\">\n" +
                        "        <edmx:Include Namespace=\"com.sap.vocabularies.UI.v1\" Alias=\"UI\"/>\n" +
                        "    </edmx:Reference>\n" +
                        "    <edmx:Reference Uri=\"/odata/$metadata\">\n" +
                        "        <edmx:Include Namespace=\"Quarkus.OData\"/>\n" +
                        "    </edmx:Reference>\n" +
                        "    <edmx:DataServices>\n" +
                        "        <Schema xmlns=\"http://docs.oasis-open.org/odata/ns/edm\" Namespace=\"fe\">");

        sb.append("            <Annotations Target=\"Quarkus.OData." + entityName + "\">\n");

        if (appBean instanceof SemanticKeyProvider) {
            sb.append(((SemanticKeyProvider) appBean).getSemanticKeyElement().renderXML());
        }

        if (appBean instanceof SelectionFieldsProvider) {
            sb.append(((SelectionFieldsProvider) appBean).getSelectionFieldElement().renderXML());
        }

        if (appBean instanceof ElementsAppInfoProvider) {
            sb.append(((ElementsAppInfoProvider) appBean).getLineItemElement().renderXML());
        }

        if (appBean instanceof HeaderFacetsProvider) {
            sb.append(((HeaderFacetsProvider) appBean).getHeaderFacetsElement().renderXML());
        }

        if (appBean instanceof FacetsProvider) {
            sb.append(((FacetsProvider) appBean).getFacetsElement().renderXML());
        }

        if (appBean instanceof ElementsAppInfoProvider) {
            sb.append(((ElementsAppInfoProvider) appBean).getHeaderInfoElement().renderXML());
        }
        /*
         * sb.append("                <Annotation Term=\"UI.Identification\">\n" +
         * "                    <Collection>\n" +
         * "                        <Record Type=\"UI.DataField\">\n" +
         * "                            <PropertyValue Property=\"Value\" Path=\"Id\"/>\n" +
         * "                            <PropertyValue Property=\"Label\" String=\"Name XYZ" +
         * "\"/>\n" +
         * "                        </Record>\n" +
         * "                        <Record Type=\"UI.DataFieldForAction\">\n" +
         * "                            <PropertyValue Property=\"Action\" String=\"Quarkus.OData.myAction\"/>\n" +
         * "                            <PropertyValue Property=\"Label\" String=\"myAction x\"/>\n" +
         * "                        </Record>\n" +
         * "                        <Record Type=\"UI.DataFieldForAction\">\n" +
         * "                            <PropertyValue Property=\"Action\" String=\"Quarkus.OData.myFunction\"/>\n" +
         * "                            <PropertyValue Property=\"Label\" String=\"myFunction x\"/>\n" +
         * "                        </Record>\n" +
         * "                    </Collection>\n" +
         * "                </Annotation>\n");
         */
        sb.append("            </Annotations>\n");

        if (appBean instanceof DraftActionProvider) {
            DraftRootElement draftActions = ((DraftActionProvider) appBean).getDraftActions();
            sb.append(draftActions.renderXML());
        }

        if (appBean instanceof ElementsAppInfoProvider) { // TODO Refactor?
            for (FieldElement element : ((ElementsAppInfoProvider) appBean).getFieldElements()) {
                sb.append(element.renderXML());
            }
        }

        if (appBean instanceof AdditionalAnnotationsProvider) {
            for (AnnotationsTargetElement element : (((AdditionalAnnotationsProvider) appBean).getAdditionalAnnotations())) {
                sb.append(element.renderXML());
            }
        }
        sb.append("        </Schema>\n" +
                "    </edmx:DataServices>\n" +
                "</edmx:Edmx>");

        String result = sb.toString();
        return result;
    }

    @Override
    protected String getContentType() {
        return "application/xml";
    }

    protected String getAppName(String path) {

        String[] pathsegments = path.split("/");
        return pathsegments[pathsegments.length - 3];
    }
}
