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
import me.duncte123.weebJava.web.ErrorUtils;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.List;

public class WeebUtils {

    private static Gson gson = new Gson();

    public static <T> T getClassFromJson(Response res, Class<T> cls) {
        return gson.fromJson(toJSONObject(res).toString(), cls);
    }

    /*public static <T> List<T> getClassFromJsonList(Response res, Class<T> cls) throws IOException {
        Type listType = new TypeToken<List< T >>() {}.getType();
        return gson.fromJson(toJSONObject(res).toString(), listType);
    }*/

    public static <T> List<T> getClassFromJsonList(JSONArray json, @SuppressWarnings("unused") Class<T> unused) {
        Type listType = new TypeToken<List< T >>() {}.getType();
        return gson.fromJson(json.toString(), listType);
    }

    public static JSONObject toJSONObject(Response res) {
        return ErrorUtils.toJSONObject(res);
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static Gson getGson() {
        return gson;
    }

    public static String colorToHex(Color color) {
        return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}
