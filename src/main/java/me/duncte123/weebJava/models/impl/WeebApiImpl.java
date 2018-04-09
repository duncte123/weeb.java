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
import me.duncte123.weebJava.models.image.response.TypesResponse;
import me.duncte123.weebJava.models.impl.image.ImageGeneratorImpl;
import me.duncte123.weebJava.models.impl.image.ImageTagImpl;
import me.duncte123.weebJava.models.impl.image.WeebImageImpl;
import me.duncte123.weebJava.models.impl.image.response.TypesResponseImpl;
import me.duncte123.weebJava.types.ApiUrl;
import me.duncte123.weebJava.types.HiddenMode;
import me.duncte123.weebJava.types.NSFWType;
import me.duncte123.weebJava.types.TokenType;
import me.duncte123.weebJava.web.RequestManager;
import okhttp3.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class WeebApiImpl implements WeebApi {

    private final RequestManager requestManager;

    private final TokenType tokenType;
    private final String token;
    private final ApiUrl apiUrl;

    private ImageGenerator imageGenerator = null;

    public WeebApiImpl(TokenType tokenType, String token, ApiUrl apiUrl, String appName) {
        this.tokenType = tokenType;
        this.token = token;
        this.apiUrl = apiUrl;
        this.requestManager = new RequestManager(new OkHttpClient.Builder()
                .readTimeout(10L, TimeUnit.SECONDS)
                .writeTimeout(10L, TimeUnit.SECONDS)
                .build(), appName);

        System.out.println(requestManager.USER_AGENT);
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
    public List<String> getTags(HiddenMode hidden, NSFWType nsfw) {

        List<String> query = new ArrayList<>();

        if(hidden != null)
            query.add("hidden=" + hidden);

        if (nsfw != null)
            query.add("nsfw=" + nsfw.getType());

        Ason res = executeRequestSync("/images/tags", query.toArray(new String[0]));

        AsonArray<String> returnData = res.getJsonArray("tags");
        //System.out.println("made api request");
        return returnData.toList();
    }

    @Override
    public TypesResponse getTypes(HiddenMode hidden, NSFWType nsfw, boolean preview) {

        List<String> query = new ArrayList<>();
        if(hidden != null)
            query.add("hidden=" + hidden);

        if (nsfw != null)
            query.add("nsfw=" + nsfw.getType());
        query.add("preview=" + preview);

        Ason res = executeRequestSync("/images/types", query.toArray(new String[0]));

        AsonArray<String> returnData = res.getJsonArray("types");
        List<String> typesReturned = returnData.toList();
        List<TypesResponse.PartialImage> previewData = new ArrayList<>();
        AsonArray<Ason> previewReturned = res.getJsonArray("preview");
        previewReturned.forEach(
                ason -> previewData.add(
                        new TypesResponseImpl.PartialImageImpl(
                                ason.getString("url"),
                                ason.getString("id"),
                                ason.getString("fileType"),
                                ason.getString("baseType")
                        )
                )
        );
        //System.out.println("made api request");
        return new TypesResponseImpl(
                typesReturned,
                previewData
        );
    }

    @Override
    public WeebImage getRandomImage(String type, String tags, boolean hidden, NSFWType NSFW, String filetype) throws ImageNotFoundException {
        List<String> query = new ArrayList<>();

        if (type != null)
            query.add("type=" + type);
        if (tags != null)
            query.add("tags=" + tags);

        query.add("hidden=" + hidden);

        if (NSFW != null)
            query.add("nsfw=" + NSFW.getType());
        if (filetype != null)
            query.add("filetype=" + filetype);

        Ason response = executeRequestSync("/images/random", query.toArray(new String[0]));

        if (response.getInt("status") != 404)
            return getImageFromResponse(response);
        else
            throw new ImageNotFoundException(response.getString("message"));
    }

    @Override
    public WeebImage getImageById(String imageId) throws ImageNotFoundException {
        if (imageId == null || imageId.isEmpty())
            throw new IllegalArgumentException("imageId cannot be null or empty");

        Ason response = executeRequestSync("/images/info/" + imageId);

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
                response.getString("url"),
                response.getString("source", null)
        );
    }

    @Override
    public ImageGenerator getImageGenerator() {
        if (imageGenerator == null)
            imageGenerator = new ImageGeneratorImpl(this);
        return imageGenerator;
    }

    private Ason executeRequestSync(String path, String... query) {
        return requestManager.createRequest(
                path,
                requestManager.prepareGet(String.format("%s%s%s",
                        getAPIBaseUrl(),
                        path,
                        requestManager.toParams(query)
                        ),
                        getCompiledToken()),
                200,
                (body) -> new Ason(Objects.requireNonNull(body).string())
        ).execute();
    }

    public RequestManager getRequestManager() {
        return requestManager;
    }
}
