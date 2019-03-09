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

package me.duncte123.weebJava.models.impl;

import com.github.natanbc.reliqua.Reliqua;
import com.github.natanbc.reliqua.request.PendingRequest;
import me.duncte123.weebJava.helpers.IOHelper;
import me.duncte123.weebJava.helpers.QueryBuilder;
import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.models.image.WeebImage;
import me.duncte123.weebJava.models.image.response.ImageTypesResponse;
import me.duncte123.weebJava.models.reputation.ReputationManager;
import me.duncte123.weebJava.models.reputation.impl.ReputationManagerImpl;
import me.duncte123.weebJava.models.settings.SettingsManager;
import me.duncte123.weebJava.models.settings.impl.SettingsManagerImpl;
import me.duncte123.weebJava.types.*;
import me.duncte123.weebJava.web.ErrorUtils;
import me.duncte123.weebJava.web.RequestManager;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static me.duncte123.weebJava.web.ErrorUtils.toJSONObject;

public class WeebApiImpl extends Reliqua implements WeebApi {

    private final TokenType tokenType;
    private final String token;
    private final Endpoint endpoint;

    private final RequestManager manager;

    private ReputationManager reputationManager;
    private SettingsManager settingsManager;

    public WeebApiImpl(TokenType tokenType, String token, Endpoint endpoint, String appName) {
        super(new OkHttpClient.Builder()
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .build());

        this.tokenType = tokenType;
        this.token = token;
        this.endpoint = endpoint;

        this.manager = new RequestManager(appName);
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
    public String getAPIBaseUrl() {
        return endpoint.getUrl();
    }

    @Override
    public PendingRequest<ImageTypesResponse> getTypes(HiddenMode hidden, NSFWMode nsfw, PreviewMode preview) {

        QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl()).append("/images/types");

        if (hidden != null) {
            hidden.appendTo(builder);
        }

        if (nsfw != null) {
            nsfw.appendTo(builder);
        }

        if (preview != null) {
            preview.appendTo(builder);
        }

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken()))
                .setRateLimiter(getRateLimiter("/images/types"))
                .build(
                        (response) -> ImageTypesResponse.fromJson(toJSONObject(response)),
                        ErrorUtils::handleError
                );
    }

    @Override
    public PendingRequest<List<String>> getTags(HiddenMode hidden, NSFWMode nsfw) {

        QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl()).append("/images/tags");

        if (hidden != null)
            hidden.appendTo(builder);

        if (nsfw != null)
            nsfw.appendTo(builder);

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken()))
                .setRateLimiter(getRateLimiter("/images/tags"))
                .build(
                        (response) -> {
                            List<String> tags = new ArrayList<>();

                            toJSONObject(response).getJSONArray("tags").forEach(
                                    (it) -> tags.add(String.valueOf(it))
                            );

                            return tags;
                        },
                        ErrorUtils::handleError
                );
    }

    @Override
    public PendingRequest<WeebImage> getRandomImage(String type, List<String> tags, NSFWMode nsfw, HiddenMode hidden, FileType fileType) {

        QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl()).append("/images/random");

        if (type != null && !type.isEmpty())
            builder.append("type", type);

        if (!tags.isEmpty())
            builder.append("tags", StringUtils.join(tags, ","));

        if (nsfw != null) {
            nsfw.appendTo(builder);
        }

        if (hidden != null) {
            hidden.appendTo(builder);
        }

        if (fileType != null) {
            fileType.appendTo(builder);
        }

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken()))
                .setRateLimiter(getRateLimiter("/images/random"))
                .build(
                        (response) -> WeebImage.fromJson(toJSONObject(response)),
                        ErrorUtils::handleError
                );
    }

    @Override
    public PendingRequest<WeebImage> getImageInfo(String imageId) {
        return createRequest(
                manager.prepareGet(
                        new QueryBuilder().append(getAPIBaseUrl()).append("/info/").append(imageId).build(),
                        getCompiledToken()
                ))
                .setRateLimiter(getRateLimiter("/info"))
                .build(
                        (response) -> WeebImage.fromJson(toJSONObject(response)),
                        ErrorUtils::handleError
                );
    }

    @Override
    public PendingRequest<byte[]> generateSimple(GenerateType type, Color face, Color hair) {

        QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl()).append("/auto-image/generate");

        if (type != null)
            type.appendTo(builder);

        if (face != null)
            builder.append("face", colorToHex(face));

        if (hair != null)
            builder.append("hair", colorToHex(hair));

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken()))
                .setRateLimiter(getRateLimiter("/auto-image/generate"))
                .build(IOHelper::read, ErrorUtils::handleError);
    }

    @Override
    public PendingRequest<byte[]> generateDiscordStatus(StatusType status, String avatar) {

        QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl()).append("/auto-image/discord-status");

        if (status != null)
            status.appendTo(builder);

        if (avatar != null && !avatar.isEmpty())
            builder.append("avatar", avatar);

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken()))
                .setRateLimiter(getRateLimiter("/auto-image/discord-status"))
                .build(IOHelper::read, ErrorUtils::handleError);
    }

    @Override
    public PendingRequest<byte[]> generateLicense(String title, String avatar, String[] badges, String[] widgets) {
        JSONObject data = new JSONObject()
                .put("title", title)
                .put("avatar", avatar);

        if (badges.length > 3 || widgets.length > 3)
            throw new IllegalArgumentException("Size badges and widgets cannot be higher than 3");

        if (badges.length > 0)
            data.put("badges", badges);

        if (widgets.length > 0)
            data.put("widgets", widgets);

        return createRequest(
                manager.preparePOST(
                        new QueryBuilder().append(getAPIBaseUrl()).append("/auto-image/license").build(),
                        data.toString(),
                        getCompiledToken()
                ))
                .setRateLimiter(getRateLimiter("/auto-image/license"))
                .build(IOHelper::read, ErrorUtils::handleError);
    }

    @Override
    public PendingRequest<byte[]> generateWaifuinsult(String avatar) {
        return createRequest(
                manager.preparePOST(
                        new QueryBuilder().append(getAPIBaseUrl()).append("/auto-image/waifu-insult").build(),
                        new JSONObject().put("avatar", avatar).toString(),
                        getCompiledToken()
                ))
                .setRateLimiter(getRateLimiter("/auto-image/waifu-insult"))
                .build(IOHelper::read, ErrorUtils::handleError);
    }

    @Override
    public PendingRequest<byte[]> generateLoveship(String targetOne, String targetTwo) {

        JSONObject data = new JSONObject()
                .put("targetOne", targetOne)
                .put("targetTwo", targetTwo);

        return createRequest(
                manager.preparePOST(
                        new QueryBuilder().append(getAPIBaseUrl()).append("/auto-image/love-ship").build(),
                        data.toString(),
                        getCompiledToken()
                ))
                .setRateLimiter(getRateLimiter("/auto-image/love-ship"))
                .build(IOHelper::read, ErrorUtils::handleError);
    }

    private String colorToHex(Color color) {
        return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    @Override
    public ReputationManager getReputationManager() {
        if (reputationManager == null)
            reputationManager = new ReputationManagerImpl(getClient(), getAPIBaseUrl(), manager, getCompiledToken());
        return reputationManager;
    }

    @Override
    public SettingsManager getSettingsManager() {
        if (settingsManager == null)
            settingsManager = new SettingsManagerImpl(getClient(), getAPIBaseUrl(), manager, getCompiledToken());
        return settingsManager;
    }

}
