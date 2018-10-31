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

package me.duncte123.weebJava.helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.io.IOException;
import java.util.List;

public class WeebUtils {

    private static final Gson gson = new Gson();

    public static <T> T getClassFromJson(@NotNull Response res, @NotNull Class<T> cls) throws IOException {
        return gson.fromJson(res.body().string(), cls);
    }

    public static <T> List<T> getClassFromJsonList(@NotNull JSONArray json) {
        return gson.fromJson(json.toString(), new TypeToken<List<T>>() {}.getType());
    }

    public static <T> T[] toArray(@NotNull JSONArray array) {
        //noinspection unchecked
        return (T[]) array.toList().toArray();
    }

    public static int[] toIntArray(@NotNull JSONArray array) {

        // Create an int array to accomodate the numbers.
        int[] numbers = new int[array.length()];

        // Extract numbers from JSON array.
        for (int i = 0; i < array.length(); ++i) {
            numbers[i] = array.optInt(i);
        }

        return numbers;
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
