package io.quarkiverse.ui5.fe.annotationelements.ui;

import java.util.ArrayList;
import java.util.List;

import io.quarkiverse.ui5.fe.annotationelements.AnnotationElement;
import lombok.Builder;

@Builder
public class HeaderFacetsElement implements AnnotationElement {

    @Builder.Default
    private List<FieldGroupElement> fieldGroups = new ArrayList<>();

    @Override
    public String renderXML() {

        StringBuilder sb = new StringBuilder();
        sb.append("<Annotation Term=\"UI.Facets\"><Collection>");
        fieldGroups.forEach(fieldGroup -> sb.append("<Record Type=\"UI.ReferenceFacet\">")
                .append("<PropertyValue Property=\"Label\" String=\"")
                .append(fieldGroup.getLabel())
                .append("\"/>")
                .append("<PropertyValue Property=\"Target\" AnnotationPath=\"@UI.FieldGroup#")
                .append(fieldGroup.getQualifier())
                .append("\"/>")
                .append("</Record>"));
        sb.append("</Collection></Annotation>");
        return sb.toString();
    }
}
