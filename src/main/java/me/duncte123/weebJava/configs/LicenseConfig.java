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

import javax.annotation.Nonnull;
import java.util.Objects;

public class LicenseConfig {
    private final String title;
    private final String avatar;
    private final String[] badges;
    private final String[] widgets;

    public LicenseConfig(@Nonnull String title, @Nonnull String avatar, @Nonnull String[] badges, @Nonnull String[] widgets) {
        this.title = title;
        this.avatar = avatar;
        this.badges = badges;
        this.widgets = widgets;
    }

    public String getTitle() {
        return title;
    }

    public String getAvatar() {
        return avatar;
    }

    public String[] getBadges() {
        return badges;
    }

    public String[] getWidgets() {
        return widgets;
    }

    public static class Builder {
        private String title;
        private String avatar;
        private String[] badges = new String[0];
        private String[] widgets = new String[0];

        /**
         * Sets the title of the license
         *
         * @param title
         *         the title of the license
         *
         * @return The builder, useful for chaining
         */
        public Builder setTitle(@Nonnull String title) {
            this.title = Objects.requireNonNull(title);
            return this;
        }

        /**
         * Sets the avatar on the license
         *
         * @param avatar
         *         http/s url pointing to an image, has to have proper headers and be a direct link to an image
         *
         * @return The builder, useful for chaining
         */
        public Builder setAvatar(@Nonnull String avatar) {
            this.avatar = Objects.requireNonNull(avatar);
            return this;
        }

        /**
         * Sets the badges on the license
         *
         * @param badges
         *         Array of http/s urls pointing to images, that should be used in the badges, same conditions as for
         *         avatar apply
         *
         * @return The builder, useful for chaining
         */
        public Builder setBadges(@Nonnull String[] badges) {
            this.badges = Objects.requireNonNull(badges);
            return this;
        }

        /**
         * Sets the widgets on the license
         *
         * @param widgets
         *         Array of strings for filling the three boxes with text content
         *
         * @return The builder, useful for chaining
         */
        public Builder setWidgets(@Nonnull String[] widgets) {
            this.widgets = Objects.requireNonNull(widgets);
            return this;
        }

        public LicenseConfig build() {
            return new LicenseConfig(
                    // check for nulls here as well because of the defaults
                    Objects.requireNonNull(this.title),
                    Objects.requireNonNull(this.avatar),
                    this.badges,
                    this.widgets
            );
        }
    }
}
