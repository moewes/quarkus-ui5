package io.quarkiverse.ui5.runtime.handler.elements;

import javax.enterprise.inject.spi.CDI;

import io.quarkiverse.ui5.fe.FioriElementsPageBuilder;
import io.quarkus.arc.runtime.BeanContainer;

public class FioriElementsAppHandler extends FioriElementsHandler {

    private final FioriElementsPageBuilder pageBuilder;

    public FioriElementsAppHandler(BeanContainer beanContainer,
            ClassLoader classLoader) {
        super(beanContainer, classLoader);

        pageBuilder = CDI.current().select(FioriElementsPageBuilder.class).get();
    }

    @Override
    protected String getContentType() {
        return "text/html";
    }

    @Override
    protected String getContent() {
        return pageBuilder.renderPage(appBean);
    }
}
