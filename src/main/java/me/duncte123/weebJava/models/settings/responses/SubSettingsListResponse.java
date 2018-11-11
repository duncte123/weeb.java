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

package me.duncte123.weebJava.models.settings.responses;

import me.duncte123.weebJava.models.WeebResponse;
import me.duncte123.weebJava.models.settings.objects.SubSettingsObject;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class SubSettingsListResponse extends WeebResponse {

    private final List<SubSettingsObject> subsettings;

    private SubSettingsListResponse(int status, String message, List<SubSettingsObject> subsettings) {
        super(status, message);
        this.subsettings = subsettings;
    }

    /**
     * @return A list of {@link SubSettingsObject SubSettingsObjects}
     */
    public List<SubSettingsObject> getSubsettings() {
        return subsettings;
    }

    public static SubSettingsListResponse fromJson(JSONObject jsonObject) {
        List<SubSettingsObject> objects = new ArrayList<>();

        jsonObject.getJSONArray("subsettings").forEach(
                (item) -> objects.add(SubSettingsObject.fromJson((JSONObject) item))
        );

        return new SubSettingsListResponse(
                jsonObject.getInt("status"),
                jsonObject.optString("message"),
                objects
        );

    }
}
