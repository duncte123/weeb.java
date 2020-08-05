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
import me.duncte123.weebJava.types.HiddenMode;
import me.duncte123.weebJava.types.NSFWMode;
import me.duncte123.weebJava.types.PreviewMode;

import javax.annotation.Nullable;

public class TypesConfig extends HasHiddenAndNsfwMode {
    private final PreviewMode previewMode;

    public TypesConfig(@Nullable HiddenMode hiddenMode, @Nullable NSFWMode nsfwMode, @Nullable PreviewMode previewMode) {
        super(hiddenMode, nsfwMode);
        this.previewMode = previewMode;
    }

    /**
     * Returns the preview mode for this request
     *
     * @return The preview mode for this request
     */
    @Nullable
    public PreviewMode getPreviewMode() {
        return previewMode;
    }

    public static class Builder extends HasHiddenAndNsfwMode.Builder<Builder, TypesConfig> {
        private PreviewMode previewMode;

        /**
         * Sets if we should have a preview for the types that we request, default is null
         *
         * @param previewMode
         *         {@link PreviewMode#PREVIEW} to show a preview, {@link PreviewMode#NO_PREVIEW} to disable previews
         *
         * @return The builder, useful for chaining
         */
        public Builder setPreviewMode(@Nullable PreviewMode previewMode) {
            this.previewMode = previewMode;
            return this;
        }

        @Override
        public TypesConfig build() {
            return new TypesConfig(
                    this.hiddenMode,
                    this.nsfwMode,
                    this.previewMode
            );
        }
    }
}
