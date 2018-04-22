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

package me.duncte123.weebJava.models.reputation;

import com.github.natanbc.reliqua.request.PendingRequest;
import me.duncte123.weebJava.models.reputation.objects.ReputationSettings;
import me.duncte123.weebJava.models.reputation.responses.GiveUserReputationResponse;
import me.duncte123.weebJava.models.reputation.responses.ReputationResponse;
import me.duncte123.weebJava.models.reputation.responses.ReputationSettingsResponse;

@SuppressWarnings("unused")
public interface ReputationManager {

    ReputationManager setBotId(String botId);

    String getBotId();

    PendingRequest<ReputationResponse> getReputationForUser(String userId);

    PendingRequest<GiveUserReputationResponse> giveUserReputation(String sourceUserId, String targetUserId);

    default PendingRequest<ReputationResponse> resetUserReputation(String userId) {
        return resetUserReputation(userId, false);
    }
    PendingRequest<ReputationResponse> resetUserReputation(String userId, boolean resetCooldown);

    PendingRequest<ReputationResponse> increaseUserReputation(String userId, int amount);

    PendingRequest<ReputationResponse> decreaseUserReputation(String userId, int amount);

    PendingRequest<ReputationSettingsResponse> getSettings();

    PendingRequest<ReputationSettingsResponse> setSettings(ReputationSettings settings);
}
