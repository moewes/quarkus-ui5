package io.quarkiverse.ui5.cards.list;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

@Builder
public class ListItem {

    private String title;
    private String description;
    //private ValueStateItem info; // TODO
    // private String highlight; // TODO
    //private Icon icon; // TODO
    //private List<Action> actions; // TODO
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
        return result;
    }

}
