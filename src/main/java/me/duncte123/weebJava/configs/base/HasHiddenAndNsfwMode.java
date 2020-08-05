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

package me.duncte123.weebJava.configs.base;

import me.duncte123.weebJava.types.HiddenMode;
import me.duncte123.weebJava.types.NSFWMode;

import javax.annotation.Nullable;

public abstract class HasHiddenAndNsfwMode {
    private final HiddenMode hiddenMode;
    private final NSFWMode nsfwMode;

    public HasHiddenAndNsfwMode(HiddenMode hiddenMode, NSFWMode nsfwMode) {
        this.hiddenMode = hiddenMode;
        this.nsfwMode = nsfwMode;
    }

    /**
     * Returns the current nsfw mode
     *
     * @return the current nsfw mode
     */
    @Nullable
    public NSFWMode getNsfwMode() {
        return nsfwMode;
    }

    /**
     * Returns the current hidden mode
     *
     * @return the current hidden mode
     */
    @Nullable
    public HiddenMode getHiddenMode() {
        return hiddenMode;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public abstract static class Builder<B extends Builder, T> {
        protected HiddenMode hiddenMode;
        protected NSFWMode nsfwMode;

        /**
         * Sets the current hidden mode for the request
         *
         * @param hiddenMode
         *         When {@link HiddenMode#HIDE} you only get public images, {@link HiddenMode#ONLY} will only give you
         *         hidden images uploaded by yourself, the default version is {@code null} or {@link HiddenMode#DEFAULT}
         *
         * @return The current builder, useful for chaining
         */
        public B setHiddenMode(@Nullable HiddenMode hiddenMode) {
            this.hiddenMode = hiddenMode;
            return (B) this;
        }

        /**
         * Sets the current nsfw mode for the request
         *
         * @param nsfwMode
         *         When {@link NSFWMode#DISALLOW_NSFW}, no types from nsfw images will be returned, {@link
         *         NSFWMode#ALLOW_NSFW} returns types from nsfw and non-nsfw images, {@link NSFWMode#ONLY_NSFW} returns
         *         only types from nsfw images
         *
         * @return The current builder, useful for chaining
         */
        public B setNsfwMode(@Nullable NSFWMode nsfwMode) {
            this.nsfwMode = nsfwMode;
            return (B) this;
        }

        /**
         * Builds the object and returns it
         *
         * @return The object from this builder
         */
        public abstract T build();
    }
}
