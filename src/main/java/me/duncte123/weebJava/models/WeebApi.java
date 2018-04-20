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

import me.duncte123.weebJava.WeebInfo;
import me.duncte123.weebJava.exceptions.ImageNotFoundException;
import me.duncte123.weebJava.models.image.ImageGenerator;
import me.duncte123.weebJava.models.image.WeebImage;
import me.duncte123.weebJava.models.image.response.TypesResponse;
import me.duncte123.weebJava.types.HiddenMode;
import me.duncte123.weebJava.types.NSFWType;
import me.duncte123.weebJava.types.TokenType;

import java.util.List;

@SuppressWarnings("unused")
public interface WeebApi {

    /**
     * This holds the current version of the api
     */
    String VERSION = WeebInfo.VERSION;

    /**
     * Returns the token type set on the builder
     *
     * @return the token type set on the builder
     */
    TokenType getTokenType();

    /**
     * Returns the token set on the builder
     *
     * @return the token set on the builder
     */
    String getToken();

    /**
     * Returns the base url for the api
     *
     * @return the base url for the api
     */
    default String getAPIBaseUrl() {
        return "https://api.weeb.sh";
    }

    /**
     * Returns the base url for the cdn
     *
     * @return the base url for the cdn
     */
    default String getCDNBaseUrl() {
        return "https://cdn.weeb.sh/images/";
    }

    /**
     * Returns the token ready to be passed into the auth header
     * For a Bearer token this will return <code>Bearer TOKEN</code>.
     * For a WolkeToken this will return <code>Wolke TOKEN</code>.
     *
     * @return the token ready to be passed into the auth header
     */
    default String getCompiledToken() {
        return getTokenType().getType() + " " + getToken();
    }

    /**
     * This returns a list of all the available tags
     *
     * @return a list of all the available tags
     * @see #getTags(HiddenMode)
     */
    default List<String> getTags() {
        return getTags(null, null);
    }

    /**
     * This returns a list of all the available tags
     *
     * @param hidden if we only should display the hidden tags, default {@link HiddenMode#DEFAULT}
     * @return a list of all the available tags
     */
    default List<String> getTags(HiddenMode hidden) {
        return getTags(hidden, NSFWType.FALSE);
    }

    /**
     * This returns a list of all the available tags
     *
     * @param nsfw When false, no types from nsfw images will be returned, true returns types from nsfw and non-nsfw images, only returns only types from nsfw images, default {@link NSFWType#FALSE}
     * @return a list of all the available tags
     */
    default List<String> getTags(NSFWType nsfw) {
        return getTags(null, nsfw);
    }

    /**
     * This returns a list of all the available tags
     *
     * @param hidden if we only should display the hidden tags, default {@link HiddenMode#DEFAULT}
     * @param nsfw When false, no types from nsfw images will be returned, true returns types from nsfw and non-nsfw images, only returns only types from nsfw images, default {@link NSFWType#FALSE}
     * @return a list of all the available tags
     */
    List<String> getTags(HiddenMode hidden, NSFWType nsfw);

    /**
     * This returns a list of all the available types
     *
     * @return  The response from the api wrapped in the {@link TypesResponse} class
     * @see #getTypes(HiddenMode)
     */
    default TypesResponse getTypes() {
        return getTypes(null, null, false);
    }

    default TypesResponse getTypes(boolean preview) {
        return getTypes(null, null, preview);
    }

    /**
     * This returns a list of all the available types
     *
     * @param hidden if we only should display the hidden types, default {@link HiddenMode#DEFAULT}
     * @return The response from the api wrapped in the {@link TypesResponse} class
     */
    default TypesResponse getTypes(HiddenMode hidden) {
        return getTypes(hidden, null, false);
    }

    default TypesResponse getTypes(HiddenMode hidden, NSFWType nsfw) {
        return getTypes(hidden, nsfw, false);
    }

    /**
     * This caches the types for you so that you won't have to make an api request all the time when you need the types
     *
     * @param hidden  if we only should display the hidden tags, default {@link HiddenMode#DEFAULT}
     * @param nsfw When false, no types from nsfw images will be returned, true returns types from nsfw and non-nsfw images, only returns only types from nsfw images, default {@link NSFWType#FALSE}
     * @param preview Get a preview image for each type, Default {@code false}
     * @return The response from the api wrapped in the {@link TypesResponse} class
     */
    TypesResponse getTypes(HiddenMode hidden, NSFWType nsfw, boolean preview);

