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

import me.duncte123.weebJava.models.WeebApi;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Requester {

    private final OkHttpClient client;
    public static final String USER_AGENT = "Mozilla/5.0 Weeb.java (v" +
            WeebApi.VERSION + ", https://github.com/duncte123/weeb.java)";

    public Requester() {
        this.client = new OkHttpClient.Builder()
                .readTimeout(10L, TimeUnit.SECONDS)
                .writeTimeout(10L, TimeUnit.SECONDS)
                .build();
    }

    public void requestAsync(Request r, Consumer<Response> success, Consumer<Throwable> fail) {
        client.newCall(r).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                fail.accept(e);
            }

            @Override public void onResponse(Call call, Response response) {
                if (!response.isSuccessful()) fail.accept( new IOException("Unexpected code " + response));
                else success.accept(response);
            }
        });
    }

    public Response requestSync(Request r) {
        try {
            return client.newCall(r).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toParams(String... query) {
        return query == null || query.length == 0 ? "" : "?" + StringUtils.join(query, "&");
    }
}
