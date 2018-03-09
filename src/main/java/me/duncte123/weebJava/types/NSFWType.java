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

public enum NSFWType {
    /**
     * Sets the NSFW flag to true, this means that there are NSFW images/tages in the results
     */
    TRUE("true"),
    /**
     * Sets the NSFW flag to false, this means that there are no NSFW images/tags in the results
     */
    FALSE("false"),
    /**
     * Sets the NSFW flag to only, this means that there are only NSFW images/tags in the results
     */
    ONLY("only");

    private final String type;
    NSFWType(String t) {
        this.type = t;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
