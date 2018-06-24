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

import java.net.URL;

public class Endpoint {
    /**
     * This is the normal api.weeb.sh endpoint
     */
    public static Endpoint PRODUCTION = new Endpoint("https://api.weeb.sh");
    /**
     * This is the staging.weeb.sh endpoint
     */
    public static Endpoint STAGING = new Endpoint("https://staging.weeb.sh");

    private final String url;

    private Endpoint(String url) {
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

    /**
     * Allows you to set a custom url for when weeb.sh does some experiments
     * @param url The url for the api
     * @return The url ready to be used with {@link me.duncte123.weebJava.WeebApiBuilder#setEndpoint(Endpoint)}
     */
    public static Endpoint fromUrl(URL url) {
        return new Endpoint("https://" + url.getHost());
    }

    @Override
    public String toString() {
        return url;
    }
}
