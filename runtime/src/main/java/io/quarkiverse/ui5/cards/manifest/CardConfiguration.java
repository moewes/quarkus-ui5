package io.quarkiverse.ui5.cards.manifest;

import java.util.HashMap;
import java.util.Map;

import io.quarkiverse.ui5.cards.adaptive.ActionHandler;
import lombok.Getter;

@Getter
public class CardConfiguration {

    private Map<String, ActionHandler> actionHandlers = new HashMap<>();
}