    /**
     * This gets a random image based on the filter queries
     *
     * @param tags a comma separated list of tags
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImageByTags(String tags) throws ImageNotFoundException {
        return getRandomImage(null, tags, false, NSFWType.FALSE, null);
    }

    /**
     * This gets a random image based on the filter queries
     *
     * @param tags   a comma separated list of tags
     * @param hidden If we should display hidden images, default {@code false}
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImageByTags(String tags, boolean hidden) throws ImageNotFoundException {
        return getRandomImage(null, tags, hidden, NSFWType.FALSE, null);
    }

    /**
     * This gets a random image based on the filter queries
     *
     * @param tags a comma separated list of tags
     * @param NSFW if we should filter for nsfw images, can be true, false or only, default {@link NSFWType#FALSE}
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImageByTags(String tags, NSFWType NSFW) throws ImageNotFoundException {
        return getRandomImage(null, tags, false, NSFW, null);
    }

    /**
     * This gets a random image based on the filter queries
     *
     * @param tags   a comma separated list of tags
     * @param hidden If we should display hidden images, default {@code false}
     * @param NSFW   if we should filter for nsfw images, can be true, false or only, default {@link NSFWType#FALSE}
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     */
    default WeebImage getRandomImageByTags(String tags, boolean hidden, NSFWType NSFW) throws ImageNotFoundException {
        return getRandomImage(null, tags, hidden, NSFW, null);
    }

    /**
     * This gets a random image based on the filter queries
     *
     * @param type the image type
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     * @see #getRandomImage(String, String, boolean, NSFWType, String)
     */
    default WeebImage getRandomImage(String type) throws ImageNotFoundException {
        return getRandomImage(type, null, false, NSFWType.FALSE, null);
    }

    /**
     * This gets a random image based on the filter queries
     *
     * @param type   the image type
     * @param hidden If we should display hidden images, default {@code false}
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     * @see #getRandomImage(String, String, boolean, NSFWType, String)
     */
    default WeebImage getRandomImage(String type, boolean hidden) throws ImageNotFoundException {
        return getRandomImage(type, null, hidden, NSFWType.FALSE, null);
    }

    /**
     * This gets a random image based on the filter queries
     *
     * @param type the image type
     * @param NSFW if we should filter for nsfw images, can be true, false or only, default {@link NSFWType#FALSE}
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     * @see #getRandomImage(String, String, boolean, NSFWType, String)
     */
    default WeebImage getRandomImage(String type, NSFWType NSFW) throws ImageNotFoundException {
        return getRandomImage(type, null, false, NSFW, null);
    }

    /**
     * This gets a random image based on the filter queries
     *
     * @param type   the image type
     * @param hidden If we should display hidden images, default {@code false}
     * @param NSFW   if we should filter for nsfw images, can be true, false or only, default {@link NSFWType#FALSE}
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     * @see #getRandomImage(String, String, boolean, NSFWType, String)
     */
    default WeebImage getRandomImage(String type, boolean hidden, NSFWType NSFW) throws ImageNotFoundException {
        return getRandomImage(type, null, hidden, NSFW, null);
    }

    /**
     * This gets a random image based on the filter queries
     *
     * @param type     the image type
     * @param tags     a comma separated list of tags
     * @param hidden   If we should display hidden images, default {@code false}
     * @param NSFW     if we should filter for nsfw images, can be true, false or only, default {@link NSFWType#FALSE}
     * @param filetype Filters by filetype, e.g. gif, jpg and png (jpg and jpeg are treated the same)
     * @return A random {@link WeebImage WeebImage} based on the query filters
     * @throws ImageNotFoundException when the image is not found
     */
    WeebImage getRandomImage(String type, String tags, boolean hidden, NSFWType NSFW, String filetype) throws ImageNotFoundException;

    /**
     * Returns an image by the image id
     *
     * @param imageId the image id that you want to get
     * @return an image by the image id
     * @throws ImageNotFoundException when the image is not found
     */
    WeebImage getImageById(String imageId) throws ImageNotFoundException;

    /**
     * Returns the image generator.
     * You can use the image generator to access the image generate endpoints
     *
     * @return the {@link ImageGenerator} to generate images with
     */
    ImageGenerator getImageGenerator();

}
