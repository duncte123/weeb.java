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

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class WeebImageImpl implements WeebImage {

    private String id;
    private String type;
    private String baseType;
    private boolean nsfw;
    private String fileType;
    private String mimeType;
    private List<ImageTagImpl> tags;
    private String url;
    private boolean hidden;
    private String source;
    private String account;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
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
    public boolean isNsfw() {
        return nsfw;
    }

    private List<ImageTag> t = new ArrayList<>();
    @Override
    public List<ImageTag> getTags() {
        if(t.isEmpty() && tags != null && !tags.isEmpty())
            t.addAll(tags);
        return t;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getSource() {
        return source;
    }
}
