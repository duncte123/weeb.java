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

package me.duncte123.weebJava.models.impl;

import me.duncte123.weebJava.TokenType;
import me.duncte123.weebJava.models.WeebApi;

import java.util.List;

public class WeebApiImpl implements WeebApi {

    private final TokenType tokenType;
    private final String token;

    public WeebApiImpl(TokenType tokenType, String token) {
        this.tokenType = tokenType;
        this.token = token;
    }

    @Override
    public TokenType getTokenType() {
        return tokenType;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public List<String> getTags(boolean hidden) {
        return null;
    }

    @Override
    public List<String> getTypes(boolean hidden) {
        return null;
    }

    //TODO: Random image
    //TODO: image by id
}
