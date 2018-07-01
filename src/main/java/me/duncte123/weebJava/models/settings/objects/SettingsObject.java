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

import com.google.gson.JsonObject;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class SettingsObject {

    private String id;
    private String type;
    private String accountId;
    private JsonObject data;


    /**
     *
     * @return The id for this setting
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return The type for this setting
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return The account id for the token
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     *
     * @return The data that you stored
     */
    public JSONObject getData() {
        return new JSONObject(data.toString());
    }
}
