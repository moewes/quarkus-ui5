package io.quarkiverse.ui5.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkiverse.ui5.runtime.repository.IntegrationCardItem;
import io.quarkiverse.ui5.runtime.repository.SpaceItem;

@ApplicationScoped
public class Ui5Repository {

    private final Map<String, IntegrationCardItem> integrationCards = new HashMap<>();
    private final Map<String, SpaceItem> spaces = new HashMap<>();

    private final Map<String, SpaceItem> elementsApps = new HashMap<>();

    public void addIntegrationCard(IntegrationCardItem card) {
        integrationCards.put(card.getName(), card);
    }

    public List<IntegrationCardItem> getIntegrationCards() {
        return new ArrayList<>(integrationCards.values());
    }

    public IntegrationCardItem getIntegrationCard(String name) {
        return integrationCards.get(name);
    }

    public void addSpace(SpaceItem card) {
        spaces.put(card.getName(), card);
    }

    public List<SpaceItem> getSpaces() {
        return new ArrayList<>(spaces.values());
    }

    public SpaceItem getSpace(String name) {
        return spaces.get(name);
    }

    public void addElementsApp(SpaceItem card) {
        elementsApps.put(card.getName(), card);
    }

    public List<SpaceItem> getElementsApps() {
        return new ArrayList<>(elementsApps.values());
    }

    public SpaceItem getElementsApp(String name) {
        return elementsApps.get(name);
    }
}
