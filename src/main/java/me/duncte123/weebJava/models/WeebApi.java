/*
 *    Copyright 2018 - 2019 Duncan Sterken
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

import com.github.natanbc.reliqua.request.PendingRequest;
import me.duncte123.weebJava.configs.ImageConfig;
import me.duncte123.weebJava.configs.LicenseConfig;
import me.duncte123.weebJava.configs.TagsConfig;
import me.duncte123.weebJava.configs.TypesConfig;
import me.duncte123.weebJava.models.image.WeebImage;
import me.duncte123.weebJava.models.image.response.ImageTypesResponse;
import me.duncte123.weebJava.models.reputation.ReputationManager;
import me.duncte123.weebJava.models.settings.SettingsManager;
import me.duncte123.weebJava.types.GenerateType;
import me.duncte123.weebJava.types.StatusType;
import me.duncte123.weebJava.types.TokenType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.io.InputStream;
import java.util.List;

@SuppressWarnings("unused")
public interface WeebApi {

    /**
     * Returns the token type set on the builder
     *
     * @return the token type set on the builder
     */
    @Nonnull
    TokenType getTokenType();

    /**
     * Returns the token set on the builder
     *
     * @return the token set on the builder
     */
    @Nonnull
    String getToken();

    /**
     * Returns the base url for the api
     *
     * @return the base url for the api
     */
    @Nonnull
    default String getAPIBaseUrl() {
        return "https://api.weeb.sh";
    }

    /**
     * Returns the base url for the cdn
     *
     * @return the base url for the cdn
     */
    @Nonnull
    default String getCDNBaseUrl() {
        return "https://cdn.weeb.sh/";
    }

    /**
     * Returns the token ready to be passed into the auth header
     * For a Bearer token this will return <code>Bearer TOKEN</code>.
     * For a WolkeToken this will return <code>Wolke TOKEN</code>.
     *
     * @return the token ready to be passed into the auth header
     */
    @Nonnull
    default String getCompiledToken() {
        return getTokenType().getType() + " " + getToken();
    }

    /**
     * Lists the types for the images
     *
     * @return The types that match your response
     */
    @Nonnull
    default PendingRequest<ImageTypesResponse> getTypes() {
        return getTypes(new TypesConfig(null, null, null));
    }

    /**
     * Lists the types for the images
     *
     * @param config
     *         the configuration for this request
     *
     * @return The types that match your response
     */
    @Nonnull
    PendingRequest<ImageTypesResponse> getTypes(@Nonnull TypesConfig config);

    /**
     * Get a list of the available tags
     *
     * @return A list of tags
     */
    @Nonnull
    default PendingRequest<List<String>> getTags() {
        return getTags(new TagsConfig(null, null));
    }

    /**
     * Get a list of the available tags
     *
     * @param config
     *         the configuration for this request
     *
     * @return A list of tags
     */
    @Nonnull
    PendingRequest<List<String>> getTags(@Nonnull TagsConfig config);

    /**
     * Get a random image based on the information that you provide
     *
     * @param config
     *         the config for this request
     *
     * @return A random image
     */
    @Nonnull
    PendingRequest<WeebImage> getRandomImage(@Nonnull ImageConfig config);


    /**
     * Get info about an image
     *
     * @param imageId
     *         The id of the image to get the info for
     *
     * @return The information returned from the server
     */
    @Nonnull
    PendingRequest<WeebImage> getImageInfo(@Nonnull String imageId);


    /**
     * Generates a simple image
     *
     * @param type
     *         type of the generation to create
     *
     * @return The {@link InputStream InputStream} of the generated image
     */
    @Nonnull
    default PendingRequest<byte[]> generateSimple(@Nonnull GenerateType type) {
        return generateSimple(type, null, null);
    }

    /**
     * Generates a simple image
     *
     * @param type
     *         type of the generation to create
     * @param face
     *         only used with {@link GenerateType#AWOOO awooo} type, defines color of face
     * @param hair
     *         only used with {@link GenerateType#AWOOO awooo} type, defines color of hair/fur
     *
     * @return The {@link InputStream InputStream} of the generated image
     */
    @Nonnull
    PendingRequest<byte[]> generateSimple(@Nonnull GenerateType type, @Nullable Color face, @Nullable Color hair);

    /**
     * Generates a discord avatar status
     *
     * @param status
     *         discord status that should display on the avatar, or null
     * @param avatar
     *         http/s url pointing to an avatar, has to have proper headers and be a direct link to an image, or null
     *
     * @return A {@code byte[]} of the generated image
     */
    @Nonnull
    PendingRequest<byte[]> generateDiscordStatus(@Nullable StatusType status, @Nullable String avatar);

    /**
     * Generates a licence
     *
     * @param config
     *         The configuration for this request
     *
     * @return A {@code byte[]} of the generated image
     */
    @Nonnull
    PendingRequest<byte[]> generateLicense(@Nonnull LicenseConfig config);

    /**
     * Generates a waifu insult
     *
     * @param avatar
     *         http/s url pointing to an image, has to have proper headers and be a direct link to an image
     *
     * @return The {@link InputStream InputStream} of the generated image
     */
    @Nonnull
    PendingRequest<byte[]> generateWaifuinsult(@Nonnull String avatar);

    /**
     * Generates a loveship with two images
     *
     * @param targetOne
     *         http/s url pointing to an image, has to have proper headers and be a direct link to an image, image will
     *         be on the left side.
     * @param targetTwo
     *         http/s url pointing to an image, has to have proper headers and be a direct link to an image, image will
     *         be on the right side.
     *
     * @return The {@link InputStream InputStream} of the generated image
     */
    @Nonnull
    PendingRequest<byte[]> generateLoveship(@Nonnull String targetOne, @Nonnull String targetTwo);

    /**
     * Returns the manager that is responsible for interacting with the reputation api
     *
     * @return The manager that is responsible for interacting with the reputation api
     */
    @Nonnull
    ReputationManager getReputationManager();

    /**
     * Returns the manager that is responsible for interacting with the settings api
     *
     * @return The manager that is responsible for interacting with the settings api
     */
    @Nonnull
    SettingsManager getSettingsManager();

    void shutdown();
}
