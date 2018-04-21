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

public enum UrlType {
    /**
     * This is the normal api.weeb.sh url
     */
    PRODUCTION("https://api.weeb.sh"),
    /**
     * This is the staging.weeb.sh url
     */
    STAGING("https://staging.weeb.sh");

    private final String url;

    UrlType(String url) {
        this.url = url;
    }

    /**
     * Returns the url
     *
     * @return the url as a string
     */
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return url;
    }
}