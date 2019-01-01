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

package me.duncte123.weebJava.models.reputation;

import com.github.natanbc.reliqua.request.PendingRequest;
import me.duncte123.weebJava.models.reputation.objects.ReputationSettings;
import me.duncte123.weebJava.models.reputation.responses.GiveUserReputationResponse;
import me.duncte123.weebJava.models.reputation.responses.ReputationResponse;
import me.duncte123.weebJava.models.reputation.responses.ReputationSettingsResponse;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface ReputationManager {

    /**
     * @return The bot id
     */
    String getBotId();

    /**
     * Set your bot id
     *
     * @param botId
     *         the id of your bot
     *
     * @return The current reputation manager
     */
    ReputationManager setBotId(String botId);

    /**
     * This attempts to get the reputation for a user
     *
     * @param userId
     *         The id of the user that you want to get the reputation for
     *
     * @return The {@link ReputationResponse ReputationResponse} holding the user data
     */
    PendingRequest<ReputationResponse> getReputationForUser(String userId);

    /**
     * Method to give reputation to a user
     *
     * @param sourceUserId
     *         The user that gives the reputation
     * @param targetUserId
     *         The user that receives the reputation
     *
     * @return The {@link GiveUserReputationResponse GiveUserReputationResponse} that holds the data that you need
     */
    PendingRequest<GiveUserReputationResponse> giveUserReputation(String sourceUserId, String targetUserId);

    /**
     * Resets the reputation for a user
     *
     * @param userId
     *         The id of the user that you want to reset the reputation for
     *
     * @return The {@link ReputationResponse ReputationResponse} holding the user data
     *
     * @see ReputationManager#resetUserReputation(String, boolean)
     */
    default PendingRequest<ReputationResponse> resetUserReputation(String userId) {
        return resetUserReputation(userId, false);
    }

    /**
     * Resets the reputation for a user
     *
     * @param userId
     *         The id of the user that you want to reset the reputation for
     * @param resetCooldown
     *         If we should reset the cooldown, default: {@code false}
     *
     * @return The {@link ReputationResponse ReputationResponse} holding the user data
     */
    PendingRequest<ReputationResponse> resetUserReputation(String userId, boolean resetCooldown);

    /**
     * Increases the reputation for a user
     *
     * @param userId
     *         The user to increase the reputation for
     * @param amount
     *         The amount to increase the reputation with
     *
     * @return The {@link ReputationResponse ReputationResponse} holding the user data
     */
    PendingRequest<ReputationResponse> increaseUserReputation(String userId, int amount);

    /**
     * Decreases the reputation for a user
     *
     * @param userId
     *         The user to decrease the reputation for
     * @param amount
     *         The amount to decrease the reputation with
     *
     * @return The {@link ReputationResponse ReputationResponse} holding the user data
     */
    PendingRequest<ReputationResponse> decreaseUserReputation(String userId, int amount);

    /**
     * @return The {@link ReputationSettingsResponse response} from weeb.sh holding the settings for your token
     */
    PendingRequest<ReputationSettingsResponse> getSettings();

    /**
     * Allows you to change the settings for your token
     *
     * @param settings
     *         The {@link ReputationSettings settings} that you want to apply
     *         <p>Hint: Use {@link ReputationSettingsResponse#getSettings()} to get the current settings and to modify
     *         them
     *
     * @return The {@link ReputationSettingsResponse response} from weeb.sh holding the settings for your token
     */
    PendingRequest<ReputationSettingsResponse> setSettings(ReputationSettings settings);
}
