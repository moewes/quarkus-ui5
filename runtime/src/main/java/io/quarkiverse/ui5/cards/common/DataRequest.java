package io.quarkiverse.ui5.cards.common;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Builder
public class DataRequest {

    @Builder.Default
    private String url = "";
    @Builder.Default
    private Mode mode = Mode.CORS;
    @Builder.Default
    private Method method = Method.GET;
    // parameters // TODO
    // headers // TODO
    private Integer retryAfter;
    private Boolean withCredentials;

    // TODO other attributes

    @JsonAnyGetter
    public Map<String, Object> getAttributesForJson() {
        Map<String, Object> result = new HashMap<>();

        result.put("url", url);
        if (retryAfter != null) {
            result.put("retryAfter", retryAfter);
        }
        if (withCredentials != null) {
            result.put("withCredentials", withCredentials);
        }
        if (method != Method.GET) {
            result.put("method", method.toString());
        }
        if (mode != Mode.CORS) {
            result.put("mode", mode.getText());
        }

        return result;
    }

    public enum Method {
        GET,
        POST,
        PUT,
        PATCH,
        DELETE,
        OPTIONS,
        HEAD;
    }

    @AllArgsConstructor
    public enum Mode {
        CORS("cors"),
        NO_CORS("no-cors"),
        SAME_ORIGIN("same-origin");

        @Getter
        private final String text;
    }
}
