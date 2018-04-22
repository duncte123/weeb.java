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

package me.duncte123.weebJava.models.reputation.objects;

@SuppressWarnings("unused")
public class ReputationSettings {

    private int reputationPerDay;
    private int maximumReputation;
    private int maximumReputationReceivedDay;
    private long reputationCooldown;
    private String accountId;

    public int getReputationPerDay() {
        return reputationPerDay;
    }

    public ReputationSettings setReputationPerDay(int reputationPerDay) {
        this.reputationPerDay = reputationPerDay;
        return this;
    }

    public int getMaximumReputation() {
        return maximumReputation;
    }

    public ReputationSettings setMaximumReputation(int maximumReputation) {
        this.maximumReputation = maximumReputation;
        return this;
    }

    public int getMaximumReputationReceivedDay() {
        return maximumReputationReceivedDay;
    }

    public ReputationSettings setMaximumReputationReceivedDay(int maximumReputationReceivedDay) {
        this.maximumReputationReceivedDay = maximumReputationReceivedDay;
        return this;
    }

    public long getReputationCooldown() {
        return reputationCooldown;
    }

    public ReputationSettings setReputationCooldown(long reputationCooldown) {

        if(reputationCooldown < 300)
            throw new IllegalArgumentException("reputationCooldown should be >= 300");

        this.reputationCooldown = reputationCooldown;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }
}
