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

package me.duncte123.weebJavaTests;

import me.duncte123.weebJava.types.TokenType;
import me.duncte123.weebJava.WeebApiBuilder;
import me.duncte123.weebJava.exceptions.ImageNotFoundException;
import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.models.image.WeebImage;

import java.util.List;

public class WeebApiTest {

    public static void main(String[] args) throws ImageNotFoundException {
        WeebApi api = new WeebApiBuilder(TokenType.WOLKETOKENS)
                //me.duncte123.weebJavaTests.Secrets#WOLKE_TOKEN
                .setToken(Secrets.WOLKE_TOKEN)
                .build();

        //This should get the tags if there are none yet
        List<String> tags = api.getTagsCached();
        //Print the tags
        System.out.println(tags);

        //This should display the same tags but should not make an api request
        List<String> tags2 = api.getTagsCached();
        //Print the tags
        System.out.println(tags2);

        //Get an image by a tag
        WeebImage imageByTag = api.getRandomImageByTags("b1nzy");
        //And display the url
        System.out.println(imageByTag.getUrl());

        //This should get the tags if there are none yet
        List<String> types = api.getTypesCached();
        //Print the tags
        System.out.println(types);

        //This should display the same tags but should not make an api request
        List<String> types2 = api.getTypesCached();
        //Print the tags
        System.out.println(types2);

        //initial_d
    }
}
