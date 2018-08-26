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

package me.duncte123.weebJava.types;

import me.duncte123.weebJava.helpers.QueryBuilder;
import me.duncte123.weebJava.helpers.QueryParam;

public enum StatusType implements QueryParam {

    /**
     * Tells the api to use the online status (green dot) <br>
     * <img src="https://i.imgur.com/jRtqgra.png" alt="online">
     */
    ONLINE("online"),
    /**
     * Tells the api to use the idle status (orange dot) <br>
     * <img src="https://i.imgur.com/3kPwfLG.png" alt="idle">
     */
    IDLE("idle"),
    /**
     * Tells the api to use the dnd status (red dot) <br>
     * <img src="https://i.imgur.com/xnypBwF.png" alt="dnd">
     */
    DND("dnd"),
    /**
     * Tells the api to use the streaming status (purple dot) <br>
     * <img src="https://i.imgur.com/FH9UQM1.png" alt="streaming">
     */
    STREAMING("streaming"),
    /**
     * Tells the api to use the offline status (gray dot) <br>
     * <img src="https://i.imgur.com/2AOcxWZ.png" alt="offline">
     */
    OFFLINE("offline");

    private final String status;

    StatusType(String status) {
        this.status = status;
    }


    @Override
    public void appendTo(QueryBuilder builder) {
        builder.append("status", status);
    }
}
