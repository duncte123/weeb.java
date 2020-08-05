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

    @Nullable
    public String getType() {
        return type;
    }

    @Nullable
    public List<String> getTags() {
        return tags;
    }

    @Nullable
    public FileType getFileType() {
        return fileType;
    }

    public static class Builder extends HasHiddenAndNsfwMode.Builder<Builder, ImageConfig> {
        private String type;
        private List<String> tags;
        private FileType fileType;

        public Builder setType(@Nullable String type) {
            this.type = type;
            return this;
        }

        public Builder setTags(@Nullable List<String> tags) {
            this.tags = tags;
            return this;
        }

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
