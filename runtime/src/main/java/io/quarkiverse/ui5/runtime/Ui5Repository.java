package io.quarkiverse.ui5.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import io.quarkiverse.ui5.runtime.repository.IntegrationCardItem;

@ApplicationScoped
public class Ui5Repository {

    private final Map<String, IntegrationCardItem> integrationCards = new HashMap<>();

    public void addIntegrationCard(IntegrationCardItem card) {
        integrationCards.put(card.getName(), card);
    }

    public List<IntegrationCardItem> getIntegrationCards() {
        return new ArrayList<>(integrationCards.values());
    }

    public IntegrationCardItem getIntegrationCard(String name) {
        return integrationCards.get(name);
    }
}
