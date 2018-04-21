package me.duncte123.weebJava.models.impl;

import com.afollestad.ason.Ason;
import com.github.natanbc.reliqua.Reliqua;
import com.github.natanbc.reliqua.request.PendingRequest;
import me.duncte123.weebJava.helpers.QueryBuilder;
import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.models.image.WeebImage;
import me.duncte123.weebJava.models.image.response.ImageTypesResponse;
import me.duncte123.weebJava.types.*;
import me.duncte123.weebJava.web.RequestManager;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.awt.*;
import java.io.InputStream;
import java.util.List;

public class WeebApiImpl extends Reliqua implements WeebApi {

    private final TokenType tokenType;
    private final String token;
    private final UrlType urlType;

    private final RequestManager manager;

    public WeebApiImpl(TokenType tokenType, String token, UrlType urlType, String appName) {
        super(new OkHttpClient(), null, true);

        this.tokenType = tokenType;
        this.token = token;
        this.urlType = urlType;

        this.manager = new RequestManager(appName);
    }

    @Override
    public TokenType getTokenType() {
        return tokenType;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String getAPIBaseUrl() {
        return urlType.getUrl();
    }

    @Override
    public PendingRequest<ImageTypesResponse> getTypes(HiddenMode hidden, NSFWMode nsfw, PreviewMode preview) {

        QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl()).append("/images/types");

        if (hidden != null)
            hidden.appendTo(builder);

        if (nsfw != null)
            nsfw.appendTo(builder);

        if (preview != null)
            preview.appendTo(builder);

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken())
        ).build(
                (response) -> Ason.deserialize(response.body().string(), ImageTypesResponse.class, true),
                RequestManager.WebUtilsErrorUtils::handleError
        );
    }

    @Override
    public PendingRequest<List<String>> getTags(HiddenMode hidden, NSFWMode nsfw) {

        QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl()).append("/images/tags");

        if(hidden != null)
            hidden.appendTo(builder);

        if(nsfw != null)
            nsfw.appendTo(builder);

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken())
        ).build(
                (response) -> Ason.deserializeList(new Ason(response.body().string()).getJsonArray("tags"), String.class),
                RequestManager.WebUtilsErrorUtils::handleError
        );
    }

    @Override
    public PendingRequest<WeebImage> getRandomImage(String type, List<String> tags, NSFWMode nsfw, HiddenMode hidden, FileType fileType) {

        QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl()).append("/images/random");

        if(type != null && !type.isEmpty())
            builder.append("type", type);

        if(!tags.isEmpty())
            builder.append("tags", StringUtils.join(tags, ","));

        if(nsfw != null)
            nsfw.appendTo(builder);

        if(hidden != null)
            hidden.appendTo(builder);

        if(fileType != null)
            fileType.appendTo(builder);

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken())
        ).build(
                (response) -> extractImageFromJson(response.body().string()),
                RequestManager.WebUtilsErrorUtils::handleError
        );
    }

    @Override
    public PendingRequest<WeebImage> getImageInfo(String imageId) {
        return createRequest(
                manager.prepareGet(
                        new QueryBuilder().append(getAPIBaseUrl()).append("/info/").append(imageId).build(),
                        getCompiledToken()
                )
        ).build(
                (response) -> extractImageFromJson(response.body().string()),
                RequestManager.WebUtilsErrorUtils::handleError
        );
    }

    @Override
    public PendingRequest<InputStream> generateSimple(GenerateType type, Color face, Color hair) {

        QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl()).append("/auto-image/generate");

        if(type != null)
            type.appendTo(builder);

        if(face != null)
            builder.append("face", colorToHex(face));

        if(hair != null)
            builder.append("hair", colorToHex(hair));

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken())
        ).build(RequestManager.WebUtilsErrorUtils::getInputStream, RequestManager.WebUtilsErrorUtils::handleError);
    }

    @Override
    public PendingRequest<InputStream> generateDiscordStatus(StatusType status, String avatar) {

        QueryBuilder builder = new QueryBuilder()
                .append(getAPIBaseUrl()).append("/auto-image/discord-status");

        if(status != null)
            status.appendTo(builder);

        if(avatar != null && !avatar.isEmpty())
            builder.append("avatar", avatar);

        return createRequest(
                manager.prepareGet(builder.build(), getCompiledToken())
        ).build(RequestManager.WebUtilsErrorUtils::getInputStream, RequestManager.WebUtilsErrorUtils::handleError);
    }

    @Override
    public PendingRequest<InputStream> generateLicense(String title, String avatar, String[] badges, String[] widgets) {
        JSONObject data = new JSONObject()
                .put("title", title)
                .put("avatar", avatar);

        if(badges.length > 3 || widgets.length > 3)
            throw new IllegalArgumentException("Size badges and widgets cannot be higher than 3");

        if(badges.length > 0)
            data.put("badges", badges);

        if(widgets.length > 0)
            data.put("widgets", widgets);

        return createRequest(
                manager.preparePOST(
                        new QueryBuilder().append(getAPIBaseUrl()).append("/auto-image/license").build(),
                        data,
                        getCompiledToken()
                )
        ).build(RequestManager.WebUtilsErrorUtils::getInputStream, RequestManager.WebUtilsErrorUtils::handleError);
    }

    @Override
    public PendingRequest<InputStream> generateWaifuinsult(String avatar) {
        return createRequest(
                manager.preparePOST(
                        new QueryBuilder().append(getAPIBaseUrl()).append("/auto-image/waifu-insult").build(),
                        new JSONObject().put("avatar", avatar),
                        getCompiledToken()
                )
        ).build(RequestManager.WebUtilsErrorUtils::getInputStream, RequestManager.WebUtilsErrorUtils::handleError);
    }

    @Override
    public PendingRequest<InputStream> generateLoveship(String targetOne, String targetTwo) {

        JSONObject data = new JSONObject()
                .put("targetOne", targetOne)
                .put("targetTwo", targetTwo);

        return createRequest(
                manager.preparePOST(
                        new QueryBuilder().append(getAPIBaseUrl()).append("/auto-image/love-ship").build(),
                        data,
                        getCompiledToken()
                )
        ).build(RequestManager.WebUtilsErrorUtils::getInputStream, RequestManager.WebUtilsErrorUtils::handleError);
    }

    private String colorToHex(Color color) {
        return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    private WeebImage extractImageFromJson(String json) {
        return Ason.deserialize(json, WeebImage.class);
    }
}
