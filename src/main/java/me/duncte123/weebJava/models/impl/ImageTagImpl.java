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

import me.duncte123.weebJava.models.ImageTag;

public class ImageTagImpl implements ImageTag {

    private final String name;
    private final boolean hidden;
    private final String user;

    public ImageTagImpl(String name, boolean hidden, String user) {
        this.name = name;
        this.hidden = hidden;
        this.user = user;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public String getUser() {
        return user;
    }
}