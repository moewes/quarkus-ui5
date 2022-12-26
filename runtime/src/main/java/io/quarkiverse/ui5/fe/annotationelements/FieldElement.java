package io.quarkiverse.ui5.fe.annotationelements;

import lombok.Builder;

@Builder
public class FieldElement implements AnnotationElement {

    private String target;
    private boolean hidden;

    @Override
    public String renderXML() {

        StringBuilder sb = new StringBuilder();

        sb.append("<Annotations Target=\"" + target + "\">");
        if (hidden) {
            sb.append("<Annotation Term=\"UI.hidden\" bool=\"true\" />");
        }
        sb.append("</Annotations>");

        return sb.toString();
    }
}
