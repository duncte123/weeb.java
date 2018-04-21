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

package me.duncte123.weebJava.models.image;

import java.util.List;

@SuppressWarnings("unused")
public class WeebImage {

    private String id;
    private String type;
    private String baseType;
    private boolean nsfw;
    private String fileType;
    private String mimeType;
    private List<ImageTag> tags;
    private String url;
    private boolean hidden;
    private String source;
    private String account;


    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBaseType() {
        return baseType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getAccount() {
        return account;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean isNsfw() {
        return nsfw;
    }

    public List<ImageTag> getTags() {
        return tags;
    }

    public String getUrl() {
        return url;
    }

    public String getSource() {
        return source;
    }
}
