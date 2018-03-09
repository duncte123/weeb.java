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

import me.duncte123.weebJava.models.image.ImageTag;
import me.duncte123.weebJava.models.image.WeebImage;

import java.util.List;

public class WeebImageImpl implements WeebImage {

    private final String id;
    private final String baseType;
    private final String mimeType;
    private final String account;
    private final boolean hidden;
    private final boolean NSFW;
    private final List<ImageTag> tags;
    private final String url;

    public WeebImageImpl(String id, String baseType, String mimeType, String account, boolean hidden, boolean NSFW,
                         List<ImageTag> tags, String url) {
        this.id = id;
        this.baseType = baseType;
        this.mimeType = mimeType;
        this.account = account;
        this.hidden = hidden;
        this.NSFW = NSFW;
        this.tags = tags;
        this.url = url;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getBaseType() {
        return baseType;
    }

    @Override
    public String getMimeType() {
        return mimeType;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public boolean isNSFW() {
        return NSFW;
    }

    @Override
    public List<ImageTag> getTags() {
        return tags;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
