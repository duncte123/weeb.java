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

package me.duncte123.weebJava.models.reputation.responses;

import me.duncte123.weebJava.models.WeebResponse;
import me.duncte123.weebJava.models.reputation.objects.ReputationSettings;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class ReputationSettingsResponse extends WeebResponse {

    private final ReputationSettings settings;

    private ReputationSettingsResponse(int status, String message, ReputationSettings settings) {
        super(status, message);
        this.settings = settings;
    }

    /**
     * @return The {@link ReputationSettings settings} for your account
     */
    public ReputationSettings getSettings() {
        return settings;
    }

    public static ReputationSettingsResponse fromJson(JSONObject jsonObject) {
        return new ReputationSettingsResponse(
                jsonObject.getInt("status"),
                jsonObject.getString("message"),
                ReputationSettings.fromJson(jsonObject.getJSONObject("settings"))
        );
    }
}
