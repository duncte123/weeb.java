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

package me.duncte123.weebJava.models.impl.image.response;

import me.duncte123.weebJava.models.image.response.ImageTypesResponse;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ImageTypesResponseImpl implements ImageTypesResponse {

    private List<String> types;
    private List<PartialImageImpl> preview;

    private final List<PartialImage> h = new ArrayList<>();

    @Override
    public List<String> getTypes() {
        return types;
    }

    @Override
    public List<PartialImage> getPreview() {
        if(h.isEmpty() && !preview.isEmpty())
            h.addAll(preview);
        return h;
    }

    public static class PartialImageImpl implements PartialImage {

        private String url;
        private String id;
        private String fileType;
        private String baseType;
        private String type;

        @Override
        public String getUrl() {
            return url;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getFileType() {
            return fileType;
        }

        @Override
        public String getBaseType() {
            return baseType;
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return "PartialImage("+getUrl()+")";
        }
    }
}
