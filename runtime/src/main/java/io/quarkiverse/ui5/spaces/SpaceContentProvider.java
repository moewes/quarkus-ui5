package io.quarkiverse.ui5.spaces;

public interface SpaceContentProvider {

    String getContent();

    default String getContentType() {
        return "text/html";
    }
}
