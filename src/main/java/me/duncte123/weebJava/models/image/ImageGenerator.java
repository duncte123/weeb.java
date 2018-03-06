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

@SuppressWarnings("unused")
public interface ImageGenerator {

    /**
     * This is the default red discord avatar used for generating images.<br />
     * Yes I know that the weeb.sh api defaults to the green one but I like red better
     */
    String DEFAULT_AVATAR = "https://discordapp.com/assets/1cbd08c76f8af6dddce02c5138971129.png";

    /**
     * This is used to generate simple images
     * @param type type of the generation to create, possible types are listed in {@link GenerateType}
     * @param callback The call back function, we are using callbacks here because this method is async
     *
     * @see #generateSimple(GenerateType, Color, Color, Consumer)
     */
    default void generateSimple(GenerateType type, Consumer<InputStream>  callback) {
        generateSimple(type, Color.decode("#fff0d3"), Color.decode("#cc817c"), callback);
    }

    /**
     * This is used to generate simple images
     * @param type type of the generation to create, possible types are listed in {@link GenerateType}
     * @param face 	only used with awooo type, defines color of face
     * @param hair only used with awooo type, defines color of hair/fur
     * @param callback The call back function, we are using callbacks here because this method is async
     */
    void generateSimple(GenerateType type, Color face, Color hair, Consumer<InputStream> callback);

    /**
     * This method is used to generate discord statuses
     * @param callback The call back function, we are using callbacks here because this method is async
     *
     * @see #generateDiscordStatus(StatusType, String, Consumer)
     */
    default void generateDiscordStatus(Consumer<InputStream> callback) {
        generateDiscordStatus(StatusType.ONLINE, DEFAULT_AVATAR, callback);
    }

    /**
     * This method is used to generate discord statuses
     * @param status discord status of the mock, has to be one of the states listed in {@link StatusType}
     * @param callback The call back function, we are using callbacks here because this method is async
     *
     * @see #generateDiscordStatus(StatusType, String, Consumer)
     */
    default void generateDiscordStatus(StatusType status, Consumer<InputStream> callback) {
        generateDiscordStatus(status, DEFAULT_AVATAR, callback);
    }

    /**
     * This method is used to generate discord statuses
     * @param avatarUrl uri encoded http/s url pointing to an avatar, has to have proper headers and be a direct link to an image
     * @param callback The call back function, we are using callbacks here because this method is async
     *
     * @see #generateDiscordStatus(StatusType, String, Consumer)
     */
    default void generateDiscordStatus(String avatarUrl, Consumer<InputStream> callback) {
        generateDiscordStatus(StatusType.ONLINE, avatarUrl, callback);
    }

    /**
     * This method is used to generate discord statuses
     * @param status discord status of the mock, has to be one of the states listed in {@link StatusType}
     * @param avatarUrl uri encoded http/s url pointing to an avatar, has to have proper headers and be a direct link to an image
     * @param callback The call back function, we are using callbacks here because this method is async
     */
    void generateDiscordStatus(StatusType status, String avatarUrl, Consumer<InputStream> callback);

    /**
     * This method is used to generate waifuinsults
     * @param avatar http/s url pointing to an image, has to have proper headers and be a direct link to an image
     * @param callback The call back function, we are using callbacks here because this method is async
     */
    void generateWaifuinsult(String avatar, Consumer<InputStream> callback);

    /**
     * Generates a license
     * @param title Title of the license
     * @param avatar http/s url pointing to an image, has to have proper headers and be a direct link to an image
     * @param callback The call back function, we are using callbacks here because this method is async
     *
     * @see #generateLicense(String, String, String[], String[], Consumer)
     */
    default void generateLicense(String title, String avatar, Consumer<InputStream> callback) {
        generateLicense(title, avatar, new String[0], new String[0], callback);
    }

    /**
     * Generates a license
     * @param title Title of the license
     * @param avatar http/s url pointing to an image, has to have proper headers and be a direct link to an image
     * @param badges Array of http/s urls pointing to images, that should be used in the badges, same conditions as for avatar apply
     * @param callback The call back function, we are using callbacks here because this method is async
     *
     * @see #generateLicense(String, String, String[], String[], Consumer)
     */
    default void generateLicense(String title, String avatar, String[] badges, Consumer<InputStream> callback) {
        generateLicense(title, avatar, badges, new String[0], callback);
    }

    /**
     * Generates a license
     * @param title Title of the license
     * @param avatar http/s url pointing to an image, has to have proper headers and be a direct link to an image
     * @param badges Array of http/s urls pointing to images, that should be used in the badges, same conditions as for avatar apply
     * @param widgets Array of strings for filling the three boxes with text content
     * @param callback The call back function, we are using callbacks here because this method is async
     */
    void generateLicense(String title, String avatar, String[] badges, String[] widgets, Consumer<InputStream> callback);
}
