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

package me.duncte123.weebJava.models.image;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.duncte123.weebJava.models.WeebApi;

import java.util.List;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeebImage {

    private final String id;
    private final String type;
    private final String baseType;
    private final boolean nsfw;
    private final String fileType;
    private final String mimeType;
    private final List<ImageTag> tags;
    private final String url;
    private final boolean hidden;
    private final String source;
    private final String account;

    @JsonCreator
    public WeebImage(
            @JsonProperty("id") String id,
            @JsonProperty("type") String type,
            @JsonProperty("baseType") String baseType,
            @JsonProperty("nsfw") boolean nsfw,
            @JsonProperty("fileType") String fileType,
            @JsonProperty("mimeType") String mimeType,
            @JsonProperty("tags") List<ImageTag> tags,
            @JsonProperty("url") String url,
            @JsonProperty("hidden") boolean hidden,
            @JsonProperty("source") String source,
            @JsonProperty("account") String account
    ) {
        this.id = id;
        this.type = type;
        this.baseType = baseType;
        this.nsfw = nsfw;
        this.fileType = fileType;
        this.mimeType = mimeType;
        this.tags = tags;
        this.url = url;
        this.hidden = hidden;
        this.source = source;
        this.account = account;
    }

    /**
     * Returns the unique id of the image
     *
     * @return The unique id of the image
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the type/category of the image, this is what's used to show the list of types in {@link
     * WeebApi#getTypes()}
     *
     * @return The type/category of the image, this is what's used to show the list of types in {@link WeebApi#getTypes()}
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the type/category of the image, this is what's used to show the list of types in {@link
     * WeebApi#getTypes()}
     *
     * @return The type/category of the image, this is what's used to show the list of types in {@link WeebApi#getTypes()}
     */
    public String getBaseType() {
        return baseType;
    }

    /**
     * Returns the mime type of the image
     *
     * @return The mime type of the image
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Returns the file type of this image
     *
     * @return The file type of this image
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Returns the id of the account that uploaded that image
     *
     * @return The id of the account that uploaded that image
     */
    public String getAccount() {
        return account;
    }

    /**
     * Returns whether this image can only be seen by the uploader
     *
     * @return Whether this image can only be seen by the uploader
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Returns whether this image has content that could be considered NSFW (not safe for work)
     *
     * @return Whether this image has content that could be considered NSFW (not safe for work)
     */
    public boolean isNsfw() {
        return nsfw;
    }

    /**
     * Returns the tags associated with this image
     *
     * @return The tags associated with this image
     */
    public List<ImageTag> getTags() {
        return tags;
    }

    /**
     * Returns the full url used to load the image, you can safely hotlink the image to your site/service
     *
     * @return The full url used to load the image, you can safely hotlink the image to your site/service
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the possible null source url of the image
     *
     * @return The possible null source url of the image
     */
    public String getSource() {
        return source;
    }
}
