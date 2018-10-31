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

import com.google.gson.JsonElement;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class SettingsObject {

    private final String id;
    private final String type;
    private final String accountId;
    private final JsonElement data;

    private JSONObject jsonData;
    private String dataRaw;

    public SettingsObject(String id, String type, String accountId, JsonElement data) {
        this.id = id;
        this.type = type;
        this.accountId = accountId;
        this.data = data;
    }

    /**
     * @return The id for this setting
     */
    public String getId() {
        return id;
    }

    /**
     * @return The type for this setting
     */
    public String getType() {
        return type;
    }

    /**
     * @return The account id for the token
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @return The data that you stored as a string
     */
    public String getDataRaw() {

        if (dataRaw == null) {
            dataRaw = data.getAsJsonObject().toString();
        }

        return dataRaw;
    }

    /**
     * @return The data that you stored
     */
    public JSONObject getData() {

        if (jsonData == null) {
            jsonData = new JSONObject(getDataRaw());
        }

        return jsonData;
    }
}
