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

import com.afollestad.ason.Ason;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class SettingsObject {

    private String id;
    private String type;
    private String accountId;
    private Ason data;


    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getAccountId() {
        return accountId;
    }

    public Ason getData() {
        return data;
    }

    /*public void setData(Ason data) {
        this.data = data;
    }

    *//**
     * This allows you to set the data
     * @param data
     *//*
    public void setData(JSONObject data) {
        this.data = new Ason(data);
    }*/
}
