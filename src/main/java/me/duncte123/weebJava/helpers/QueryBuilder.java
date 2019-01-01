/*
 *    Copyright 2018 - 2019 Duncan Sterken
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
