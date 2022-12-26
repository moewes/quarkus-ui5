package io.quarkiverse.ui5.fe.annotationelements;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
public class AnnotationsTargetElement implements AnnotationElement {

    private String target;

    @Builder.Default
    @Getter
    private List<AnnotationElement> annotationElements = new ArrayList<>();

    @Override
    public String renderXML() {

        StringBuilder sb = new StringBuilder();
        sb.append("<Annotations Target=\"")
                .append(target)
                .append("\">");
        annotationElements.forEach(element -> sb.append(element.renderXML()));

        sb.append("</Annotations>");
        return sb.toString();
    }
}
