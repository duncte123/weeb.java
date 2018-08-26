package me.duncte123.weebJava.helpers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryBuilder {

    private final StringBuilder builder = new StringBuilder();
    private byte hasParams = 0;

    public QueryBuilder append(String s) {
        builder.append(s);
        return this;
    }

    public QueryBuilder append(String key, String value) {

        if (hasParams == 1) {
            builder.append('&').append(key).append('=').append(encodeStr(value));
        } else {
            builder.append('?').append(key).append('=').append(encodeStr(value));
            hasParams = 1;
        }

        return this;
    }

    public String build() {
        return builder.toString();
    }

    private String encodeStr(String raw) {
        try {
            return URLEncoder.encode(raw, "UTF-8");
        } catch (UnsupportedEncodingException ignored) {
            return raw.replaceAll(" ", "%20");
        }
    }

}
