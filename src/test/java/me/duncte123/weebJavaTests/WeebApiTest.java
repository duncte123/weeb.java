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
import me.duncte123.weebJava.models.image.ImageGenerator;
import me.duncte123.weebJava.models.image.WeebImage;
import me.duncte123.weebJava.models.image.response.TypesResponse;
import me.duncte123.weebJava.types.ApiUrl;
import me.duncte123.weebJava.types.GenerateType;
import me.duncte123.weebJava.types.TokenType;

import java.awt.*;
import java.io.*;
import java.util.List;

public class WeebApiTest {

    public static void main(String[] args) throws Exception {
        WeebApi api = new WeebApiBuilder(TokenType.WOLKETOKENS)
                //me.duncte123.weebJavaTests.Secrets#WOLKE_TOKEN
                .setToken(Secrets.WOLKE_TOKEN)
                .build();
        String myavy = "https://cdn.discordapp.com/avatars/191231307290771456/79c89c512f050e356b593a7e7a2564e1.png";

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
        TypesResponse types = api.getTypesCached(false, true);
        //Print the tags
        System.out.println(types.getTypes());
        System.out.println(types.getPreview().get(0).getUrl());

        //This should display the same tags but should not make an api request
        TypesResponse types2 = api.getTypesCached();
        //Print the tags
        System.out.println(types2);

        WeebApi apiImg = new WeebApiBuilder(TokenType.WOLKETOKENS)
                //me.duncte123.weebJavaTests.Secrets#WOLKE_TOKEN
                .setToken(Secrets.WOLKE_TOKEN)
                .setApiUrl(ApiUrl.STAGING)
                .build();

        //Generate Awooo
        apiImg.getImageGenerator().generateSimple(GenerateType.AWOOO, Color.RED, Color.GREEN, (img) -> writeToFile(img, "simple") );
        //Discord status
        apiImg.getImageGenerator().generateDiscordStatus(myavy, (img) -> writeToFile(img, "status") );
        //Insult
        apiImg.getImageGenerator().generateWaifuinsult(myavy, (img) -> writeToFile(img, "wifu"));
        //License
        apiImg.getImageGenerator().generateLicense("Phan", myavy,
                new String[]{"https://pbs.twimg.com/profile_images/456226536816119809/Gwzk9qCp.jpeg"},
                new String[] {"", "", "Discord: duncte123#1245"},
                (img) -> writeToFile(img, "license"));
        //Love ship
        apiImg.getImageGenerator().generateLoveship(myavy, ImageGenerator.DEFAULT_AVATAR, (img) -> writeToFile(img, "loveship"));
    }

    private static void writeToFile(InputStream in, String name) {
        try {

            File targetFile = new File("image-test-" + name + ".png");
            OutputStream outStream = new FileOutputStream(targetFile);
            int read;
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
