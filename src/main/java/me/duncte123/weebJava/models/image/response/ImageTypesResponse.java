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

package me.duncte123.weebJava.models.image.response;

import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.models.WeebResponse;

import java.util.List;

@SuppressWarnings("unused")
public class ImageTypesResponse extends WeebResponse {


    private List<String> types;
    private List<PartialImage> preview;

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


    public static class PartialImage {

        private String url;
        private String id;
        private String fileType;
        private String baseType;
        private String type;

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
