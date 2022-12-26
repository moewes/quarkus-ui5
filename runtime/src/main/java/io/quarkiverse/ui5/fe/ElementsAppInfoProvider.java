package io.quarkiverse.ui5.fe;

import java.util.List;

import io.quarkiverse.ui5.fe.annotationelements.FieldElement;
import io.quarkiverse.ui5.fe.annotationelements.ui.HeaderInfoElement;
import io.quarkiverse.ui5.fe.annotationelements.ui.LineItemElement;

public interface ElementsAppInfoProvider {

    String getName();

    String getNamespace();

    String getEntitySetName();

    String getEntityName();

    List<FieldElement> getFieldElements();

    HeaderInfoElement getHeaderInfoElement();

    LineItemElement getLineItemElement();

}
