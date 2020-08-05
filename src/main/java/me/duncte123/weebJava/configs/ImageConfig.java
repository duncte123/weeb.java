/*
 *    Copyright 2018 - 2020 Duncan Sterken
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

package me.duncte123.weebJava.configs;

import me.duncte123.weebJava.configs.base.HasHiddenAndNsfwMode;
import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.types.FileType;
import me.duncte123.weebJava.types.HiddenMode;
import me.duncte123.weebJava.types.NSFWMode;

import javax.annotation.Nullable;
import java.util.List;

public class ImageConfig extends HasHiddenAndNsfwMode {
    private final String type;
    private final List<String> tags;
    private final FileType fileType;

    public ImageConfig(@Nullable String type, @Nullable List<String> tags, @Nullable HiddenMode hiddenMode, @Nullable NSFWMode nsfwMode, @Nullable FileType fileType) {
        super(hiddenMode, nsfwMode);
        this.type = type;
        this.tags = tags;
        this.fileType = fileType;
    }

    /**
     * Returns the type set on the builder
     *
     * @return The type set on the builder
     */
    @Nullable
    public String getType() {
        return type;
    }

    /**
     * The tags set on the builder
     *
     * @return The tags set on the builder
     */
    @Nullable
    public List<String> getTags() {
        return tags;
    }

    /**
     * The file type set on the builder
     *
     * @return The file type set on the builder
     */
    @Nullable
    public FileType getFileType() {
        return fileType;
    }

    public static class Builder extends HasHiddenAndNsfwMode.Builder<Builder, ImageConfig> {
        private String type;
        private List<String> tags;
        private FileType fileType;

        /**
         * Sets the type of the image you want to get either {@code #setType(String)} or {@link #setTags(List)} is
         * mandatory, but you can combine them
         * <p>Types can be fetched with {@link WeebApi#getTypes()}</p>
         *
         * @param type
         *         the type of image to search for
         *
         * @return The builder, useful for chaining
         */
        public Builder setType(@Nullable String type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the type of the image you want to get either {@link #setType(String)} or {@code #setTags(List)} is
         * mandatory, but you can combine them
         * <p>Types can be fetched with {@link WeebApi#getTags()}</p>
         *
         * @param tags
         *         a list of tags that the image should have
         *
         * @return The builder, useful for chaining
         */
        public Builder setTags(@Nullable List<String> tags) {
            this.tags = tags;
            return this;
        }

        /**
         * Sets the file type to return
         *
         * @param fileType
         *         The file type of the image to return
         *
         * @return The builder, useful for chaining
         */
        public Builder setFileType(@Nullable FileType fileType) {
            this.fileType = fileType;
            return this;
        }

        @Override
        public ImageConfig build() {
            return new ImageConfig(
                    this.type,
                    this.tags,
                    this.hiddenMode,
                    this.nsfwMode,
                    this.fileType
            );
        }
    }
}
