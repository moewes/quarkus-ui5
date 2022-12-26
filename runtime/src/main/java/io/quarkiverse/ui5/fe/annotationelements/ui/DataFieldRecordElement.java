package io.quarkiverse.ui5.fe.annotationelements.ui;

import io.quarkiverse.ui5.fe.annotationelements.AnnotationElement;
import lombok.Builder;

@Builder
public class DataFieldRecordElement implements AnnotationElement {

    @Builder.Default
    private String label = "";
    @Builder.Default
    private String path = "";

    @Override
    public String renderXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<Record Type=\"UI.DataField\">");
        sb.append("<PropertyValue Property=\"Label\" String=\"" + label + "\"/>");
        sb.append("<PropertyValue Property=\"Value\" Path=\"" + path + "\"/>");
        sb.append("</Record>");
        return sb.toString();
    }
}
