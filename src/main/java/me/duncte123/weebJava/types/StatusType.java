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

public enum StatusType {

    /**
     * Tells the api to use the online status (green dot)
     */
    ONLINE("online"),
    /**
     * Tells the api to use the idle status (orange dot)
     */
    IDLE("idle"),
    /**
     * Tells the api to use the dnd status (red dot)
     */
    DND("dnd"),
    /**
     * Tells the api to use the streaming status (purple dot)
     */
    STREAMING("streaming"),
    /**
     * Tells the api to use the offline status (gray dot)
     */
    OFFLINE("offline");

    private final String status;
    StatusType(String status) {
        this.status = status;
    }

    /**
     * Returns the status
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}
