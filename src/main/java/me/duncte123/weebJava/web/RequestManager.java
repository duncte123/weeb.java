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

package me.duncte123.weebJava.web;

import me.duncte123.weebJava.WeebInfo;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestManager {

    private final String USER_AGENT;

    public RequestManager(String appName) {
        USER_AGENT = appName.trim() + " (Weeb.java/" + WeebInfo.VERSION + ")";
    }

    public Request.Builder prepareGet(String url, String token) {
        return new Request.Builder()
                .url(url)
                .get()
                .header("Authorization", token)
                .addHeader("User-Agent", USER_AGENT);
    }

    public Request.Builder prepareDelete(String url, String token) {
        return prepareGet(url, token).delete();
    }

    public Request.Builder preparePOST(String url, String body, String token) {
        return new Request.Builder()
                .url(url)
                .post(RequestBody.create(MediaType.parse("application/json"), body))
                .header("Authorization", token)
                .addHeader("User-Agent", USER_AGENT);
    }

}
