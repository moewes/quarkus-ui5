package io.quarkiverse.ui5.cards.list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import io.quarkiverse.ui5.cards.common.CardAction;
import lombok.Builder;

@Builder
public class ListItem {

    private String title;
    private String description;
    //private ValueStateItem info; // TODO
    // private String highlight; // TODO
    //private Icon icon; // TODO
    private List<CardAction> actions; // TODO Test
    //private Microchart chart; // TODO
    // actionStrip // TODO
    //attributes // TODO
    // attributesLayoutType // TODO

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = new HashMap<>();
        if (title != null) {
            result.put("title", title);
        }
        if (description != null) {
            result.put("description", description);
        }
        if (actions != null) {
            result.put("actions", actions);
        }
        return result;
    }

}
