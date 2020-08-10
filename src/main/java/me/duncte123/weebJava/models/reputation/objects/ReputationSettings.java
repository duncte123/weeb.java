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

package me.duncte123.weebJava.models.reputation.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReputationSettings {

    private final String accountId;
    private int reputationPerDay;
    private int maximumReputation;
    private int maximumReputationReceivedDay;
    private long reputationCooldown;

    @JsonCreator
    private ReputationSettings(
            @JsonProperty("reputationPerDay") int reputationPerDay,
            @JsonProperty("maximumReputation") int maximumReputation,
            @JsonProperty("maximumReputationReceivedDay") int maximumReputationReceivedDay,
            @JsonProperty("reputationCooldown") long reputationCooldown,
            @JsonProperty("accountId") String accountId
    ) {
        this.reputationPerDay = reputationPerDay;
        this.maximumReputation = maximumReputation;
        this.maximumReputationReceivedDay = maximumReputationReceivedDay;
        this.reputationCooldown = reputationCooldown;
        this.accountId = accountId;
    }

    /**
     * @return Number of reputations a user may give out per reputationCooldown
     */
    public int getReputationPerDay() {
        return reputationPerDay;
    }

    /**
     * @param reputationPerDay
     *         Number of reputations a user may give out per reputationCooldown, default: {@code 2}
     *
     * @return The current settings object, useful for chaining
     */
    public ReputationSettings setReputationPerDay(int reputationPerDay) {
        this.reputationPerDay = reputationPerDay;
        return this;
    }

    /**
     * @return The maximum reputation a user may receive
     */
    public int getMaximumReputation() {
        return maximumReputation;
    }

    /**
     * @param maximumReputation
     *         The maximum reputation a user may receive, default: {@code 0} (disabled)
     *
     * @return The current settings object, useful for chaining
     */
    public ReputationSettings setMaximumReputation(int maximumReputation) {
        this.maximumReputation = maximumReputation;
        return this;
    }

    /**
     * @return The maximum reputation a user may receive per day
     */
    public int getMaximumReputationReceivedDay() {
        return maximumReputationReceivedDay;
    }

    /**
     * @param maximumReputationReceivedDay
     *         The maximum reputation a user may receive per day, default: {@code 0} (disabled)
     *
     * @return The current settings object, useful for chaining
     */
    public ReputationSettings setMaximumReputationReceivedDay(int maximumReputationReceivedDay) {
        this.maximumReputationReceivedDay = maximumReputationReceivedDay;
        return this;
    }

    /**
     * @return Cooldown per reputation, this is set to time in seconds
     */
    public long getReputationCooldown() {
        return reputationCooldown;
    }

    /**
     * @param reputationCooldown
     *         Cooldown per reputation, this is set to time in seconds, default: {@code 86400} (1 day)
     *
     * @return The current settings object, useful for chaining
     */
    public ReputationSettings setReputationCooldown(long reputationCooldown) {

        if (reputationCooldown < 300)
            throw new IllegalArgumentException("reputationCooldown should be >= 300");

        this.reputationCooldown = reputationCooldown;
        return this;
    }

    /**
     * @return The account id for the token
     */
    public String getAccountId() {
        return accountId;
    }

    public ObjectNode toJson(JsonMapper mapper) {
        return mapper.createObjectNode()
                .put("reputationPerDay", getReputationPerDay())
                .put("maximumReputation", getMaximumReputation())
                .put("maximumReputationReceivedDay", getMaximumReputationReceivedDay())
                .put("reputationCooldown", getReputationCooldown());
    }

}
