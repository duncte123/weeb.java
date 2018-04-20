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

package me.duncte123.weebJava.models.impl.image;

import com.github.natanbc.reliqua.request.PendingRequest;
import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.models.image.ImageGenerator;
import me.duncte123.weebJava.models.impl.WeebApiImpl;
import me.duncte123.weebJava.types.GenerateType;
import me.duncte123.weebJava.types.StatusType;
import org.json.JSONObject;

import java.awt.*;
import java.io.InputStream;
import java.util.Objects;

public class ImageGeneratorImpl implements ImageGenerator {

    private final WeebApiImpl api;

    public ImageGeneratorImpl(WeebApi api) {
        this.api = (WeebApiImpl) api;
    }

    @Override
    public PendingRequest<InputStream> generateSimple(GenerateType type, Color face, Color hair) {
        return api.getRequestManager().createRequest(
                api.getRequestManager().prepareGet(
                        createEndpoint("/generate",
                                "type=" + type,
                                "face=" + String.format("%02x%02x%02x", face.getRed(), face.getGreen(), face.getBlue()),
                                "hair=" + String.format("%02x%02x%02x", hair.getRed(), hair.getGreen(), hair.getBlue())
                        ),
                        api.getCompiledToken()
                ),
                r -> Objects.requireNonNull(r.body()).byteStream()
        );
    }

    @Override
    public PendingRequest<InputStream> generateDiscordStatus(StatusType status, String avatarUrl) {
        return api.getRequestManager().createRequest(
                api.getRequestManager().prepareGet(
                        createEndpoint("/discord-status", "status=" + status, "avatar=" + avatarUrl),
                        api.getCompiledToken()
                ),
                r -> Objects.requireNonNull(r.body()).byteStream()
        );
    }

    @Override
    public PendingRequest<InputStream> generateWaifuinsult(String avatar) {
        return api.getRequestManager().createRequest(
                api.getRequestManager().preparePOST(
                        createEndpoint("/waifu-insult"),
                        new JSONObject().put("avatar", avatar),
                        api.getCompiledToken()
                ),
                r -> Objects.requireNonNull(r.body()).byteStream()
        );
    }

    @Override
    public PendingRequest<InputStream> generateLicense(String title, String avatar, String[] badges, String[] widgets) {
        JSONObject data = new JSONObject()
                .put("title", title)
                .put("avatar", avatar);

        if (badges.length > 0)
            data.put("badges", badges);

        if (widgets.length > 0)
            data.put("widgets", widgets);

        return api.getRequestManager().createRequest(
                api.getRequestManager().preparePOST(
                        createEndpoint("/license"),
                        data,
                        api.getCompiledToken()
                ),
                r -> Objects.requireNonNull(r.body()).byteStream()
        );
    }

    @Override
    public PendingRequest<InputStream> generateLoveship(String target1, String target2) {
        return api.getRequestManager().createRequest(
                api.getRequestManager().preparePOST(
                        createEndpoint("/love-ship"),
                        new JSONObject().put("targetOne", target1).put("targetTwo", target2),
                        api.getCompiledToken()
                ),
                r -> Objects.requireNonNull(r.body()).byteStream()
        );
    }

    private String createRoute(String part) {
        return "/auto-image" + part;
    }

    private String createEndpoint(String part) {
        return api.getAPIBaseUrl() + createRoute(part);
    }


    private String createEndpoint(String part, String... params) {
        return api.getAPIBaseUrl() + createRoute(part) + api.getRequestManager().toParams(params);
    }
}
