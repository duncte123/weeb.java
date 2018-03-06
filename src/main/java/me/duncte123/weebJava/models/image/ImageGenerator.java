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

package me.duncte123.weebJava.models.image;

import me.duncte123.weebJava.types.GenerateType;

import java.awt.*;
import java.io.InputStream;
import java.util.function.Consumer;

public interface ImageGenerator {

    default void generateSimple(GenerateType type, Consumer<InputStream>  callback) {
        generateSimple(type, Color.decode("fff0d3"), Color.decode("cc817c"), callback);
    }

    void generateSimple(GenerateType type, Color face, Color hair, Consumer<InputStream> callback);
}
