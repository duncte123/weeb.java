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

import me.duncte123.weebJava.WeebApiBuilder;
import me.duncte123.weebJava.models.WeebApi;
import me.duncte123.weebJava.types.ApiUrl;
import me.duncte123.weebJava.types.GenerateType;
import me.duncte123.weebJava.types.TokenType;

import java.io.*;

public class WeebApiTest {

    public static void main(String[] args) throws Exception {
        WeebApi api = new WeebApiBuilder(TokenType.WOLKETOKENS)
                //me.duncte123.weebJavaTests.Secrets#WOLKE_TOKEN
                .setToken(Secrets.WOLKE_TOKEN)
                .setApiUrl(ApiUrl.STAGING)
                .build();
        String myavy = "https://cdn.discordapp.com/avatars/191231307290771456/02c10e1918926a81b58110d6ae902c3b.png";

        /*//This should get the tags if there are none yet
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
        System.out.println(types2);*/

        api.getImageGenerator().generateSimple(GenerateType.WON, (img) -> writeToFile(img, "simple") );
        api.getImageGenerator().generateDiscordStatus((img) -> writeToFile(img, "status") );
        api.getImageGenerator().generateWaifuinsult(myavy, (img) -> writeToFile(img, "wifu"));
        api.getImageGenerator().generateLicense("Phan", myavy,
                new String[]{"https://pbs.twimg.com/profile_images/456226536816119809/Gwzk9qCp.jpeg"},
                new String[] {"", "", "Discord: duncte123#1245"},
                (img) -> writeToFile(img, "license"));

    }

    private static void writeToFile(InputStream in, String name) {
        try {

            File targetFile = new File("image-test-" + name + ".png");
            OutputStream outStream = new FileOutputStream(targetFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                outStream.write(bytes, 0, read);
            }
            outStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
