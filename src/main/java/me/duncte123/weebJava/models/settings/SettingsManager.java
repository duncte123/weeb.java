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

package me.duncte123.weebJava.models.settings;

import com.afollestad.ason.Ason;
import com.github.natanbc.reliqua.request.PendingRequest;
import me.duncte123.weebJava.models.settings.responses.SettingsResponse;
import me.duncte123.weebJava.models.settings.responses.SubSettingsResponse;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public interface SettingsManager {

    PendingRequest<SettingsResponse> getSetting(@NotNull String type, @NotNull String id);

    PendingRequest<SettingsResponse> updateSetting(@NotNull String type, @NotNull String id, @NotNull Ason data);

    default PendingRequest<SettingsResponse> updateSetting(@NotNull String type, @NotNull String id, @NotNull JSONObject data) {
        return updateSetting(type, id, new Ason(data));
    }

    PendingRequest<SettingsResponse> deleteSetting(@NotNull String type, @NotNull String id);


    PendingRequest<SubSettingsResponse> listSubSettings(String type, @NotNull String id, @NotNull String subtype);

    PendingRequest<SettingsResponse> getSubSetting(String type, String id, @NotNull String subtype, @NotNull String subId);

    PendingRequest<SettingsResponse> updateSubSetting(String type, String id, @NotNull String subtype, @NotNull String subId, @NotNull Ason data);

    default PendingRequest<SettingsResponse> updateSubSetting(String type, String id, @NotNull String subtype, @NotNull String subId, @NotNull JSONObject data) {
        return updateSubSetting(type, id, subtype, subId, new Ason(data));
    }

    PendingRequest<SettingsResponse> deleteSubSetting(String type, String id, @NotNull String subtype, @NotNull String subId);
}
