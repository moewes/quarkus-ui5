package io.quarkiverse.ui5.fe.annotationelements.ui;

import java.util.ArrayList;
import java.util.List;

import io.quarkiverse.ui5.fe.annotationelements.AnnotationElement;
import lombok.Builder;

@Builder
public class FacetsElement implements AnnotationElement {

    @Builder.Default
    private List<FacetElement> fieldGroups = new ArrayList<>();

    @Override
    public String renderXML() {

        StringBuilder sb = new StringBuilder();
        sb.append("<Annotation Term=\"UI.Facets\"><Collection>");
        fieldGroups.forEach(fieldGroup -> sb.append("<Record Type=\"UI.ReferenceFacet\">")
                .append("<PropertyValue Property=\"Label\" String=\"")
                .append(fieldGroup.getLabel())
                .append("\"/>")
                .append("<PropertyValue Property=\"Target\" AnnotationPath=\"")
                .append(fieldGroup.getAnnotationPath())
                .append("\"/>")
                .append("</Record>"));
        sb.append("</Collection></Annotation>");

        fieldGroups.forEach(fieldGroup -> sb.append(fieldGroup.renderXML()));
        return sb.toString();
    }
}
