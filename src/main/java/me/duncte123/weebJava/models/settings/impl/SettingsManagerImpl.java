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

package me.duncte123.weebJava.models.settings.impl;

import com.github.natanbc.reliqua.Reliqua;
import com.github.natanbc.reliqua.request.PendingRequest;
import com.github.natanbc.reliqua.util.PendingRequestBuilder;
import me.duncte123.weebJava.helpers.WeebUtils;
import me.duncte123.weebJava.models.settings.SettingsManager;
import me.duncte123.weebJava.models.settings.responses.SettingsResponse;
import me.duncte123.weebJava.models.settings.responses.SubSettingsListResponse;
import me.duncte123.weebJava.models.settings.responses.SubSettingsResponse;
import me.duncte123.weebJava.web.ErrorUtils;
import me.duncte123.weebJava.web.RequestManager;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import static me.duncte123.weebJava.helpers.WeebUtils.isNullOrEmpty;

public class SettingsManagerImpl extends Reliqua implements SettingsManager {

    private final String apiBase;
    private final String token;
    private final RequestManager manager;

    public SettingsManagerImpl(OkHttpClient client, String apiBase, RequestManager manager, String token) {
        super(client, null, true);

        this.apiBase = apiBase;
        this.token = token;
        this.manager = manager;
    }

    @Override
    public PendingRequest<SettingsResponse> getSetting(@NotNull String type, @NotNull String id) {
        return getSubSetting(null, null, type, id);
    }

    @Override
    public PendingRequest<SettingsResponse> updateSetting(@NotNull String type, @NotNull String id, @NotNull JSONObject data) {
        return updateSubSetting(null, null, type, id, data);
    }

    @Override
    public PendingRequest<SettingsResponse> deleteSetting(@NotNull String type, @NotNull String id) {
        return deleteSubSetting(null, null, type, id);
    }

    @Override
    public PendingRequest<SubSettingsListResponse> listSubSettings(@NotNull String type, @NotNull String id, @NotNull String subtype) {
        final String url = generateUrl(type, id) + "/" + subtype;

        return createRequest(manager.prepareGet(url, token))
                .setRateLimiter(getRateLimiter(url))
                .build(
                        (response) -> WeebUtils.getClassFromJson(response, SubSettingsListResponse.class),
                        ErrorUtils::handleError
                );
    }

    @Override
    public PendingRequest<SettingsResponse> getSubSetting(String type, String id, @NotNull String subtype, @NotNull String subId) {
        final String url = generateUrl(type, id, subtype, subId);

        final PendingRequestBuilder builder = createRequest(manager.prepareGet(url, token))
                .setRateLimiter(getRateLimiter(url));

        if (isNullOrEmpty(type)) {
            return builder.build(
                    (response) -> WeebUtils.getClassFromJson(response, SettingsResponse.class),
                    ErrorUtils::handleError
            );
        } else {
            return builder.build(
                    (response) -> WeebUtils.getClassFromJson(response, SubSettingsResponse.class),
                    ErrorUtils::handleError
            );
        }
    }

    @Override
    public PendingRequest<SettingsResponse> updateSubSetting(String type, String id, @NotNull String subtype, @NotNull String subId, @NotNull JSONObject data) {
        final String url = generateUrl(type, id, subtype, subId);

        if (data.toString().length() > 10 * 1024)
            throw new IllegalArgumentException("Data must be below 10Kib");

        final PendingRequestBuilder builder = createRequest(manager.preparePOST(url, data, token))
                .setRateLimiter(getRateLimiter(url));

        if (isNullOrEmpty(type)) {
            return builder.build(
                    (response) -> WeebUtils.getClassFromJson(response, SettingsResponse.class),
                    ErrorUtils::handleError
            );
        } else {
            return builder.build(
                    (response) -> WeebUtils.getClassFromJson(response, SubSettingsResponse.class),
                    ErrorUtils::handleError
            );
        }
    }

    @Override
    public PendingRequest<SettingsResponse> deleteSubSetting(String type, String id, @NotNull String subtype, @NotNull String subId) {

        final String url = generateUrl(type, id, subtype, subId);

        final PendingRequestBuilder builder = createRequest(manager.prepareDelete(url, token))
                .setRateLimiter(getRateLimiter(url));

        if (isNullOrEmpty(type)) {
            return builder.build(
                    (response) -> WeebUtils.getClassFromJson(response, SettingsResponse.class),
                    ErrorUtils::handleError
            );
        } else {
            return builder.build(
                    (response) -> WeebUtils.getClassFromJson(response, SubSettingsResponse.class),
                    ErrorUtils::handleError
            );
        }
    }

    private String generateUrl(String type, String id, @NotNull String subType, @NotNull String subid) {
        String url = apiBase + "/settings/";
        if (!isNullOrEmpty(type) && !isNullOrEmpty(id)) {
            url += type + "/" + id + "/";
        }
        return url + subType + "/" + subid;
    }

    private String generateUrl(String type, String id) {
        return generateUrl(null, null, type, id);
    }
}
