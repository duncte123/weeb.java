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


    default PendingRequest<ImageTypesResponse> getTypes() {
        return getTypes(null, null, null);
    }

    default PendingRequest<ImageTypesResponse> getTypes(HiddenMode hidden) {
        return getTypes(hidden, null, null);
    }

    default PendingRequest<ImageTypesResponse> getTypes(NSFWMode nsfw) {
        return getTypes(null, nsfw, null);
    }

    default PendingRequest<ImageTypesResponse> getTypes(PreviewMode preview) {
        return getTypes(null, null, preview);
    }

    PendingRequest<ImageTypesResponse> getTypes(HiddenMode hidden, NSFWMode nsfw, PreviewMode preview);


    default PendingRequest<List<String>> getTags() {
        return getTags(null, null);
    }

    default PendingRequest<List<String>> getTags(HiddenMode hidden) {
        return getTags(hidden, null);
    }

    default PendingRequest<List<String>> getTags(NSFWMode nsfw) {
        return getTags(null, nsfw);
    }

    PendingRequest<List<String>> getTags(HiddenMode hidden, NSFWMode nsfw);


    default PendingRequest<WeebImage> getRandomImage(String type) {
        return getRandomImage(type, new ArrayList<>(), null, null, null);
    }

    default PendingRequest<WeebImage> getRandomImage(List<String> tags) {
        return getRandomImage(null, tags, null, null, null);
    }

    PendingRequest<WeebImage> getRandomImage(String type, List<String> tags, NSFWMode nsfw, HiddenMode hidden, FileType fileType);


    PendingRequest<WeebImage> getImageInfo(String imageId);


    default PendingRequest<InputStream> generateSimple(GenerateType type) {
        return generateSimple(type, null, null);
    }

    PendingRequest<InputStream> generateSimple(GenerateType type, Color face, Color hair);


    default PendingRequest<InputStream> generateDiscordStatus() {
        return generateDiscordStatus(null, null);
    }

    default PendingRequest<InputStream> generateDiscordStatus(StatusType status) {
        return generateDiscordStatus(status, null);
    }

    default PendingRequest<InputStream> generateDiscordStatus(String avatar) {
        return generateDiscordStatus(null, avatar);
    }

    PendingRequest<InputStream> generateDiscordStatus(StatusType status, String avatar);


    default PendingRequest<InputStream> generateLicense(String title, String avatar) {
        return generateLicense(title, avatar, new String[0], new String[0]);
    }

    default PendingRequest<InputStream> generateLicense(String title, String avatar, String[] badges) {
        return generateLicense(title, avatar, badges, new String[0]);
    }

    PendingRequest<InputStream> generateLicense(String title, String avatar, String[] badges, String[] widgets);

    PendingRequest<InputStream> generateWaifuinsult(String avatar);

    PendingRequest<InputStream> generateLoveship(String targetOne, String targetTwo);

    ReputationManager getReputationManager();

    SettingsManager getSettingsManager();
}
