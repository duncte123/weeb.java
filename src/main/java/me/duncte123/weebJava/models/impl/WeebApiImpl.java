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
import me.duncte123.weebJava.exceptions.ImageNotFoundException;
import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.models.image.ImageGenerator;
import me.duncte123.weebJava.models.image.ImageTag;
import me.duncte123.weebJava.models.image.WeebImage;
import me.duncte123.weebJava.models.impl.image.ImageGeneratorImpl;
import me.duncte123.weebJava.models.impl.image.ImageTagImpl;
import me.duncte123.weebJava.models.impl.image.WeebImageImpl;
import me.duncte123.weebJava.types.ApiUrl;
import me.duncte123.weebJava.types.TokenType;
import me.duncte123.weebJava.web.Requester;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WeebApiImpl implements WeebApi {

    private final Requester requester = new Requester();

    private final TokenType tokenType;
    private final String token;
    private final ApiUrl apiUrl;

    private final List<String> tagsCache = new ArrayList<>();
    private final List<String> typesCache = new ArrayList<>();

    public WeebApiImpl(TokenType tokenType, String token, ApiUrl apiUrl) {
        this.tokenType = tokenType;
        this.token = token;
        this.apiUrl = apiUrl;
    }

    @Override
    public TokenType getTokenType() {
        return tokenType;
    }

    @Override
    public String getAPIBaseUrl() {
        return apiUrl.getUrl();
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public List<String> getTagsCached(boolean hidden, boolean refresh) {

        if (refresh || this.tagsCache.isEmpty()) {

            this.tagsCache.clear();

            Ason res = executeRequestSync(getAPIBaseUrl(), "/images/tags", "hidden=" + hidden);
            if (res == null)
                return null;

            AsonArray<String> returnData = res.getJsonArray("tags");
            List<String> tagsReturned = returnData.toList();
            this.tagsCache.addAll(tagsReturned);
            //System.out.println("made api request");
            return tagsReturned;
        }

        return this.tagsCache;
    }

    @Override
    public List<String> getTypesCached(boolean hidden, boolean refresh) {

        if (refresh || this.typesCache.isEmpty()) {

            this.typesCache.clear();

            Ason res = executeRequestSync(getAPIBaseUrl(), "/images/types", "hidden=" + hidden);
            if (res == null)
                return null;

            AsonArray<String> returnData = res.getJsonArray("types");
            List<String> typesReturned = returnData.toList();
            this.typesCache.addAll(typesReturned);
            //System.out.println("made api request");
            return typesReturned;
        }

        return this.typesCache;
    }

    @Override
    public WeebImage getRandomImage(String type, String tags, boolean hidden, String NSFW, String filetype) throws ImageNotFoundException {
        List<String> query = new ArrayList<>();

        if (type != null)
            query.add("type=" + type);
        if (tags != null)
            query.add("tags=" + tags);

        query.add("hidden=" + hidden);

        if (NSFW != null)
            query.add("nsfw=" + NSFW);
        if (filetype != null)
            query.add("filetype=" + filetype);

        Ason response = executeRequestSync(getAPIBaseUrl(), "/images/random", query.toArray(new String[0]));

        if (response == null)
            return null;

        if (response.getInt("status") != 404)
            return getImageFromResponse(response);
        else
            throw new ImageNotFoundException(response.getString("message"));
    }

    @Override
    public WeebImage getImageById(String imageId) throws ImageNotFoundException {
        if (imageId == null || imageId.isEmpty())
            throw new IllegalArgumentException("imageId cannot be null or empty");

        Ason response = executeRequestSync(getAPIBaseUrl(), "/images/info/" + imageId);

        if (response == null)
            return null;

        if (response.getInt("status") != 404)
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

    @Override
    public ImageGenerator getImageGenerator() {
        return new ImageGeneratorImpl(this);
    }
    /*private void executeRequestAsync(String apiBase, String path, Consumer<Ason> ason, String... query) {
        requester.requestAsync(new Request.Builder()
                .url(
                        String.format("%s%s%s",
                                apiBase,
                                path,
                                query == null || query.length == 0 ? "" : "?" + StringUtils.join(query, "&")
                        )
                )
                .get()
                .header("Authorization", getCompiledToken())
                .addHeader("User-Agent", Requester.USER_AGENT)
                .build(),
            (response) -> {
                try {
                    ason.accept(new Ason(Objects.requireNonNull(response.body()).string()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            },
            Throwable::printStackTrace
        );
    }*/

    private Ason executeRequestSync(String apiBase, String path, String... query) {

        try {
            Response res = requester.requestSync(
                    new Request.Builder()
                            .url(
                                    String.format("%s%s%s",
                                            apiBase,
                                            path,
                                            requester.toParams(query)
                                    )
                            )
                            .get()
                            .header("Authorization", getCompiledToken())
                            .addHeader("User-Agent", Requester.USER_AGENT)
                            .build()
            );

            return new Ason(Objects.requireNonNull(res.body()).string());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Requester getRequester() {
        return requester;
    }
}
