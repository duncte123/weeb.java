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

package me.duncte123.weebJava;

import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.models.impl.WeebApiImpl;
import me.duncte123.weebJava.types.ApiUrl;
import me.duncte123.weebJava.types.TokenType;

public class WeebApiBuilder {

    private final TokenType tokenType;
    private String token;
    private ApiUrl apiUrl = ApiUrl.PRODUCTION;

    /**
     * This creates the builder for the <a href="https://weeb.sh/" target="_blank">weeb.sh</a> api
     * @param tokenType The type of token that you want to use
     *
     * @see TokenType#WOLKETOKENS
     * @see TokenType#BEARER
     */
    public WeebApiBuilder(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * This sets the token used to authenticate you for all your requests to <a href="https://weeb.sh/" target="_blank">weeb.sh</a>
     * @param token Your token
     * @return The current builder, useful for chaining
     */
    public WeebApiBuilder setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * This sets the api url that we user to make our requests
     * @param apiUrl The {@link ApiUrl} that we want to use
     * @return The current builder, useful for chaining
     */
    public WeebApiBuilder setApiUrl(ApiUrl apiUrl) {
        this.apiUrl = apiUrl;
        return this;
    }

    /**
     * This builds the api and returns the {@link WeebApi WeebApi} interface ready to be used
     * @return the {@link WeebApi WeebApi} interface ready to be used
     */
    public WeebApi build() {
        return new WeebApiImpl(tokenType, token, apiUrl);
    }
}
