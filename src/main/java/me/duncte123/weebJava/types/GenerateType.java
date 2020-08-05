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

public enum GenerateType implements QueryParam {

    /**
     * This is the awooo type: <br>
     * <img src="https://i.imgur.com/9sJ21z3.png" alt="awooo">
     */
    AWOOO,
    /**
     * This is the eyes type: <br>
     * <img src="https://i.imgur.com/q9UL3fN.png" alt="eyes">
     */
    EYES,
    /**
     * This is the won type: <br>
     * <img src="https://i.imgur.com/hdapIsB.png?1" alt="won">
     */
    WON;


    @Override
    public void appendTo(QueryBuilder builder) {
        builder.append("type", this.name().toLowerCase());
    }
}
