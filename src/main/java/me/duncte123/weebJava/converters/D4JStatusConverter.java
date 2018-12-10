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

package me.duncte123.weebJava.converters;

import me.duncte123.weebJava.types.StatusType;

public class D4JStatusConverter {
    /**
     * Returns an instance of the StatusType from a Discord4J status
     *
     * @param status
     *         The status that you want to display
     *
     * @return The {@link StatusType} that matches the Discord4J status
     */
    public static StatusType convert(sx.blah.discord.handle.obj.StatusType status) {
        switch (status) {
            case ONLINE:
                return StatusType.ONLINE;

            case IDLE:
                return StatusType.IDLE;

            case DND:
                return StatusType.DND;

            default:
                return StatusType.OFFLINE;
        }
    }
}
