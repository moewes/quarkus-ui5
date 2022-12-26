package io.quarkiverse.ui5.fe.annotationelements.ui;

import lombok.Builder;
import lombok.Getter;

@Builder
public class TableFacetElement implements FacetElement {

    private String path;

    @Getter
    @Builder.Default
    private String label = "";

    @Override
    public String renderXML() {

        return "";
    }

    @Override
    public String getAnnotationPath() {
        return path + "/@UI.LineItem";
    }
}
