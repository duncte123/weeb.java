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

package me.duncte123.weebJava.types;

import me.duncte123.weebJava.helpers.QueryBuilder;
import me.duncte123.weebJava.helpers.QueryParam;

/**
 * This sets the hidden mode on the images
 */
@SuppressWarnings("unused")
public enum HiddenMode implements QueryParam {

    /**
     * Only display the images, types, tags that you uploaded
     */
    ONLY {
        @Override
        public void appendTo(QueryBuilder builder) {
            builder.append("hidden", "true");
        }
    },
    /**
     * Display all the public images
     */
    HIDE {
        @Override
        public void appendTo(QueryBuilder builder) {
            builder.append("hidden", "false");
        }
    },

    /**
     * Display public images and hidden images you uploaded
     */
    DEFAULT {
        @Override
        public void appendTo(QueryBuilder builder) {
        }
    }
}
