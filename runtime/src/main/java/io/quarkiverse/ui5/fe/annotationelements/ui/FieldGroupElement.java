package io.quarkiverse.ui5.fe.annotationelements.ui;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
public class FieldGroupElement implements FacetElement {

    @Getter
    private String qualifier;

    @Getter
    @Builder.Default
    private String label = "";

    @Builder.Default
    private List<DataFieldRecordElement> fields = new ArrayList<>();

    @Override
    public String renderXML() {

        StringBuilder sb = new StringBuilder();
        sb.append("<Annotation Term=\"UI.FieldGroup\" Qualifier=\"")
                .append(qualifier)
                .append("\"><Record Type=\"UI")
                .append(".FieldGroupType\">")
                .append("<PropertyValue Property=\"Data\">")
                .append("<Collection>");

        fields.forEach(field -> sb.append(field.renderXML()));

        sb.append("</Collection></PropertyValue></Record></Annotation>");
        return sb.toString();
    }

    @Override
    public String getAnnotationPath() {
        return "@UI.FieldGroup#" + qualifier;
    }
}
