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

package me.duncte123.weebJava.examples;

import me.duncte123.weebJava.TokenType;
import me.duncte123.weebJava.WeebApiBuilder;
import me.duncte123.weebJava.exceptions.ImageNotFoundException;
import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.models.WeebImage;

public class Example {
    public static void main(String[] args) throws ImageNotFoundException {
        WeebApi api = new WeebApiBuilder(TokenType.WOLKETOKENS)
                .setToken("TOKEN")
                .build();

        //This prints a list of all the available tags
        System.out.println(api.getTags());

        //This prints a list of all the available types
        System.out.println(api.getTypes());

        //This gets a random image by a tag
        WeebImage imageTag = api.getRandomImageByTags("dance");

        //prints the image url
        System.out.println(imageTag.getUrl());

        WeebImage imageID = api.getImageById("H1mOU3auZ");

        System.out.println(imageID.getUrl());
    }
}
