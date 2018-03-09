/*
 *    Copyright 2018 Duncan Sterken
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

package me.duncte123.weebJava.web;

import com.github.natanbc.reliqua.Reliqua;
import com.github.natanbc.reliqua.request.PendingRequest;
import com.github.natanbc.reliqua.util.RequestMapper;
import me.duncte123.weebJava.models.WeebApi;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RequestManager extends Reliqua {

    private static final String USER_AGENT = "Mozilla/5.0 Weeb.java (v" +
            WeebApi.VERSION + ", https://github.com/duncte123/weeb.java)";

    public RequestManager(OkHttpClient client) {
        super(null, client, true);
    }

    public Request.Builder prepareGet(String url, String token) {
        return new Request.Builder()
                .url(url)
                .get()
                .header("Authorization", token)
                .addHeader("User-Agent", USER_AGENT);
    }

    public Request.Builder preparePOST(String url, JSONObject body, String token) {
        return new Request.Builder()
                .url(url)
                .post(RequestBody.create(MediaType.parse("application/json"), body.toString()))
                .header("Authorization", token)
                .addHeader("User-Agent", USER_AGENT);
    }

    @NotNull
    public <T> PendingRequest<T> createRequest(@Nullable String route, @Nonnull Request.Builder requestBuilder, @Nonnegative int expectedStatusCode, @Nonnull RequestMapper<T> mapper) {
        return createRequest(route, requestBuilder.build(), expectedStatusCode, mapper);
    }

    public String toParams(String... query) {
        return query == null || query.length == 0 ? "" : "?" + StringUtils.join(query, "&");
    }
}
