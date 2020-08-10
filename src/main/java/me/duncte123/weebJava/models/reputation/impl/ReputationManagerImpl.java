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

package me.duncte123.weebJava.models.reputation.impl;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.natanbc.reliqua.Reliqua;
import com.github.natanbc.reliqua.request.PendingRequest;
import me.duncte123.weebJava.helpers.QueryBuilder;
import me.duncte123.weebJava.models.reputation.ReputationManager;
import me.duncte123.weebJava.models.reputation.objects.ReputationSettings;
import me.duncte123.weebJava.models.reputation.responses.GiveUserReputationResponse;
import me.duncte123.weebJava.models.reputation.responses.ReputationResponse;
import me.duncte123.weebJava.models.reputation.responses.ReputationSettingsResponse;
import me.duncte123.weebJava.web.ErrorUtils;
import me.duncte123.weebJava.web.RequestManager;
import okhttp3.OkHttpClient;

import static me.duncte123.weebJava.web.ErrorUtils.getItem;

public class ReputationManagerImpl extends Reliqua implements ReputationManager {

    private final String apiBase;
    private final String token;
    private final RequestManager manager;
    private final JsonMapper mapper;
    private String botId;

    public ReputationManagerImpl(OkHttpClient client, JsonMapper mapper, String apiBase, RequestManager manager, String token) {
        super(client, null, true);

        this.apiBase = apiBase;
        this.token = token;
        this.manager = manager;
        this.mapper = mapper;
    }

    @Override
    public ReputationManager setBotId(String botId) {
        this.botId = botId;
        return this;
    }

    @Override
    public String getBotId() {
        if (botId == null || botId.isEmpty())
            throw new NullPointerException("botId is not set, use ReputationManager#seteBotId to set it.");
        return botId;
    }

    @Override
    public PendingRequest<ReputationResponse> getReputationForUser(String userId) {
        if (userId == null || userId.isEmpty())
            throw new IllegalArgumentException("userId cannot be null");
        String url = apiBase + "/reputation/" + getBotId() + "/" + userId;

        return createRequest(
                manager.prepareGet(url, token)
        ).build(
                (response) -> getItem(response, this.mapper, ReputationResponse.class),
                 ErrorUtils::handleError
        );
    }

    @Override
    public PendingRequest<GiveUserReputationResponse> giveUserReputation(String sourceUserId, String targetUserId) {

        if (sourceUserId == null || sourceUserId.isEmpty())
            throw new IllegalArgumentException("sourceUserId cannot be null");
        if (targetUserId == null || targetUserId.isEmpty())
            throw new IllegalArgumentException("targetUserId cannot be null");

        String url = apiBase + "/reputation/" + getBotId() + "/" + targetUserId;

        return createRequest(
                manager.preparePOST(
                        url,
                        this.mapper.createObjectNode().put("source_user", sourceUserId),
                        token
                )
        ).build(
                (response) -> getItem(response, this.mapper, GiveUserReputationResponse.class),
                 ErrorUtils::handleError
        );
    }

    @Override
    public PendingRequest<ReputationResponse> resetUserReputation(String userId, boolean resetCooldown) {
        if (userId == null || userId.isEmpty())
            throw new IllegalArgumentException("userId cannot be null");
        QueryBuilder builder = new QueryBuilder()
                .append(apiBase).append("/reputation/").append(getBotId()).append("/").append(userId).append("/reset");

        builder.append("cooldown", String.valueOf(resetCooldown));

        return createRequest(
                manager.prepareGet(builder.build(), token)
        ).build(
                (response) -> getItem(response, this.mapper, ReputationResponse.class),
                 ErrorUtils::handleError
        );
    }

    @Override
    public PendingRequest<ReputationResponse> increaseUserReputation(String userId, int amount) {
        if (userId == null || userId.isEmpty())
            throw new IllegalArgumentException("userId cannot be null");
        QueryBuilder builder = new QueryBuilder()
                .append(apiBase).append("/reputation/").append(getBotId()).append("/").append(userId).append("/increase");

        return createRequest(
                manager.preparePOST(
                        builder.build(),
                        this.mapper.createObjectNode().put("increase", amount),
                        token
                )
        ).build(
                (response) -> getItem(response, this.mapper, ReputationResponse.class),
                 ErrorUtils::handleError
        );
    }

    @Override
    public PendingRequest<ReputationResponse> decreaseUserReputation(String userId, int amount) {
        if (userId == null || userId.isEmpty())
            throw new IllegalArgumentException("userId cannot be null");
        QueryBuilder builder = new QueryBuilder()
                .append(apiBase).append("/reputation/").append(getBotId()).append("/").append(userId).append("/decrease");

        return createRequest(
                manager.preparePOST(
                        builder.build(),
                        this.mapper.createObjectNode().put("decrease", amount),
                        token
                )
        ).build(
                (response) -> getItem(response, this.mapper, ReputationResponse.class),
                 ErrorUtils::handleError
        );
    }

    @Override
    public PendingRequest<ReputationSettingsResponse> getSettings() {
        String url = apiBase + "/reputation/settings";

        return createRequest(
                manager.prepareGet(url, token)
        ).build(
                (response) -> getItem(response, this.mapper, ReputationSettingsResponse.class),
                 ErrorUtils::handleError
        );
    }

    @Override
    public PendingRequest<ReputationSettingsResponse> setSettings(ReputationSettings settings) {

        String url = apiBase + "/reputation/settings";

        return createRequest(
                manager.preparePOST(
                        url,
                        settings.toJson(this.mapper),
                        token
                )
        ).build(
                (response) -> getItem(response, this.mapper, ReputationSettingsResponse.class),
                 ErrorUtils::handleError
        );
    }
}
