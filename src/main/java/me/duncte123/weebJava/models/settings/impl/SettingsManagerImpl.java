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

import com.afollestad.ason.Ason;
import com.github.natanbc.reliqua.Reliqua;
import com.github.natanbc.reliqua.request.PendingRequest;
import me.duncte123.weebJava.models.settings.SettingsManager;
import me.duncte123.weebJava.models.settings.responses.SettingsResponse;
import me.duncte123.weebJava.models.settings.responses.SubSettingsResponse;
import me.duncte123.weebJava.web.RequestManager;
import okhttp3.OkHttpClient;

import java.util.Objects;

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
    public PendingRequest<SettingsResponse> getSetting(String type, String id) {
        type = Objects.requireNonNull(type, "type cannot be null");
        id = Objects.requireNonNull(id, "id cannot be null");

        return createRequest(
                manager.prepareDelete(generateUrl(type, id), token)
        ).build(
                (response) -> Ason.deserialize(response.body().string(), SettingsResponse.class, true),
                RequestManager.WebUtilsErrorUtils::handleError
        );
    }

    @Override
    public PendingRequest<SettingsResponse> updateSetting(String type, String id) {
        return null;
    }

    @Override
    public PendingRequest<SettingsResponse> deleteSetting(String type, String id) {
        return null;
    }

    @Override
    public PendingRequest<SubSettingsResponse> listSubSettings(String type, String id, String subtype) {
        return null;
    }

    @Override
    public PendingRequest<SubSettingsResponse> getSubSetting(String type, String id, String subtype, String subId) {
        return null;
    }

    @Override
    public PendingRequest<SubSettingsResponse> updateSubSetting(String type, String id, String subtype, String subId) {
        return null;
    }

    @Override
    public PendingRequest<SubSettingsResponse> deleteSubSetting(String type, String id, String subtype, String subId) {
        return null;
    }

    private String generateUrl(String type, String id) {
        return apiBase + "/settings/" + type + "/" + id;
    }

    private String genereateSubUrl(String type, String id, String subType, String subid) {
        return generateUrl(type, id) + "/" + subType + "/" + subid;
    }
}
