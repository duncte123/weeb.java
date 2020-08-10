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

package me.duncte123.weebJava.models.image.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.models.WeebResponse;

import java.util.List;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageTypesResponse extends WeebResponse {


    private final List<String> types;
    private final List<PartialImage> preview;

    @JsonCreator
    public ImageTypesResponse(
            @JsonProperty("status") int status,
            @JsonProperty("message") String message,
            @JsonProperty("types") List<String> types,
            @JsonProperty("preview") List<PartialImage> preview
    ) {
        super(status, message);
        this.types = types;
        this.preview = preview;
    }

    /**
     * Returns a list of the available types
     *
     * @return A list of the available types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * Returns a possible empty array of partial images depending on if the preview is enabled
     *
     * @return a possible empty array of partial images depending on if the preview is enabled
     */
    public List<PartialImage> getPreview() {
        return preview;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PartialImage {

        private final String url;
        private final String id;
        private final String fileType;
        private final String baseType;
        private final String type;

        @JsonCreator
        public PartialImage(
                @JsonProperty("url") String url,
                @JsonProperty("id") String id,
                @JsonProperty("fileType") String fileType,
                @JsonProperty("baseType") String baseType,
                @JsonProperty("type") String type
        ) {
            this.url = url;
            this.id = id;
            this.fileType = fileType;
            this.baseType = baseType;
            this.type = type;
        }

        /**
         * Returns the url of the image
         *
         * @return The url of the image
         */
        public String getUrl() {
            return url;
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
         * Returns the file type of this image
         *
         * @return The file type of this image
         */
        public String getFileType() {
            return fileType;
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
         * Returns the type/category of the image, this is what's used to show the list of types in {@link
         * WeebApi#getTypes()}
         *
         * @return The type/category of the image, this is what's used to show the list of types in {@link WeebApi#getTypes()}
         */
        public String getType() {
            return type;
        }

        public String toString() {
            return "PartialImage(" + url + ")";
        }
    }
}
