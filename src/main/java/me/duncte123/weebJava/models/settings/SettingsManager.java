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

import com.github.natanbc.reliqua.request.PendingRequest;
import me.duncte123.weebJava.models.settings.responses.SettingsResponse;
import me.duncte123.weebJava.models.settings.responses.SubSettingsResponse;

public interface SettingsManager {

    PendingRequest<SettingsResponse> getSetting(String type, String id);

    PendingRequest<SettingsResponse> updateSetting(String type, String id);

    PendingRequest<SettingsResponse> deleteSetting(String type, String id);


    PendingRequest<SubSettingsResponse> listSubSettings(String type, String id, String subtype);

    PendingRequest<SubSettingsResponse> getSubSetting(String type, String id, String subtype, String subId);

    PendingRequest<SubSettingsResponse> updateSubSetting(String type, String id, String subtype, String subId);

    PendingRequest<SubSettingsResponse> deleteSubSetting(String type, String id, String subtype, String subId);
}
