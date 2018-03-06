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

public interface ImageTag {
    /**
     * Returns the name of the tag
     * @return the name of the tag
     * example: momiji inubashiri
     */
    String getName();

    /**
     * Returns if this tag is private and only available to it’s creator
     * @return if this tag is private and only available to it’s creator
     */
    boolean isHidden();

    /**
     * Returns the id of the Api Account that added the tag
     * @return the id of the Api Account that added the tag
     */
    String getUser();
}
