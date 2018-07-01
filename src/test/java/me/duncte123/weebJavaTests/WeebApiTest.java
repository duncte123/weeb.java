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
import me.duncte123.weebJava.models.image.WeebImage;
import me.duncte123.weebJava.models.image.response.ImageTypesResponse;
import me.duncte123.weebJava.models.reputation.ReputationManager;
import me.duncte123.weebJava.models.reputation.objects.ReputationSettings;
import me.duncte123.weebJava.models.settings.SettingsManager;
import me.duncte123.weebJava.models.settings.objects.SettingsObject;
import me.duncte123.weebJava.models.settings.responses.SettingsResponse;
import me.duncte123.weebJava.types.GenerateType;
import me.duncte123.weebJava.types.PreviewMode;
import me.duncte123.weebJava.types.TokenType;
import org.json.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class WeebApiTest {

    public static void main(String[] args) {

        WeebApi api = new WeebApiBuilder(TokenType.WOLKETOKENS)
                //me.duncte123.weebJavaTests.Secrets#WOLKE_TOKEN
                .setToken(Secrets.WOLKE_TOKEN)
                .setBotInfo("Weeb.java-test-environment", "0.0.0", "staging")
                .build();

        testNormalImageThings(api);
//        testImageGen(api);
        testReputation(api);
        testSettings(api);
    }

    private static void testNormalImageThings(WeebApi api) {
        //This should get the tags if there are none yet
        List<String> tags = api.getTags().execute();
        //Print the tags
        System.out.println(tags);

        //This should get the tags if there are none yet
        ImageTypesResponse types = api.getTypes(PreviewMode.PREVIEW).execute();
        //Print the tags
        System.out.println(types.getStatus());
        System.out.println(types.getTypes());
        System.out.println(types.getPreview());

        //Get an image by a type
        WeebImage imageByType = api.getRandomImage("awoo").execute();
        //And display the url
        System.out.println(imageByType.getUrl());

        //Get an image by a tag
        List<String> typesA = new ArrayList<>();
        typesA.add("b1nzy");
        WeebImage imageByTags = api.getRandomImage(typesA).execute();
        //And display the url
        System.out.println(imageByTags.getUrl());
        System.out.println(imageByTags.getTags());
    }

    private static void testImageGen(WeebApi apiImg) {

        String myavy = "https://profile-pictures.rabb.it/e65e7a6b-5011-4907-86bb-38b886a9e401.jpg";

        //Generate Awooo
        apiImg.generateSimple(GenerateType.AWOOO, Color.CYAN, Color.GREEN).async((img) -> writeToFile(img, "simple"));
        //Discord status
        apiImg.generateDiscordStatus(myavy).async( (img) -> writeToFile(img, "status") );
        //Insult
        apiImg.generateWaifuinsult(myavy).async( (img) -> writeToFile(img, "wifu") );
        //License
        apiImg.generateLicense("Phan", myavy,
                new String[]{"https://pbs.twimg.com/profile_images/456226536816119809/Gwzk9qCp.jpeg"},
                new String[] {"", "", "Discord: duncte123#1245"}).async( (img) -> writeToFile(img, "license") );
        //Love ship
        apiImg.generateLoveship(myavy, myavy).async( (img) -> writeToFile(img, "loveship") );
    }

    private static void testReputation(WeebApi api) {
        ReputationManager manager = api.getReputationManager();

        manager.setBotId("215011992275124225");

        ReputationSettings settings = manager.getSettings().execute().getSettings();

        settings.setReputationCooldown(350L);

        manager.setSettings(settings).async(settings2 -> {
            System.out.println(settings2.getSettings().getAccountId());
        });

    }

    private static void testSettings(WeebApi api) {
        SettingsManager manager = api.getSettingsManager();
        SettingsResponse response = manager.updateSetting("guilds", "300407204987666432",
                new JSONObject().put("fruit", "apple")).execute();
        System.out.print("first response: ");
        System.out.println(response.getSetting().getData());

        SettingsObject settings = manager.getSetting("guilds", "300407204987666432").execute().getSetting();
        System.out.println(settings.getData());
        api.getSettingsManager().deleteSetting("guilds", "300407204987666432").async(res -> {
            System.out.println("deleted");
            System.out.println(res.getSetting().getData());
        });
    }

    private static void writeToFile(InputStream in, String name) {
        try {

            File targetFile = new File("image-test-" + name + ".png");

            Files.copy(in, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("writing");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
