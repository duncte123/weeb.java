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

package me.duncte123.weebJava.models.reputation.responses;

import me.duncte123.weebJava.helpers.DateParser;
import me.duncte123.weebJava.models.reputation.objects.ReputationUser;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class GiveUserReputationResponse extends ReputationResponse {

    private int code;
    private ReputationUser sourceUser;
    private ReputationUser targetUser;
    private String date;

    /**
     * @return The error code of this response
     *
     * <h6>Error codes:</h6>
     * <table border="1"><thead>
     * <tr>
     * <th>code</th>
     * <th>HTTP code</th>
     * <th>description</th>
     * <th>user</th>
     * </tr>
     * </thead><tbody>
     * <tr>
     * <td>0</td>
     * <td>200</td>
     * <td>Successful</td>
     * <td>-</td>
     * </tr>
     * <tr>
     * <td>1</td>
     * <td>403</td>
     * <td>The user used all of his reputations and hit the cooldown</td>
     * <td>source_user</td>
     * </tr>
     * <tr>
     * <td>2</td>
     * <td>403</td>
     * <td>The user received the maximum amount of reputation for today</td>
     * <td>target_user</td>
     * </tr>
     * <tr>
     * <td>3</td>
     * <td>403</td>
     * <td>The user reached the maximum possible amount of reputation</td>
     * <td>target_user</td>
     * </tr>
     * </tbody></table>
     */
    public int getCode() {
        return code;
    }

    /**
     * @return The user that gave the reputation
     */
    public ReputationUser getSourceUser() {
        if (sourceUser == null)
            return user;
        return sourceUser;
    }

    /**
     * @return The user that received the reputation
     */
    public ReputationUser getTargetUser() {
        return targetUser;
    }

    /**
     * @return Current server time in UTC
     */
    public LocalDateTime getDate() {
        if (date == null || date.isEmpty())
            return LocalDateTime.now();
        return DateParser.parseDate(date);
    }

    @Override
    public ReputationUser getUser() {
        throw new IllegalArgumentException("Use getSourceUser() or getTargetUser()");
    }
}
