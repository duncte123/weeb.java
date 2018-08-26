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

import me.duncte123.weebJava.web.ErrorUtils;
import okhttp3.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOHelper {

    private static final int BUFFER_SIZE = 4096;

    public static byte[] read(Response response) throws IOException {
        return readToFunction(BUFFER_SIZE).accept(ErrorUtils.getInputStream(response));
    }


    @SuppressWarnings("SameParameterValue")
    private static InputStreamFunction<byte[]> readToFunction(int bufferSize) {
        return (stream) -> readFully(stream, bufferSize);
    }

    private static byte[] readFully(InputStream is, int bufferSize) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(bufferSize);
        copyTo(is, outputStream, bufferSize);
        return outputStream.toByteArray();
    }

    private static void copyTo(InputStream in, OutputStream out, int bufferSize) throws IOException {
        byte[] buffer = new byte[bufferSize];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}
