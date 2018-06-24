package me.duncte123.weebJava.models;

import com.github.natanbc.reliqua.request.PendingRequest;
import me.duncte123.weebJava.WeebInfo;
import me.duncte123.weebJava.models.image.WeebImage;
import me.duncte123.weebJava.models.image.response.ImageTypesResponse;
import me.duncte123.weebJava.models.reputation.ReputationManager;
import me.duncte123.weebJava.models.settings.SettingsManager;
import me.duncte123.weebJava.types.*;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;
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
        return "https://cdn.weeb.sh/";
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
     * Lists the types for the images
     * @return The types that match your response
     */
    default PendingRequest<ImageTypesResponse> getTypes() {
        return getTypes(null, null, null);
    }

    /**
     * Lists the types for the images
     * @param hidden if {@link HiddenMode#ONLY}, you only get back hidden images you uploaded
     * @return The types that match your response
     */
    default PendingRequest<ImageTypesResponse> getTypes(HiddenMode hidden) {
        return getTypes(hidden, null, null);
    }

    /**
     * Lists the types for the images
     * @param nsfw When {@link NSFWMode#DISALLOW_NSFW}, no types from nsfw images will be returned, {@link NSFWMode#ALLOW_NSFW}, returns types from nsfw and non-nsfw images, {@link NSFWMode#ONLY_NSFW}, returns only types from nsfw images
     * @return The types that match your response
     */
    default PendingRequest<ImageTypesResponse> getTypes(NSFWMode nsfw) {
        return getTypes(null, nsfw, null);
    }

    /**
     * Lists the types for the images
     * @param preview Sets if we should get a preview for each type
     * @return The types that match your response
     */
    default PendingRequest<ImageTypesResponse> getTypes(PreviewMode preview) {
        return getTypes(null, null, preview);
    }

    /**
     * Lists the types for the images
     * @param hidden if {@link HiddenMode#ONLY}, you only get back hidden images you uploaded
     * @param nsfw When {@link NSFWMode#DISALLOW_NSFW}, no types from nsfw images will be returned, {@link NSFWMode#ALLOW_NSFW}, returns types from nsfw and non-nsfw images, {@link NSFWMode#ONLY_NSFW}, returns only types from nsfw images
     * @param preview Sets if we should get a preview for each type
     * @return The types that match your response
     */
    PendingRequest<ImageTypesResponse> getTypes(HiddenMode hidden, NSFWMode nsfw, PreviewMode preview);

    /**
     * Get a list of the available tags
     * @return A list of tags
     */
    default PendingRequest<List<String>> getTags() {
        return getTags(null, null);
    }

    /**
     * Get a list of the available tags
     * @param hidden if {@link HiddenMode#ONLY}, you only get back hidden tags you added
     * @return A list of tags
     */
    default PendingRequest<List<String>> getTags(HiddenMode hidden) {
        return getTags(hidden, null);
    }

    /**
     * Get a list of the available tags
     * @param nsfw When {@link NSFWMode#DISALLOW_NSFW}, no tags coming from nsfw images will be returned, {@link NSFWMode#ALLOW_NSFW} returns tags from nsfw and non-nsfw images, {@link NSFWMode#ONLY_NSFW} returns only tags from nsfw images
     * @return A list of tags
     */
    default PendingRequest<List<String>> getTags(NSFWMode nsfw) {
        return getTags(null, nsfw);
    }

    /**
     * Get a list of the available tags
     * @param hidden if {@link HiddenMode#ONLY}, you only get back hidden tags you added
     * @param nsfw When {@link NSFWMode#DISALLOW_NSFW}, no tags coming from nsfw images will be returned, {@link NSFWMode#ALLOW_NSFW} returns tags from nsfw and non-nsfw images, {@link NSFWMode#ONLY_NSFW} returns only tags from nsfw images
     * @return A list of tags
     */
    PendingRequest<List<String>> getTags(HiddenMode hidden, NSFWMode nsfw);

    /**
     * Get a random image based on the information that you provide
     * @param type type of the image you want to get Either Type or Tags is mandatory, but you can combine them
     * @return A random image
     */
    default PendingRequest<WeebImage> getRandomImage(String type) {
        return getRandomImage(type, new ArrayList<>(), null, null, null);
    }

    /**
     * Get a random image based on the information that you provide
     * @param tags list of the tags the image should have
     * @return A random image
     */
    default PendingRequest<WeebImage> getRandomImage(List<String> tags) {
        return getRandomImage(null, tags, null, null, null);
    }

    /**
     * Get a random image based on the information that you provide
     * @param type type of the image you want to get Either Type or Tags is mandatory, but you can combine them
     * @param tags list of the tags the image should have
     * @param nsfw When {@link NSFWMode#DISALLOW_NSFW}, no types from nsfw images will be returned, {@link NSFWMode#ALLOW_NSFW} returns types from nsfw and non-nsfw images, {@link NSFWMode#ONLY_NSFW} returns only types from nsfw images
     * @param hidden When {@link HiddenMode#HIDE} you only get public images, {@link HiddenMode#ONLY} will only give you hidden images uploaded by yourself
     * @param fileType Filetype of the image, may either be jpg/jpeg, png or gif. jpeg and jpg are treated like being the same.
     * @return A random image
     */
    PendingRequest<WeebImage> getRandomImage(String type, List<String> tags, NSFWMode nsfw, HiddenMode hidden, FileType fileType);


    /**
     * Get info about an image
     * @param imageId The id of the image to get the info for
     * @return The information returned from the server
     */
    PendingRequest<WeebImage> getImageInfo(String imageId);


    /**
     * Generates a simple image
     * @param type type of the generation to create
     * @return The {@link InputStream InputStream} of the generated image
     */
    default PendingRequest<InputStream> generateSimple(GenerateType type) {
        return generateSimple(type, null, null);
    }

    /**
     * Generates a simple image
     * @param type type of the generation to create
     * @param face only used with {@link GenerateType#AWOOO awooo} type, defines color of face
     * @param hair only used with {@link GenerateType#AWOOO awooo} type, defines color of hair/fur
     * @return The {@link InputStream InputStream} of the generated image
     */
    PendingRequest<InputStream> generateSimple(GenerateType type, Color face, Color hair);

    /**
     * Generates a discord avatar status
     * @return The {@link InputStream InputStream} of the generated image
     */
    default PendingRequest<InputStream> generateDiscordStatus() {
        return generateDiscordStatus(null, null);
    }

    /**
     * Generates a discord avatar status
     * @param status discord status of the mock
     * @return The {@link InputStream InputStream} of the generated image
     */
    default PendingRequest<InputStream> generateDiscordStatus(StatusType status) {
        return generateDiscordStatus(status, null);
    }

    /**
     * Generates a discord avatar status
     * @param avatar http/s url pointing to an avatar, has to have proper headers and be a direct link to an image
     * @return The {@link InputStream InputStream} of the generated image
     */
    default PendingRequest<InputStream> generateDiscordStatus(String avatar) {
        return generateDiscordStatus(null, avatar);
    }

    /**
     * Generates a discord avatar status
     * @param status discord status of the mock
     * @param avatar http/s url pointing to an avatar, has to have proper headers and be a direct link to an image
     * @return The {@link InputStream InputStream} of the generated image
     */
    PendingRequest<InputStream> generateDiscordStatus(StatusType status, String avatar);

    /**
     * Generates a licence
     * @param title Title of the license
     * @param avatar http/s url pointing to an image, has to have proper headers and be a direct link to an image
     * @return The {@link InputStream InputStream} of the generated image
     */
    default PendingRequest<InputStream> generateLicense(String title, String avatar) {
        return generateLicense(title, avatar, new String[0], new String[0]);
    }

    /**
     * Generates a licence
     * @param title Title of the license
     * @param avatar http/s url pointing to an image, has to have proper headers and be a direct link to an image
     * @param badges Array of http/s urls pointing to images, that should be used in the badges, same conditions as for avatar apply
     * @return The {@link InputStream InputStream} of the generated image
     */
    default PendingRequest<InputStream> generateLicense(String title, String avatar, String[] badges) {
        return generateLicense(title, avatar, badges, new String[0]);
    }

    /**
     * Generates a licence
     * @param title Title of the license
     * @param avatar http/s url pointing to an image, has to have proper headers and be a direct link to an image
     * @param badges Array of http/s urls pointing to images, that should be used in the badges, same conditions as for avatar apply
     * @param widgets Array of strings for filling the three boxes with text content
     * @return The {@link InputStream InputStream} of the generated image
     */
    PendingRequest<InputStream> generateLicense(String title, String avatar, String[] badges, String[] widgets);

    /**
     * Generates a waifu insult
     * @param avatar http/s url pointing to an image, has to have proper headers and be a direct link to an image
     * @return The {@link InputStream InputStream} of the generated image
     */
    PendingRequest<InputStream> generateWaifuinsult(String avatar);

    /**
     * Generates a loveship with two images
     * @param targetOne http/s url pointing to an image, has to have proper headers and be a direct link to an image, image will be on the left side.
     * @param targetTwo http/s url pointing to an image, has to have proper headers and be a direct link to an image, image will be on the right side.
     * @return The {@link InputStream InputStream} of the generated image
     */
    PendingRequest<InputStream> generateLoveship(String targetOne, String targetTwo);

    /**
     * Returns the manager that is responsible for interacting with the reputation api
     * @return The manager that is responsible for interacting with the reputation api
     */
    ReputationManager getReputationManager();

    /**
     * Returns the manager that is responsible for interacting with the settings api
     * @return The manager that is responsible for interacting with the settings api
     */
    SettingsManager getSettingsManager();
}
