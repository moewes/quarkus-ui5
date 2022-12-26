package io.quarkiverse.ui5.fe.annotationelements.ui;

import io.quarkiverse.ui5.fe.annotationelements.AnnotationElement;
import lombok.Builder;

@Builder
public class HeaderInfoElement implements AnnotationElement {

    @Builder.Default
    private String typeName = "TypeName";
    @Builder.Default
    private String typeNamePlural = "TypesName";

    private DataFieldRecordElement title;
    private DataFieldRecordElement description;

    @Override
    public String renderXML() {

        StringBuilder sb = new StringBuilder();
        sb.append("<Annotation Term=\"UI.HeaderInfo\">");
        sb.append("<Record Type=\"UI.HeaderInfoType\">");

        sb.append("<PropertyValue Property=\"TypeName\" String=\"" + typeName + "\"/>\n");

        sb.append("<PropertyValue Property=\"TypeNamePlural\" String=\"" + typeNamePlural + "\"/>");

        if (title != null) {
            sb.append("<PropertyValue Property=\"Title\">" + title.renderXML() +
                    "                        </PropertyValue>");
        }
        if (description != null) {
            sb.append("<PropertyValue Property=\"Description\">" + description.renderXML() +
                    "                        </PropertyValue>");
        }
        sb.append("</Record>");
        sb.append("</Annotation>");

        return sb.toString();
    }
}
