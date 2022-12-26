package io.quarkiverse.ui5.fe.annotationelements.common;

import io.quarkiverse.ui5.fe.annotationelements.AnnotationElement;
import lombok.Builder;

@Builder
public class SemanticKeyElement implements AnnotationElement {

    private String propertyPath;

    @Override
    public String renderXML() {

        StringBuilder sb = new StringBuilder();
        sb.append("<Annotation Term=\"Common.SemanticKey\">\n" +
                "        <Collection>\n" +
                "            <PropertyPath>Id</PropertyPath>\n" +
                "        </Collection>\n" +
                "    </Annotation>");
        return sb.toString();
    }
}
