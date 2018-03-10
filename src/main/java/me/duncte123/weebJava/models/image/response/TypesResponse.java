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

import java.util.List;

@SuppressWarnings("ALL")
public interface TypesResponse {

    /**
     * Returns a list of type string with the types that you requested
     * @return a list of type string with the types that you requested
     */
    List<String> getTypes();

    /**
     * Returns a List of {@link PartialImage PartialImages} if you requested the preview
     * @return a List of {@link PartialImage PartialImages} if you requested the preview
     */
    List<PartialImage> getPreview();

    interface PartialImage {

        /**
         * Returns the image url
         * @return the image url
         */
        String getUrl();

        /**
         * Returns the image id
         * @return the image id
         */
        String getId();

        /**
         * Returns the file type
         * @return the file type
         */
        String getFileType();

        /**
         * Returns the type/category of the image, this is what's used to show the list of types in /types
         * @return the type/category of the image, this is what's used to show the list of types in /types
         */
        String getBaseType();

        /**
         * Returns the type/category of the image, this is what's used to show the list of types in /types
         * @return the type/category of the image, this is what's used to show the list of types in /types
         */
        default String getType() {
            return getBaseType();
        }

    }

}
