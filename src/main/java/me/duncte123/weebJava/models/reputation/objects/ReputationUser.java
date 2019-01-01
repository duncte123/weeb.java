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

import me.duncte123.weebJava.helpers.DateParser;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static me.duncte123.weebJava.helpers.WeebUtils.toArray;
import static me.duncte123.weebJava.helpers.WeebUtils.toIntArray;

@SuppressWarnings({"unused", "MismatchedReadAndWriteOfArray"})
public class ReputationUser {

    /*
    Variables that contain the parsed time
     */
    private final List<LocalDateTime> cooldownParsed = new ArrayList<>();
    private final List<LocalDateTime> givenReputationParsed = new ArrayList<>();
    private final int reputation;
    private final String[] cooldown;
    private final String[] givenReputation;
    private final String userId;
    private final String botId;
    private final String accountId;
    private final int availableReputations;
    private final int[] nextAvailableReputations;

    public ReputationUser(int reputation, String[] cooldown, String[] givenReputation, String userId, String botId, String accountId, int availableReputations, int[] nextAvailableReputations) {
        this.reputation = reputation;
        this.cooldown = cooldown;
        this.givenReputation = givenReputation;
        this.userId = userId;
        this.botId = botId;
        this.accountId = accountId;
        this.availableReputations = availableReputations;
        this.nextAvailableReputations = nextAvailableReputations;
    }

    public int getReputation() {
        return reputation;
    }

    public List<LocalDateTime> getCooldown() {
        if (cooldownParsed.isEmpty())
            cooldownParsed.addAll(DateParser.parseDate(cooldown));

        return cooldownParsed;
    }

    public List<LocalDateTime> getGivenReputation() {
        if (givenReputationParsed.isEmpty())
            givenReputationParsed.addAll(DateParser.parseDate(givenReputation));

        return givenReputationParsed;
    }

    public String getUserId() {
        return userId;
    }

    public String getBotId() {
        return botId;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getAvailableReputations() {
        return availableReputations;
    }

    public int[] getNextAvailableReputations() {
        return nextAvailableReputations;
    }

    public static ReputationUser fromJson(JSONObject jsonObject) {
        return new ReputationUser(
                jsonObject.getInt("reputation"),
                toArray(jsonObject.getJSONArray("cooldown")),
                toArray(jsonObject.getJSONArray("givenReputation")),
                jsonObject.getString("userId"),
                jsonObject.getString("botId"),
                jsonObject.getString("accountId"),
                jsonObject.getInt("availableReputations"),
                toIntArray(jsonObject.getJSONArray("nextAvailableReputations"))
        );
    }
}
