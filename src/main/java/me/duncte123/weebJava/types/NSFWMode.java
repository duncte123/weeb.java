/*
 *    Copyright 2018 - 2019 Duncan Sterken
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

@SuppressWarnings("unused")
public enum NSFWMode implements QueryParam {
    /**
     * Sets the NSFW flag to true, this means that there are NSFW images/tages allowed in the results
     */
    ALLOW_NSFW("true"),
    /**
     * Sets the NSFW flag to false, this means that there are no NSFW images/tags allowed in the results
     */
    DISALLOW_NSFW("false"),
    /**
     * Sets the NSFW flag to only, this means that there are only NSFW images/tags in the results
     */
    ONLY_NSFW("only");

    private final String type;

    NSFWMode(String t) {
        this.type = t;
    }

    @Override
    public void appendTo(QueryBuilder builder) {
        builder.append("nsfw", type);
    }
}
