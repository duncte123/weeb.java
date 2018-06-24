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

public enum FileType implements QueryParam {

    JPG {
        @Override
        public void appendTo(QueryBuilder builder) {
            builder.append("filetype", "jpg");
        }
    },

    PNG {
        @Override
        public void appendTo(QueryBuilder builder) {
            builder.append("filetype", "png");
        }
    },

    GIF {
        @Override
        public void appendTo(QueryBuilder builder) {
            builder.append("filetype", "gif");
        }
    }
}
