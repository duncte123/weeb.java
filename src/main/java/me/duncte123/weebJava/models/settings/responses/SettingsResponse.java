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

package me.duncte123.weebJava.models.settings.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.duncte123.weebJava.models.WeebResponse;
import me.duncte123.weebJava.models.settings.objects.SettingsObject;
import me.duncte123.weebJava.models.settings.objects.SubSettingsObject;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SettingsResponse extends WeebResponse {
    private final SettingsObject setting;

    @JsonCreator
    public SettingsResponse(
            @JsonProperty("status") int status,
            @JsonProperty("message") String message,
            @JsonProperty("setting") SettingsObject setting
    ) {
        super(status, message);
        this.setting = setting;
    }

    /**
     * @return The settings object holding your data
     */
    public SettingsObject getSetting() {
        return setting;
    }

    /**
     * @return The sub-setting data if this setting is a sub setting
     */
    public SubSettingsObject getSubsetting() {
        throw new IllegalArgumentException("Can't get sub-settings on settings");
    }
}
