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

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.natanbc.reliqua.Reliqua;
import com.github.natanbc.reliqua.request.PendingRequest;
import com.github.natanbc.reliqua.util.StatusCodeValidator;
import me.duncte123.weebJava.configs.ImageConfig;
import me.duncte123.weebJava.configs.LicenseConfig;
import me.duncte123.weebJava.configs.TagsConfig;
import me.duncte123.weebJava.configs.TypesConfig;
import me.duncte123.weebJava.helpers.IOHelper;
import me.duncte123.weebJava.helpers.QueryBuilder;
import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.models.image.WeebImage;
import me.duncte123.weebJava.models.image.response.ImageTypesResponse;
import me.duncte123.weebJava.models.reputation.ReputationManager;
import me.duncte123.weebJava.models.reputation.impl.ReputationManagerImpl;
import me.duncte123.weebJava.models.settings.SettingsManager;
import me.duncte123.weebJava.models.settings.impl.SettingsManagerImpl;
import me.duncte123.weebJava.types.Endpoint;
import me.duncte123.weebJava.types.GenerateType;
import me.duncte123.weebJava.types.StatusType;
import me.duncte123.weebJava.types.TokenType;
import me.duncte123.weebJava.web.ErrorUtils;
import me.duncte123.weebJava.web.RequestManager;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static me.duncte123.weebJava.web.ErrorUtils.getItem;
import static me.duncte123.weebJava.web.ErrorUtils.toJSONObject;

public class WeebApiImpl extends Reliqua implements WeebApi {

    private final TokenType tokenType;
    private final String token;
    private final Endpoint endpoint;

    private final RequestManager manager;

    private ReputationManager reputationManager;
    private SettingsManager settingsManager;

    private final JsonMapper mapper = new JsonMapper();

    public WeebApiImpl(TokenType tokenType, String token, Endpoint endpoint, String appName) {
        super();

        this.tokenType = tokenType;
        this.token = token;
        this.endpoint = endpoint;

        this.manager = new RequestManager(appName, mapper);
    }

    @Nonnull
    @Override
    public TokenType getTokenType() {
        return tokenType;
    }

    @Nonnull
    @Override
    public String getToken() {
        return token;
    }

    @Nonnull
    @Override
    public String getAPIBaseUrl() {
        return endpoint.getUrl();
    }


    @Nonnull
    @Override
    public PendingRequest<ImageTypesResponse> getTypes(@Nonnull TypesConfig config) {
        Objects.requireNonNull(config, "The config cannot be null");

        final QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl())
                .append("/images/types");

        if (config.getHiddenMode() != null) {
            config.getHiddenMode().appendTo(builder);
        }

        if (config.getNsfwMode() != null) {
            config.getNsfwMode().appendTo(builder);
        }

