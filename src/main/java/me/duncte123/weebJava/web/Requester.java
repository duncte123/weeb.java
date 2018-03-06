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
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public final class Requester {

    private static final OkHttpClient client;
    private static final String USER_AGENT = "Mozilla/5.0 Weeb.java (v" +
            WeebApi.VERSION + ", https://github.com/duncte123/weeb.java)";

    static {
        client = new OkHttpClient.Builder()
                .readTimeout(10L, TimeUnit.SECONDS)
                .writeTimeout(10L, TimeUnit.SECONDS)
                .build();
    }



    private Requester() {}
}
