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

import javax.annotation.Nullable;

public class TagsConfig extends HasHiddenAndNsfwMode {

    public TagsConfig(@Nullable HiddenMode hiddenMode, @Nullable NSFWMode nsfwMode) {
        super(hiddenMode, nsfwMode);
    }

    public static class Builder extends HasHiddenAndNsfwMode.Builder<Builder, TagsConfig> {
        @Override
        public TagsConfig build() {
            return new TagsConfig(this.hiddenMode, this.nsfwMode);
        }
    }
}
