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

package me.duncte123.weebJava.models;

import me.duncte123.weebJava.TokenType;

import java.util.List;

public interface WeebApi {

    /**
     * Returns the token type set on the builder
     * @return the token type set on the builder
     */
    TokenType getTokenType();

    /**
     * Returns the token set on the builder
     * @return the token set on the builder
     */
    String getToken();

    default String getAPIBaseUrl() {
        return "https://api.weeb.sh/images";
    }

    default String getCDNBaseUrl() {
        return "https://cdn.weeb.sh/images/";
    }

    /**
     * Returns the token ready to be passed into the auth header
     * For a Bearer token this will return <code>Bearer TOKEN</code>.
     * For a WolkeToken this will return <code>Wolke TOKEN</code>.
     * @return the token ready to be passed into the auth header
     */
    default String getCompiledToken() {
        return getTokenType().getType() + " " + getToken();
    }

    /**
     * This returns a list of all the available tags
     * @return a list of all the available tags
     */
    default List<String> getTags() {
        return getTags(false);
    }

    /**
     * This returns a list of all the available tags
     * @param hidden if we only should display the hidden tags, default {@code false}
     * @return a list of all the available tags
     */
    List<String> getTags(boolean hidden);

    /**
     * This returns a list of all the available types
     * @return a list of all the available types
     */
    default List<String> getTypes() {
        return getTypes(false);
    }

    /**
     * This returns a list of all the available types
     * @param hidden if we only should display the hidden types, default {@code false}
     * @return a list of all the available types
     */
    List<String> getTypes(boolean hidden);

}
