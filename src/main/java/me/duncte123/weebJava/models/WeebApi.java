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
import me.duncte123.weebJava.configs.TagsConfig;
import me.duncte123.weebJava.configs.TypesConfig;
import me.duncte123.weebJava.models.image.WeebImage;
import me.duncte123.weebJava.models.image.response.ImageTypesResponse;
import me.duncte123.weebJava.models.reputation.ReputationManager;
import me.duncte123.weebJava.models.settings.SettingsManager;
import me.duncte123.weebJava.types.*;

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
     * @param hidden
     *         if {@link HiddenMode#ONLY}, you only get back hidden images you uploaded
     * @param nsfw
     *         When {@link NSFWMode#DISALLOW_NSFW}, no types from nsfw images will be returned, {@link
     *         NSFWMode#ALLOW_NSFW}, returns types from nsfw and non-nsfw images, {@link NSFWMode#ONLY_NSFW}, returns
     *         only types from nsfw images
     * @param preview
     *         Sets if we should get a preview for each type
     * @param config the configuration for this request
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
     * @param hidden
     *         if {@link HiddenMode#ONLY}, you only get back hidden tags you added
     * @param nsfw
     *         When {@link NSFWMode#DISALLOW_NSFW}, no tags coming from nsfw images will be returned, {@link
     *         NSFWMode#ALLOW_NSFW} returns tags from nsfw and non-nsfw images, {@link NSFWMode#ONLY_NSFW} returns only
     *         tags from nsfw images
     *
     * @return A list of tags
     */
    @Nonnull
    PendingRequest<List<String>> getTags(@Nonnull TagsConfig config);

    /**
     * Get a random image based on the information that you provide
     *
     * @param type
     *         type of the image you want to get Either Type or Tags is mandatory, but you can combine them
     * @param tags
     *         list of the tags the image should have
     * @param nsfw
     *         When {@link NSFWMode#DISALLOW_NSFW}, no types from nsfw images will be returned, {@link
     *         NSFWMode#ALLOW_NSFW} returns types from nsfw and non-nsfw images, {@link NSFWMode#ONLY_NSFW} returns only
     *         types from nsfw images
     * @param hidden
     *         When {@link HiddenMode#HIDE} you only get public images, {@link HiddenMode#ONLY} will only give you
     *         hidden images uploaded by yourself
     * @param fileType
     *         Filetype of the image, may either be jpg/jpeg, png or gif. jpeg and jpg are treated like being the same.
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
     * @return The {@link InputStream InputStream} of the generated image
     */
    @Nonnull
    default PendingRequest<byte[]> generateDiscordStatus() {
        return generateDiscordStatus(null, null);
    }

    /**
     * Generates a discord avatar status
     *
     * @param status
     *         discord status of the mock
     *
     * @return The {@link InputStream InputStream} of the generated image
     */
    @Nonnull
    default PendingRequest<byte[]> generateDiscordStatus(@Nullable StatusType status) {
        return generateDiscordStatus(status, null);
    }

    /**
     * Generates a discord avatar status
     *
     * @param avatar
     *         http/s url pointing to an avatar, has to have proper headers and be a direct link to an image
     *
     * @return The {@link InputStream InputStream} of the generated image
     */
    @Nonnull
    default PendingRequest<byte[]> generateDiscordStatus(@Nullable String avatar) {
        return generateDiscordStatus(null, avatar);
    }

    /**
     * Generates a discord avatar status
     *
     * @param status
     *         discord status of the mock
     * @param avatar
     *         http/s url pointing to an avatar, has to have proper headers and be a direct link to an image
     *
     * @return The {@link InputStream InputStream} of the generated image
     */
    @Nonnull
    PendingRequest<byte[]> generateDiscordStatus(@Nullable StatusType status, @Nullable String avatar);

    /**
     * Generates a licence
     *
     * @param title
     *         Title of the license
     * @param avatar
     *         http/s url pointing to an image, has to have proper headers and be a direct link to an image
     *
     * @return The {@link InputStream InputStream} of the generated image
     */
    @Nonnull
    default PendingRequest<byte[]> generateLicense(@Nonnull String title, @Nonnull String avatar) {
        return generateLicense(title, avatar, new String[0], new String[0]);
    }

    /**
     * Generates a licence
     *
     * @param title
     *         Title of the license
     * @param avatar
     *         http/s url pointing to an image, has to have proper headers and be a direct link to an image
     * @param badges
     *         Array of http/s urls pointing to images, that should be used in the badges, same conditions as for avatar
     *         apply
     *
     * @return The {@link InputStream InputStream} of the generated image
     */
    @Nonnull
    default PendingRequest<byte[]> generateLicense(@Nonnull String title, @Nonnull String avatar, @Nonnull String[] badges) {
        return generateLicense(title, avatar, badges, new String[0]);
    }

    /**
     * Generates a licence
     *
     * @param title
     *         Title of the license
     * @param avatar
     *         http/s url pointing to an image, has to have proper headers and be a direct link to an image
     * @param badges
     *         Array of http/s urls pointing to images, that should be used in the badges, same conditions as for avatar
     *         apply
     * @param widgets
     *         Array of strings for filling the three boxes with text content
     *
     * @return The {@link InputStream InputStream} of the generated image
     */
    @Nonnull
    PendingRequest<byte[]> generateLicense(@Nonnull String title, @Nonnull String avatar, @Nonnull String[] badges, @Nonnull String[] widgets);

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
