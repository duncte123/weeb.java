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
import okhttp3.Request;

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
        api.getRequester().requestAsync(new Request.Builder()
                .url(
                        String.format("%s%s%s",
                                api.getAPIBaseUrl(),
                                "/auto-image/generate",
                                api.getRequester().toParams(
                                        "type=" + type,
                                        "face=" + String.format("%02x%02x%02x", face.getRed(), face.getGreen(), face.getBlue()),
                                        "hair=" + String.format("%02x%02x%02x", hair.getRed(), hair.getGreen(), hair.getBlue())
                                )
                        )
                )
                .get()
                .build(),
                (res) -> callback.accept(Objects.requireNonNull(res.body()).byteStream())
        );
    }
}
