package io.quarkiverse.ui5.cards.adaptive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class AdaptiveTextInputElementTest {

    @Test
    public void testBuilder() {

        AdaptiveTextInputElement build = AdaptiveTextInputElement.builder().style("style").label("label").build();

        assertNotNull(build);
        assertEquals("Input.Text", build.getType());
        assertEquals("style", build.getStyle());
        assertEquals("label", build.getLabel());

    }
}
