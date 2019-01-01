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

package me.duncte123.weebJava.models.reputation.responses;

import me.duncte123.weebJava.models.WeebResponse;
import me.duncte123.weebJava.models.reputation.objects.ReputationUser;
import org.json.JSONObject;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ReputationResponse extends WeebResponse {

    protected final ReputationUser user;

    ReputationResponse(int status, String message, ReputationUser user) {
        super(status, message);
        this.user = user;
    }

    /**
     * @return The corresponding user object for the reputation
     */
    public ReputationUser getUser() {
        return user;
    }

    public static ReputationResponse fromJson(JSONObject jsonObject) {
        return new ReputationResponse(
                jsonObject.getInt("status"),
                jsonObject.optString("message"),
                ReputationUser.fromJson(jsonObject.getJSONObject("user"))
        );
    }

}
