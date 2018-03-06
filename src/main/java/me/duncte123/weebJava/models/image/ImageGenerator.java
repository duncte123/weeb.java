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

package me.duncte123.weebJava.models.image;

import me.duncte123.weebJava.types.GenerateType;
import me.duncte123.weebJava.types.StatusType;

import java.awt.*;
import java.io.InputStream;
import java.util.function.Consumer;

public interface ImageGenerator {

    String DEFAULT_AVATAR = "https://discordapp.com/assets/1cbd08c76f8af6dddce02c5138971129.png";

    default void generateSimple(GenerateType type, Consumer<InputStream>  callback) {
        generateSimple(type, Color.decode("#fff0d3"), Color.decode("#cc817c"), callback);
    }

    void generateSimple(GenerateType type, Color face, Color hair, Consumer<InputStream> callback);

    default void generateDiscordStatus(Consumer<InputStream> callback) {
        generateDiscordStatus(StatusType.ONLINE, DEFAULT_AVATAR, callback);
    }

    default void generateDiscordStatus(StatusType status, Consumer<InputStream> callback) {
        generateDiscordStatus(status, DEFAULT_AVATAR, callback);
    }

    default void generateDiscordStatus(String avatarUrl, Consumer<InputStream> callback) {
        generateDiscordStatus(StatusType.ONLINE, avatarUrl, callback);
    }

    void generateDiscordStatus(StatusType status, String avatarUrl, Consumer<InputStream> callback);

    void generateWaifuinsult(String avatar, Consumer<InputStream> callback);

    default void generateLicense(String title, String avatar, Consumer<InputStream> callback) {
        generateLicense(title, avatar, new String[0], new String[0], callback);
    }

    default void generateLicense(String title, String avatar, String[] badges, Consumer<InputStream> callback) {
        generateLicense(title, avatar, badges, new String[0], callback);
    }

    void generateLicense(String title, String avatar, String[] badges, String[] widgets, Consumer<InputStream> callback);
}
