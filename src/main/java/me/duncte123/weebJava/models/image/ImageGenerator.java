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

import com.github.natanbc.reliqua.request.PendingRequest;
import me.duncte123.weebJava.types.GenerateType;
import me.duncte123.weebJava.types.StatusType;

import java.awt.*;
import java.io.InputStream;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public interface ImageGenerator {

    /**
     * This is the default red discord avatar used for generating images.<br>
     * Yes I know that the weeb.sh api defaults to the green one but I like the red one better
     */
    String DEFAULT_AVATAR = "https://discordapp.com/assets/1cbd08c76f8af6dddce02c5138971129.png";

    /**
     * This is used to generate simple images
     *
     * @param type     type of the generation to create, possible types are listed in {@link GenerateType}
     * @param callback The callback function with the image data, we are using callbacks here because this method is async
     * @see #generateSimple(GenerateType, Color, Color, Consumer)
     */
    default void generateSimple(GenerateType type, Consumer<InputStream> callback) {
        generateSimple(type, Color.decode("#fff0d3"), Color.decode("#cc817c"), callback);
    }

    /**
     * This is used to generate simple images
     *
     * @param type     type of the generation to create, possible types are listed in {@link GenerateType}
     * @param face     only used with awooo type, defines color of face
     * @param hair     only used with awooo type, defines color of hair/fur
     * @param callback The callback function with the image data, we are using callbacks here because this method is async
     */
    default void generateSimple(GenerateType type, Color face, Color hair, Consumer<InputStream> callback) {
        generateSimple(type, face, hair).async(callback);
    }

    PendingRequest<InputStream> generateSimple(GenerateType type, Color face, Color hair);

    /**
     * This method is used to generate discord statuses
     *
     * @param callback The callback function with the image data, we are using callbacks here because this method is async
     * @see #generateDiscordStatus(StatusType, String, Consumer)
     */
    default void generateDiscordStatus(Consumer<InputStream> callback) {
        generateDiscordStatus(StatusType.ONLINE, DEFAULT_AVATAR, callback);
    }

    /**
     * This method is used to generate discord statuses
     *
     * @param status   discord status of the mock, has to be one of the states listed in {@link StatusType}
     * @param callback The callback function with the image data, we are using callbacks here because this method is async
     * @see #generateDiscordStatus(StatusType, String, Consumer)
     */
    default void generateDiscordStatus(StatusType status, Consumer<InputStream> callback) {
        generateDiscordStatus(status, DEFAULT_AVATAR, callback);
    }

    /**
     * This method is used to generate discord statuses
     *
     * @param avatarUrl uri encoded http/s url pointing to an avatar, has to have proper headers and be a direct link to an image
     * @param callback The callback function with the image data, we are using callbacks here because this method is async
     * @see #generateDiscordStatus(StatusType, String, Consumer)
     */
    default void generateDiscordStatus(String avatarUrl, Consumer<InputStream> callback) {
        generateDiscordStatus(StatusType.ONLINE, avatarUrl, callback);
    }

    /**
     * This method is used to generate discord statuses
     *
     * @param status    discord status of the mock, has to be one of the states listed in {@link StatusType}
     * @param avatarUrl uri encoded http/s url pointing to an avatar, has to have proper headers and be a direct link to an image
     * @param callback The callback function with the image data, we are using callbacks here because this method is async
     */
    default void generateDiscordStatus(StatusType status, String avatarUrl, Consumer<InputStream> callback) {
        generateDiscordStatus(status, avatarUrl).async(callback);
    }

    PendingRequest<InputStream> generateDiscordStatus(StatusType status, String avatarUrl);

    /**
     * This method is used to generate waifuinsults
     *
     * @param avatar   http/s url pointing to an image, has to have proper headers and be a direct link to an image
     * @param callback The callback function with the image data, we are using callbacks here because this method is async
     */
    default void generateWaifuinsult(String avatar, Consumer<InputStream> callback) {
        generateWaifuinsult(avatar).async(callback);
    }

    PendingRequest<InputStream> generateWaifuinsult(String avatar);

    /**
     * Generates a license
     *
     * @param title    Title of the license
     * @param avatar   http/s url pointing to an image, has to have proper headers and be a direct link to an image
     * @param callback The callback function with the image data, we are using callbacks here because this method is async
     * @see #generateLicense(String, String, String[], String[], Consumer)
     */
    default void generateLicense(String title, String avatar, Consumer<InputStream> callback) {
        generateLicense(title, avatar, new String[0], new String[0], callback);
    }

    /**
     * Generates a license
     *
     * @param title    Title of the license
     * @param avatar   http/s url pointing to an image, has to have proper headers and be a direct link to an image
     * @param badges   Array of http/s urls pointing to images, that should be used in the badges, same conditions as for avatar apply
     * @param callback The callback function with the image data, we are using callbacks here because this method is async
     * @see #generateLicense(String, String, String[], String[], Consumer)
     */
    default void generateLicense(String title, String avatar, String[] badges, Consumer<InputStream> callback) {
        generateLicense(title, avatar, badges, new String[0], callback);
    }

    /**
     * Generates a license
     *
     * @param title    Title of the license
     * @param avatar   http/s url pointing to an image, has to have proper headers and be a direct link to an image
     * @param badges   Array of http/s urls pointing to images, that should be used in the badges, same conditions as for avatar apply
     * @param widgets  Array of strings for filling the three boxes with text content
     * @param callback The call back function, we are using callbacks here because this method is async
     */
    default void generateLicense(String title, String avatar, String[] badges, String[] widgets, Consumer<InputStream> callback) {
        generateLicense(title, avatar, badges, widgets).async(callback);
    }

    PendingRequest<InputStream> generateLicense(String title, String avatar, String[] badges, String[] widgets);

    /**
     * Generates a love ship. <br>
     *     Example: <img src="https://camo.githubusercontent.com/e4c8b9efd86ef78a3c6c40013281025a40fef7b2/68747470733a2f2f692e696d6775722e636f6d2f375977344c73362e706e67" alt="loveship">
     * @param target1 http/s url pointing to an image, has to have proper headers and be a direct link to an image, image will be on the left side.
     * @param target2 http/s url pointing to an image, has to have proper headers and be a direct link to an image, image will be on the right side.
     * @param callback The callback function with the image data, we are using callbacks here because this method is async
     */
    default void generateLoveship(String target1, String target2, Consumer<InputStream> callback) {
        generateLoveship(target1, target2).async(callback);
    }

    PendingRequest<InputStream> generateLoveship(String target1, String target2);
}
