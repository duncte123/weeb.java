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

import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.models.image.ImageGenerator;
import me.duncte123.weebJava.models.impl.WeebApiImpl;
import me.duncte123.weebJava.types.GenerateType;
import me.duncte123.weebJava.types.StatusType;
import me.duncte123.weebJava.web.Requester;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;

import java.awt.*;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Consumer;

public class ImageGeneratorImpl implements ImageGenerator {

    private final WeebApiImpl api;

    public ImageGeneratorImpl(WeebApi api) {
        this.api = (WeebApiImpl) api;
    }

    @Override
    public void generateSimple(GenerateType type, Color face, Color hair, Consumer<InputStream> callback) {
        executeGETRequest("generate",api.getRequester().toParams(
                "type=" + type,
                "face=" + String.format("%02x%02x%02x", face.getRed(), face.getGreen(), face.getBlue()),
                "hair=" + String.format("%02x%02x%02x", hair.getRed(), hair.getGreen(), hair.getBlue())
        ), callback);
    }

    @Override
    public void generateDiscordStatus(StatusType status, String avatarUrl, Consumer<InputStream> callback) {
        executeGETRequest("discord-status", api.getRequester().toParams(
                "status=" + status,
                "avatar=" + avatarUrl
        ), callback);
    }

    @Override
    public void generateWaifuinsult(String avatar, Consumer<InputStream> callback) {
        executePOSTRequest("waifu-insult", new JSONObject().put("avatar", avatar), callback);
    }

    @Override
    public void generateLicense(String title, String avatar, String[] badges, String[] widgets, Consumer<InputStream> callback) {
        JSONObject data = new JSONObject()
                .put("title", title)
                .put("avatar", avatar);

        if(badges.length > 0)
            data.put("badges", badges);

        if(widgets.length > 0)
            data.put("widgets", widgets);

        executePOSTRequest("license", data, callback);
    }

    private void executeGETRequest(String endpoint, String params, Consumer<InputStream> callback) {
        api.getRequester().requestAsync(new Request.Builder()
                        .url(
                                String.format("%s%s%s",
                                        api.getAPIBaseUrl(),
                                        "/auto-image/" + endpoint,
                                        params
                                )
                        )
                        .get()
                        .header("Authorization", api.getCompiledToken())
                        .addHeader("User-Agent", Requester.USER_AGENT)
                        .build(),
                (res) -> callback.accept(Objects.requireNonNull(res.body()).byteStream()),
                Throwable::printStackTrace
        );
    }
    private void executePOSTRequest(String endpoint, JSONObject json, Consumer<InputStream> callback) {
        api.getRequester().requestAsync(new Request.Builder()
                        .url(
                                String.format("%s%s",
                                        api.getAPIBaseUrl(),
                                        "/auto-image/" + endpoint
                                )
                        )
                        .post(RequestBody.create(MediaType.parse("application/json"), json.toString() ))
                        .header("Authorization", api.getCompiledToken())
                        .addHeader("User-Agent", Requester.USER_AGENT)
                        .build(),
                (res) -> callback.accept(Objects.requireNonNull(res.body()).byteStream()),
                Throwable::printStackTrace
        );
    }
}
