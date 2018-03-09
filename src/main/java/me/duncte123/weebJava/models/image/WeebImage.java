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
public interface WeebImage {

    /**
     * Returns the internal document id
     *
     * @return the internal document id
     * example: H1mOU3auZ
     */
    String getId();

    /**
     * Returns a category-like type of the image
     *
     * @return a category-like type of the image
     * example: sumfuk
     * @see #getBaseType()
     */
    default String getType() {
        return getBaseType();
    }

    /**
     * Returns a category-like type of the image
     *
     * @return a category-like type of the image
     * example: sumfuk
     */
    String getBaseType();

    /**
     * Returns the mime type of the image
     *
     * @return the mime type of the image
     * example: image/jpeg
     */
    String getMimeType();

    /**
     * Returns the id of the Api Account that uploaded that image
     *
     * @return the id of the Api Account that uploaded that image
     * example: BJJUjqjL-
     */
    String getAccount();

    /**
     * Returns if this image is private and only available to it’s uploader
     *
     * @return if this image is private and only available to it’s uploader
     */
    boolean isHidden();

    /**
     * Returns if this image contains inappropriate content
     *
     * @return if this image contains inappropriate content
     */
    boolean isNSFW();

    /**
     * Returns the list of tags that this image has
     *
     * @return the list of tags that this image has
     */
    List<ImageTag> getTags();

    /**
     * Returns the URL of the image
     *
     * @return the URL of the image
     * example: https://cdn.weeb.sh/images/H1mOU3auZ.jpeg
     */
    String getUrl();

    String getSource();
}
