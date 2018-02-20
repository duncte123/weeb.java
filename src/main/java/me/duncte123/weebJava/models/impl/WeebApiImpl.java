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
import me.duncte123.weebJava.models.ImageTag;
import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.models.WeebImage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeebApiImpl implements WeebApi {

    private final TokenType tokenType;
    private final String token;
    private final OkHttpClient client;
	private static final String USER_AGENT = "Mozilla/5.0 Weeb.java (v" +
            WeebApi.VERSION + ", https://github.com/duncte123/weeb.java)";

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
        Ason res = executeRequest(getAPIBaseUrl(), "/types", "hidden=" + hidden);
        if(res == null)
            return null;

        AsonArray<String> returnData =  res.getJsonArray("types");
        return returnData.toList();
    }


    @Override
    public WeebImage getRandomImage(String type, String tags, boolean hidden, String NSFW, String filetype) throws ImageNotFoundException {
        List<String> query = new ArrayList<>();

        if(type != null)
            query.add("type=" + type);
        if(tags != null)
            query.add("tags=" + tags);

        query.add("hidden=" + hidden);

        if(NSFW != null)
            query.add("nsfw=" + NSFW);
        if(filetype != null)
            query.add("filetype=" + filetype);

        Ason response = executeRequest(getAPIBaseUrl(), "/random", query.toArray(new String[0]));

        if(response == null)
            return null;

        if(response.getInt("status") != 404)
            return getImageFromResponse(response);
        else
            throw new ImageNotFoundException(response.getString("message"));
    }

    @Override
    public WeebImage getImageById(String imageId) throws ImageNotFoundException {
        if(imageId == null || imageId.isEmpty())
            throw new IllegalArgumentException("imageId cannot be null or empty");

        Ason response = executeRequest(getAPIBaseUrl(), "/info/" + imageId);

        if(response == null)
            return null;

        if(response.getInt("status") != 404)
            return getImageFromResponse(response);
        else
            throw new ImageNotFoundException(response.getString("message"));
    }

    private WeebImage getImageFromResponse(Ason response) {
        AsonArray<Ason> responseTags = response.getJsonArray("tags");
        List<ImageTag> imageTags = new ArrayList<>();
        responseTags.forEach(it -> imageTags.add(
                new ImageTagImpl(
                        it.getString("name"),
                        it.getBool("hidden"),
                        it.getString("user")
                )
        ));

        return new WeebImageImpl(
                response.getString("id"),
                response.getString("baseType"),
                response.getString("mimeType"),
                response.getString("account"),
                response.getBool("hidden"),
                response.getBool("nsfw"),
                imageTags,
                response.getString("url")

        );
    }

    private Ason executeRequest(String apiBase, String path, String... query) {

        try {
            Response res =  client.newCall(
                    new Request.Builder()
                    .url(
                            String.format("%s%s%s",
                                apiBase,
                                path,
                                query == null || query.length == 0 ? "" : "?" + StringUtils.join(query, "&")
                            )
                    )
                    .get()
                    .header("Authorization", getCompiledToken())
					.addHeader("User-Agent", USER_AGENT)
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
