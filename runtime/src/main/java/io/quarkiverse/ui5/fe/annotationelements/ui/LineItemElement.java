package io.quarkiverse.ui5.fe.annotationelements.ui;

import java.util.ArrayList;
import java.util.List;

import io.quarkiverse.ui5.fe.annotationelements.AnnotationElement;
import lombok.Builder;

@Builder
public class LineItemElement implements AnnotationElement {

    @Builder.Default
    private List<DataFieldRecordElement> lineItems = new ArrayList<>();

    @Override
    public String renderXML() {

        StringBuilder sb = new StringBuilder();

        sb.append("<Annotation Term=\"UI.LineItem\"><Collection>");
        lineItems.forEach(item -> sb.append(item.renderXML()));
        sb.append("</Collection></Annotation>");

        return sb.toString();
    }
}
