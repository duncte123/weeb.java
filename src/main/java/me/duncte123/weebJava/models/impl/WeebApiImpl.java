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

package me.duncte123.weebJava.models.impl;

import com.afollestad.ason.Ason;
import com.afollestad.ason.AsonArray;
import me.duncte123.weebJava.TokenType;
import me.duncte123.weebJava.exceptions.ImageNotFoundException;
import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.models.WeebImage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeebApiImpl implements WeebApi {

    private final TokenType tokenType;
    private final String token;
    private final OkHttpClient client;

    public WeebApiImpl(TokenType tokenType, String token) {
        this.tokenType = tokenType;
        this.token = token;
        this.client = new OkHttpClient.Builder()
                .readTimeout(10L, TimeUnit.SECONDS)
                .writeTimeout(10L, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public TokenType getTokenType() {
        return tokenType;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public List<String> getTags(boolean hidden) {

        Ason res = executeRequest(getAPIBaseUrl(), "/tags", "hidden=" + hidden);
        if(res == null)
            return null;

        AsonArray<String> returnData =  res.getJsonArray("tags");
        return returnData.toList();
    }

    @Override
    public List<String> getTypes(boolean hidden) {
        return null;
    }


    @Override
    public WeebImage getRandomImage(String type, String tags, boolean hidden, String NSFW, String filetype) {
        return null;
    }

    @Override
    public WeebImage getImageById(String image) throws ImageNotFoundException {
        return null;
    }

    private Ason executeRequest(String apiBase, String path, String... query) {

        try {
            Response res =  client.newCall(
                    new Request.Builder()
                    .url(
                            String.format("%s%s%s",
                                apiBase,
                                path,
                                query.length == 0 ? "" : "?" + StringUtils.join(query, "&")
                            )
                    )
                    .get()
                    .header("Authorization", getCompiledToken())
                    .build()
            ).execute();

            return new Ason(res.body().string());
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
