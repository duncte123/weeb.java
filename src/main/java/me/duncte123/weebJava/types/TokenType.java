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

public enum TokenType {

    /**
     * This is the Bearer auth token type
     * use this if you have a Bearer token
     * If you have a WolkeTokens use {@link #WOLKETOKENS WOLKETOKENS} instead
     *
     * @see #WOLKETOKENS
     */
    BEARER("Bearer"),

    /**
     * This is the Wolke auth token type
     * use this if you have a Wolke token
     * If you have a Bearer use {@link #BEARER BEARER} instead
     *
     * @see #BEARER
     */
    WOLKETOKENS("Wolke");

    private final String tokenType;

    TokenType(String type) {
        this.tokenType = type;
    }

    /**
     * Returns the token type to be used in the auth requests
     *
     * @return the token type
     */
    public String getType() {
        return tokenType;
    }

    @Override
    public String toString() {
        return tokenType;
    }
}
