package io.quarkiverse.ui5.fe.annotationelements.ui;

import java.util.ArrayList;
import java.util.List;

import io.quarkiverse.ui5.fe.annotationelements.AnnotationElement;
import lombok.Builder;

@Builder
public class SelectFieldsElement implements AnnotationElement {

    @Builder.Default
    private List<String> fields = new ArrayList<>();

    @Override
    public String renderXML() {
        StringBuilder sb = new StringBuilder();

        sb.append("<Annotation Term=\"UI.SelectionFields\"><Collection>");
        fields.forEach(field -> sb.append("<PropertyPath>")
                .append(field)
                .append("</PropertyPath>"));
        sb.append("</Collection></Annotation>");
        return sb.toString();
    }
}
