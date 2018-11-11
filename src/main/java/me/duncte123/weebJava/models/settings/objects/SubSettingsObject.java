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

package me.duncte123.weebJava.models.settings.objects;

import org.json.JSONObject;

@SuppressWarnings("unused")
public class SubSettingsObject extends SettingsObject {

    private final String subId;
    private final String subType;

    private SubSettingsObject(String id, String type, String accountId, JSONObject data, String subId, String subType) {
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

    public static SubSettingsObject fromJson(JSONObject jsonObject) {
        return new SubSettingsObject(
                jsonObject.getString("id"),
                jsonObject.getString("type"),
                jsonObject.getString("accountId"),
                jsonObject.getJSONObject("data"),
                jsonObject.getString("subId"),
                jsonObject.getString("subType")
        );
    }
}
