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
import me.duncte123.weebJava.models.settings.objects.SubSettingsObject;

import java.util.List;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubSettingsListResponse extends WeebResponse {

    private final List<SubSettingsObject> subsettings;

    @JsonCreator
    public SubSettingsListResponse(
            @JsonProperty("id") int status,
            @JsonProperty("message") String message,
            @JsonProperty("subsettings") List<SubSettingsObject> subsettings
    ) {
        super(status, message);
        this.subsettings = subsettings;
    }

    /**
     * @return A list of {@link SubSettingsObject SubSettingsObjects}
     */
    public List<SubSettingsObject> getSubsettings() {
        return subsettings;
    }
}
