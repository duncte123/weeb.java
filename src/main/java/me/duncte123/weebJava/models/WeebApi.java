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

package me.duncte123.weebJava.models;

import me.duncte123.weebJava.TokenType;
import me.duncte123.weebJava.exceptions.ImageNotFoundException;

import java.util.List;

public interface WeebApi {

    /**
     * Returns the token type set on the builder
     * @return the token type set on the builder
     */
    TokenType getTokenType();

    /**
     * Returns the token set on the builder
     * @return the token set on the builder
     */
    String getToken();

    default String getAPIBaseUrl() {
        return "https://api.weeb.sh/images";
    }

    default String getCDNBaseUrl() {
        return "https://cdn.weeb.sh/images/";
    }

    /**
     * Returns the token ready to be passed into the auth header
     * For a Bearer token this will return <code>Bearer TOKEN</code>.
     * For a WolkeToken this will return <code>Wolke TOKEN</code>.
     * @return the token ready to be passed into the auth header
     */
    default String getCompiledToken() {
        return getTokenType().getType() + " " + getToken();
    }

    /**
     * This returns a list of all the available tags
     * @return a list of all the available tags
     */
    default List<String> getTags() {
        return getTags(false);
    }

    /**
     * This returns a list of all the available tags
     * @param hidden if we only should display the hidden tags, default {@code false}
     * @return a list of all the available tags
     */
    List<String> getTags(boolean hidden);

    /**
     * This returns a list of all the available types
     * @return a list of all the available types
     */
    default List<String> getTypes() {
        return getTypes(false);
    }

    /**
     * This returns a list of all the available types
     * @param hidden if we only should display the hidden types, default {@code false}
     * @return a list of all the available types
     */
    List<String> getTypes(boolean hidden);

    /**
     * This gets a random image based on the filter queries
     * @param type the image type
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImageByType(String type) throws ImageNotFoundException {
        return getRandomImage(type, null, false, "false", null);
    }

    /**
     * This gets a random image based on the filter queries
     * @param tags the image type
     * @param hidden If we should display hidden images, default {@code false}
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImageByTags(String tags, boolean hidden) throws ImageNotFoundException {
        return getRandomImage(null, tags, hidden, "false", null);
    }

    /**
     * This gets a random image based on the filter queries
     * @param tags the image type
     * @param NSFW if we should filter for nsfw images, can be true, false or only, default {@code false}
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImageByTags(String tags, String NSFW) throws ImageNotFoundException {
        return getRandomImage(null, tags, false, NSFW, null);
    }

    /**
     * This gets a random image based on the filter queries
     * @param tags the image type
     * @param hidden If we should display hidden images, default {@code false}
     * @param NSFW if we should filter for nsfw images, can be true, false or only, default {@code false}
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImageByTags(String tags, boolean hidden, String NSFW) throws ImageNotFoundException {
        return getRandomImage(null, tags, hidden, NSFW, null);
    }

    /**
     * This gets a random image based on the filter queries
     * @param type a comma separated list of tags
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @see #getRandomImage(String, String, boolean, String, String)
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImage(String type) throws ImageNotFoundException {
        return getRandomImage(type, null, false, "false", null);
    }

    /**
     * This gets a random image based on the filter queries
     * @param type a comma separated list of tags
     * @param hidden If we should display hidden images, default {@code false}
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @see #getRandomImage(String, String, boolean, String, String)
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImage(String type, boolean hidden) throws ImageNotFoundException {
        return getRandomImage(type, null, hidden, "false", null);
    }

    /**
     * This gets a random image based on the filter queries
     * @param type a comma separated list of tags
     * @param NSFW if we should filter for nsfw images, can be true, false or only, default {@code false}
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @see #getRandomImage(String, String, boolean, String, String)
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImage(String type, String NSFW) throws ImageNotFoundException {
        return getRandomImage(type, null, false, NSFW, null);
    }

    /**
     * This gets a random image based on the filter queries
     * @param type a comma separated list of tags
     * @param hidden If we should display hidden images, default {@code false}
     * @param NSFW if we should filter for nsfw images, can be true, false or only, default {@code false}
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @see #getRandomImage(String, String, boolean, String, String)
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImage(String type, boolean hidden, String NSFW) throws ImageNotFoundException {
        return getRandomImage(type, null, hidden, NSFW, null);
    }

    /**
     * This gets a random image based on the filter queries
     * @param type the image type
     * @param tags a comma separated list of tags
     * @param hidden If we should display hidden images, default {@code false}
     * @param NSFW if we should filter for nsfw images, can be true, false or only, default {@code false}
     * @param filetype Filters by filetype, e.g. gif, jpg and png (jpg and jpeg are treated the same)
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     */
    WeebImage getRandomImage(String type, String tags, boolean hidden, String NSFW, String filetype) throws ImageNotFoundException;

    /**
     * Returns an image by the image id
     * @param imageId the image id that you want to get
     * @return an image by the image id
     * @throws ImageNotFoundException when the image is not found
     */
    WeebImage getImageById(String imageId) throws ImageNotFoundException;

}
