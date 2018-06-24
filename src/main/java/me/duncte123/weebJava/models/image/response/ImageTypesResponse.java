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

package me.duncte123.weebJava.models.image.response;

import me.duncte123.weebJava.models.WeebResponse;

import java.util.List;

@SuppressWarnings("unused")
public class ImageTypesResponse extends WeebResponse  {


    private List<String> types;
    private List<PartialImage> preview;

    public List<String> getTypes() {
        return types;
    }

    public List<PartialImage> getPreview() {
        return preview;
    }


    public static class PartialImage {

        private String url;
        private String id;
        private String fileType;
        private String baseType;
        private String type;

        public String getUrl() {
            return url;
        }

        public String getId() {
            return id;
        }

        public String getFileType() {
            return fileType;
        }

        public String getBaseType() {
            return baseType;
        }

        public String getType() {
            return type;
        }

        public String toString() {
            return "PartialImage("+url+")";
        }
    }
}
