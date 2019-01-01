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

package me.duncte123.weebJava.models.image;

import org.json.JSONObject;

@SuppressWarnings("unused")
public class ImageTag {

    private final String name;
    private final boolean hidden;
    private final String user;

    private ImageTag(String name, boolean hidden, String user) {
        this.name = name;
        this.hidden = hidden;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getUser() {
        return user;
    }

    public String toString() {
        return "ImageTag(" + name + ")";
    }

    public static ImageTag fromJson(JSONObject jsonObject) {
        return new ImageTag(
                jsonObject.getString("name"),
                jsonObject.getBoolean("hidden"),
                jsonObject.getString("user")
        );
    }
}
