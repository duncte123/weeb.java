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

package me.duncte123.weebJava;

import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.models.impl.WeebApiImpl;
import me.duncte123.weebJava.types.Endpoint;
import me.duncte123.weebJava.types.TokenType;

@SuppressWarnings("unused")
public class WeebApiBuilder {

    private final TokenType tokenType;
    private String token;
    private Endpoint endpoint = Endpoint.PRODUCTION;
    private String appName = null;

    /**
     * This creates the builder for the <a href="https://weeb.sh/" target="_blank">weeb.sh</a> api
     *
     * @param tokenType
     *         The type of token that you want to use
     *
     * @see TokenType#WOLKETOKENS
     * @see TokenType#BEARER
     */
    public WeebApiBuilder(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * This creates the builder for the <a href="https://weeb.sh/" target="_blank">weeb.sh</a> api
     *
     * @param tokenType
     *         The type of token that you want to use
     * @param appInfo
     *         the name of your application
     *         This sets the app name in the user agent when making requests.
     *         This valus is supposed to be in the following format {@code BotName/Version} or alternatively {@code
     *         BotName/Version/environment}
     *         Examples {@code DuncteBot/3.2.4} or {@code DuncteBot/3.2.4/beta}
     *         What you set for version and environment is fully up to you, as long as the name is set correctly
     *         The reason that this is done is to help weeb.sh identify the users a lot better
     *
     * @see TokenType#WOLKETOKENS
     * @see TokenType#BEARER
     * @deprecated for removal use {@link WeebApiBuilder(TokenType)} and {@link #setBotInfo(String, String, String)} instead
     */
    @Deprecated
    public WeebApiBuilder(TokenType tokenType, String appInfo) {
        this.tokenType = tokenType;
        this.appName = appInfo;
    }

    /**
     * This sets the token used to authenticate you for all your requests to <a href="https://weeb.sh/"
     * target="_blank">weeb.sh</a>
     *
     * @param token
     *         Your token
     *
     * @return The current builder, useful for chaining
     */
    public WeebApiBuilder setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * This sets the api url that we user to make our requests
     *
     * @param endpoint
     *         The {@link Endpoint} that we want to use
     *
     * @return The current builder, useful for chaining
     */
    public WeebApiBuilder setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    /**
     * This sets the app name in the user agent when making requests.
     * This valus is supposed to be in the following format {@code BotName/Version} or alternatively {@code
     * BotName/Version/environment}
     * Examples {@code DuncteBot/3.2.4} or {@code DuncteBot/3.2.4/beta}
     * What you set for version and environment is fully up to you, as long as the name is set correctly
     * The reason that this is done is to help weeb.sh identify the users a lot better
     *
     * @param botName
     *         the name of your application
     * @param botVersion
     *         The version of your application
     * @param environment
     *         Some additional data
     *
     * @return The current builder, useful for chaining
     */
    public WeebApiBuilder setBotInfo(String botName, String botVersion, String environment) {
        this.appName = String.format("%s/%s/%s", botName, botVersion, environment);
        return this;
    }

    /**
     * This builds the api and returns the {@link WeebApi WeebApi} interface ready to be used
     *
     * @return the {@link WeebApi WeebApi} interface ready to be used
     */
    public WeebApi build() {
        if (appName == null || appName.isEmpty())
            throw new NullPointerException("Bot info has not been set, please set it via WeebApiBuilder#setBotInfo");
        return new WeebApiImpl(tokenType, token, endpoint, appName);
    }
}