        if (config.getPreviewMode() != null) {
            config.getPreviewMode().appendTo(builder);
        }

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken()))
                .setRateLimiter(getRateLimiter("/images/types"))
                .build(
                        (response) -> getItem(response, this.mapper, ImageTypesResponse.class),
                        ErrorUtils::handleError
                );
    }

    @Nonnull
    @Override
    public PendingRequest<List<String>> getTags(@Nonnull TagsConfig config) {
        Objects.requireNonNull(config, "The config cannot be null");

        final QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl())
                .append("/images/tags");

        if (config.getHiddenMode() != null) {
            config.getHiddenMode().appendTo(builder);
        }

        if (config.getNsfwMode() != null) {
            config.getNsfwMode().appendTo(builder);
        }

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken()))
                .setRateLimiter(getRateLimiter("/images/tags"))
                .build(
                        (response) -> {
                            List<String> tags = new ArrayList<>();

                            toJSONObject(response).get("tags").forEach(
                                    (it) -> tags.add(String.valueOf(it))
                            );

                            return tags;
                        },
                        ErrorUtils::handleError
                );
    }

    @Nonnull
    @Override
    public PendingRequest<WeebImage> getRandomImage(@Nonnull ImageConfig config) {
        Objects.requireNonNull(config, "The config cannot be null");

        final QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl())
                .append("/images/random");

        if (config.getType() == null && config.getTags() == null) {
            throw new IllegalArgumentException("Either one of type or tags must be set");
        }

        if (config.getType() != null && !config.getType().isEmpty()) {
            builder.append("type", config.getType());
        }

        if (config.getTags() != null && !config.getTags().isEmpty()) {
            builder.append("tags", StringUtils.join(config.getTags(), ","));
        }

        if (config.getNsfwMode() != null) {
            config.getNsfwMode().appendTo(builder);
        }

        if (config.getHiddenMode() != null) {
            config.getHiddenMode().appendTo(builder);
        }

        if (config.getFileType() != null) {
            config.getFileType().appendTo(builder);
        }

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken()))
                .setRateLimiter(getRateLimiter("/images/random"))
                .build(
                        (response) -> getItem(response, this.mapper, WeebImage.class),
                        ErrorUtils::handleError
                );
    }

    @Nonnull
    @Override
    public PendingRequest<WeebImage> getImageInfo(@Nonnull String imageId) {
        return createRequest(
                manager.prepareGet(
                        new QueryBuilder().append(getAPIBaseUrl()).append("/info/").append(imageId).build(),
                        getCompiledToken()
                ))
                .setRateLimiter(getRateLimiter("/info"))
                .build(
                        (response) -> getItem(response, this.mapper, WeebImage.class),
                        ErrorUtils::handleError
                );
    }

    @Nonnull
    @Override
    public PendingRequest<byte[]> generateSimple(@Nonnull GenerateType type, @Nullable Color face, @Nullable Color hair) {

        QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl()).append("/auto-image/generate");

        type.appendTo(builder);

        if (face != null) {
            builder.append("face", colorToHex(face));
        }

        if (hair != null) {
            builder.append("hair", colorToHex(hair));
        }

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken()))
                .setRateLimiter(getRateLimiter("/auto-image/generate"))
                .build(IOHelper::read, ErrorUtils::handleError);
    }

    @Nonnull
    @Override
    public PendingRequest<byte[]> generateDiscordStatus(StatusType status, String avatar) {

        QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl()).append("/auto-image/discord-status");

        if (status != null) {
            status.appendTo(builder);
        }

        if (avatar != null && !avatar.isEmpty()) {
            builder.append("avatar", avatar);
        }

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken()))
                .setRateLimiter(getRateLimiter("/auto-image/discord-status"))
                .setStatusCodeValidator(StatusCodeValidator.ACCEPT_2XX)
                .build(IOHelper::read, ErrorUtils::handleError);
    }

    @Nonnull
    @Override
    public PendingRequest<byte[]> generateLicense(@Nonnull LicenseConfig config) {
        Objects.requireNonNull(config, "The config cannot be null");

        final ObjectNode data = this.mapper.createObjectNode()
                .put("title", config.getTitle())
                .put("avatar", config.getAvatar());

        final String[] badges = config.getBadges();
        final String[] widgets = config.getWidgets();

        if (badges.length > 3 || widgets.length > 3) {
            throw new IllegalArgumentException("Size badges and widgets cannot be higher than 3");
        }

        if (badges.length > 0) {
            final ArrayNode badgesArray = data.putArray("badges");

            for (final String badge : badges) {
                badgesArray.add(badge);
            }
        }

        if (widgets.length > 0) {
            final ArrayNode widgetsArray = data.putArray("widgets");

            for (final String widget : widgets) {
                widgetsArray.add(widget);
            }
        }

        return createRequest(
                manager.preparePOST(
                        new QueryBuilder().append(getAPIBaseUrl()).append("/auto-image/license").build(),
                        data,
                        getCompiledToken()
                ))
                .setRateLimiter(getRateLimiter("/auto-image/license"))
                .setStatusCodeValidator(StatusCodeValidator.ACCEPT_2XX)
                .build(IOHelper::read, ErrorUtils::handleError);
    }

    @Nonnull
    @Override
    public PendingRequest<byte[]> generateWaifuinsult(@Nonnull String avatar) {
        return createRequest(
                manager.preparePOST(
                        new QueryBuilder().append(getAPIBaseUrl()).append("/auto-image/waifu-insult").build(),
                        this.mapper.createObjectNode().put("avatar", avatar),
                        getCompiledToken()
                ))
                .setRateLimiter(getRateLimiter("/auto-image/waifu-insult"))
                .setStatusCodeValidator(StatusCodeValidator.ACCEPT_2XX)
                .build(IOHelper::read, ErrorUtils::handleError);
    }

    @Nonnull
    @Override
    public PendingRequest<byte[]> generateLoveship(@Nonnull String targetOne, @Nonnull String targetTwo) {
        final ObjectNode data = this.mapper.createObjectNode()
                .put("targetOne", targetOne)
                .put("targetTwo", targetTwo);

        return createRequest(
                manager.preparePOST(
                        new QueryBuilder().append(getAPIBaseUrl()).append("/auto-image/love-ship").build(),
                        data,
                        getCompiledToken()
                ))
                .setRateLimiter(getRateLimiter("/auto-image/love-ship"))
                .setStatusCodeValidator(StatusCodeValidator.ACCEPT_2XX)
                .build(IOHelper::read, ErrorUtils::handleError);
    }

    private String colorToHex(Color color) {
        return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    @Nonnull
    @Override
    public ReputationManager getReputationManager() {
        if (reputationManager == null) {
            reputationManager = new ReputationManagerImpl(getClient(), this.mapper, getAPIBaseUrl(), manager, getCompiledToken());
        }

        return reputationManager;
    }

    @Nonnull
    @Override
    public SettingsManager getSettingsManager() {
        if (settingsManager == null) {
            settingsManager = new SettingsManagerImpl(getClient(), this.mapper, getAPIBaseUrl(), manager, getCompiledToken());
        }

        return settingsManager;
    }

    @Override
    public void shutdown() {
        this.getClient().connectionPool().evictAll();
        this.getClient().dispatcher().executorService().shutdown();
    }
}
