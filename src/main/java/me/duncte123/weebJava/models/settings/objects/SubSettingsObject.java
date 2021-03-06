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

package me.duncte123.weebJava.models.settings.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubSettingsObject extends SettingsObject {

    private final String subId;
    private final String subType;

    @JsonCreator
    public SubSettingsObject(
            @JsonProperty("id") String id,
            @JsonProperty("type") String type,
            @JsonProperty("accountId") String accountId,
            @JsonProperty("data") JsonNode data,
            @JsonProperty("subId") String subId,
            @JsonProperty("subType") String subType
    ) {
        super(id, type, accountId, data);
        this.subId = subId;
        this.subType = subType;
    }

    public String getSubId() {
        return subId;
    }

    public String getSubType() {
        return subType;
    }
}
