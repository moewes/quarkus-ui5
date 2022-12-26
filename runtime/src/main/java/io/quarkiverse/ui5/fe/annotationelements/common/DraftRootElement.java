package io.quarkiverse.ui5.fe.annotationelements.common;

import io.quarkiverse.ui5.fe.annotationelements.AnnotationElement;
import lombok.Builder;

@Builder
public class DraftRootElement implements AnnotationElement {

    private String target;

    private String activationAction;
    private String editAction;

    private String newAction;

    private String preparationAction;

    @Override
    public String renderXML() {
        StringBuilder sb = new StringBuilder();

        sb.append("<Annotations Target=\"" + target + "\">");
        sb.append("<Annotation Term=\"Common.DraftRoot\">");
        sb.append("<Record Type=\"Common.DraftRootType\">");

        if (editAction != null) {
            sb.append("<PropertyValue Property=\"EditAction\" String=\"" + editAction + "\"/>");
        }
        if (activationAction != null) {
            sb.append("<PropertyValue Property=\"ActivationAction\" String=\"" + activationAction +
                    "\"/>");
        }
        if (preparationAction != null) {
            sb.append("<PropertyValue Property=\"PreparationAction\" String=\"" + preparationAction +
                    "\"/>");
        }

        if (newAction != null) {
            sb.append("<PropertyValue Property=\"NewAction\" String=\"" + newAction +
                    "\"/>");
        }

        sb.append("</Record>");
        sb.append("</Annotation>");
        sb.append("</Annotations>");

        return sb.toString();
    }
}
