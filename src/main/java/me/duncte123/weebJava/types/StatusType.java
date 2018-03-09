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
     * Tells the api to use the online status (green dot) <br />
     * <img src="https://camo.githubusercontent.com/706e76e054a8a28fde1bac61209bd5c14d223f51/68747470733a2f2f692e696d6775722e636f6d2f6a5274716772612e706e67" />
     */
    ONLINE("online"),
    /**
     * Tells the api to use the idle status (orange dot) <br />
     * <img src="https://camo.githubusercontent.com/a52f312f4bd926173096d1cfe46a0740677ff2f8/68747470733a2f2f692e696d6775722e636f6d2f336b5077664c472e706e67" />
     */
    IDLE("idle"),
    /**
     * Tells the api to use the dnd status (red dot) <br />
     * <img src="https://camo.githubusercontent.com/bff7d5c10f0e740704ae9953a3b88a8aed9d727c/68747470733a2f2f692e696d6775722e636f6d2f786e79704277462e706e67" />
     */
    DND("dnd"),
    /**
     * Tells the api to use the streaming status (purple dot) <br />
     * <img src="https://camo.githubusercontent.com/a6a7439b40171a4327d64fd21cb9b9c6d08a223b/68747470733a2f2f692e696d6775722e636f6d2f46483955514d312e706e67" />
     */
    STREAMING("streaming"),
    /**
     * Tells the api to use the offline status (gray dot) <br />
     * <img src="https://camo.githubusercontent.com/e36ef0beece64a9eb5541e9724767db0a844f25b/68747470733a2f2f692e696d6775722e636f6d2f32414f6378575a2e706e67" />
     */
    OFFLINE("offline");

    private final String status;

    StatusType(String status) {
        this.status = status;
    }

    /**
     * Returns the status
     *
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
