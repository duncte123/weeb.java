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
import me.duncte123.weebJava.WeebInfo;
import me.duncte123.weebJava.exceptions.ImageNotFoundException;
import me.duncte123.weebJava.models.image.WeebImage;

import java.util.List;

@SuppressWarnings("unused")
public interface WeebApi {

    /**
     * This holds the current version of the api
     */
    String VERSION = WeebInfo.VERSION;

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

    /**
     * Returns the base url for the api
     * @return the base url for the api
     */
    default String getAPIBaseUrl() {
        return "https://api.weeb.sh";
    }

    /**
     * Returns the base url for the cdn
     * @return the base url for the cdn
     */
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
     *
     * @see #getTags(boolean)
     */
    default List<String> getTags() {
        return getTagsCached(false, true);
    }

    /**
     * This returns a list of all the available tags
     * @param hidden if we only should display the hidden tags, default {@code false}
     * @return a list of all the available tags
     *
     * @see #getTagsCached(boolean, boolean)
     */
    default List<String> getTags(boolean hidden) {
        return getTagsCached(hidden, true);
    }

    /**
     * This cached the tags for you so that you won't have to make an api request all the time when you need the tags
     * @return A list of the available tags that are cached in the system
     *
     * @see #getTagsCached(boolean, boolean)
     */
    default List<String> getTagsCached() {
        return getTagsCached(false, false);
    }

    /**
     * This cached the tags for you so that you won't have to make an api request all the time when you need the tags
     * @param refresh if we should delete the current cache and get an up-to-date list from weeb.sh, default {@code false}
     * @return A list of the available tags that are cached in the system
     *
     * @see #getTagsCached(boolean, boolean)
     */
    default List<String> getTagsCached(boolean refresh) {
        return getTagsCached(false, refresh);
    }

    /**
     * This caches the tags for you so that you won't have to make an api request all the time when you need the tags
     * @param hidden if we only should display the hidden tags, default {@code false}
     * @param refresh if we should delete the current cache and get an up-to-date list from weeb.sh, default {@code false}
     * @return A list with the available tags that are cached in the system
     */
    List<String> getTagsCached(boolean hidden, boolean refresh);

    /**
     * This returns a list of all the available types
     * @return a list of all the available types
     *
     * @see #getTypes(boolean)
     */
    default List<String> getTypes() {
        return getTypesCached(false, true);
    }

    /**
     * This returns a list of all the available types
     * @param hidden if we only should display the hidden types, default {@code false}
     * @return a list of all the available types
     *
     * @see #getTypesCached(boolean, boolean)
     */
    default List<String> getTypes(boolean hidden) {
        return getTypesCached(hidden, true);
    }

    /**
     * This caches the types for you so that you won't have to make an api request all the time when you need the types
     * @return A list with the available types that are cached in the system
     *
     * @see #getTypesCached(boolean)
     */
    default List<String> getTypesCached() {
        return getTypesCached(false, false);
    }

    /**
     * This caches the types for you so that you won't have to make an api request all the time when you need the types
     * @param refresh if we should delete the current cache and get an up-to-date list from weeb.sh, default {@code false}
     * @return A list with the available types that are cached in the system
     *
     * @see #getTypesCached(boolean, boolean)
     */
    default List<String> getTypesCached(boolean refresh) {
        return getTypesCached(false, refresh);
    }

    /**
     * This caches the types for you so that you won't have to make an api request all the time when you need the types
     * @param hidden if we only should display the hidden tags, default {@code false}
     * @param refresh if we should delete the current cache and get an up-to-date list from weeb.sh, default {@code false}
     * @return A list with the available types that are cached in the system
     */
    List<String> getTypesCached(boolean hidden, boolean refresh);

    /**
     * This gets a random image based on the filter queries
     * @param tags a comma separated list of tags
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImageByTags(String tags) throws ImageNotFoundException {
        return getRandomImage(null, tags, false, "false", null);
    }

    /**
     * This gets a random image based on the filter queries
     * @param tags a comma separated list of tags
     * @param hidden If we should display hidden images, default {@code false}
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImageByTags(String tags, boolean hidden) throws ImageNotFoundException {
        return getRandomImage(null, tags, hidden, "false", null);
    }

    /**
     * This gets a random image based on the filter queries
     * @param tags a comma separated list of tags
     * @param NSFW if we should filter for nsfw images, can be true, false or only, default {@code false}
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImageByTags(String tags, String NSFW) throws ImageNotFoundException {
        return getRandomImage(null, tags, false, NSFW, null);
    }

    /**
     * This gets a random image based on the filter queries
     * @param tags a comma separated list of tags
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
     * @param type the image type
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @see #getRandomImage(String, String, boolean, String, String)
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImage(String type) throws ImageNotFoundException {
        return getRandomImage(type, null, false, "false", null);
    }

    /**
     * This gets a random image based on the filter queries
     * @param type the image type
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
     * @param type the image type
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
     * @param type the image type
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
