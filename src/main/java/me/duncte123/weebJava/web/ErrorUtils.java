/*
 *    Copyright 2018 - 2019 Duncan Sterken
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

package me.duncte123.weebJava.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.natanbc.reliqua.request.RequestContext;
import com.github.natanbc.reliqua.request.RequestException;
import me.duncte123.weebJava.exceptions.MissingPermissionException;
import me.duncte123.weebJava.exceptions.NotFoundException;
import okhttp3.Response;
import okhttp3.ResponseBody;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

public class ErrorUtils {
    private static final JsonMapper mapper = new JsonMapper();

    @Nonnull
    public static JsonNode toJSONObject(Response response) throws IOException {
        return mapper.readTree(getInputStream(response));
    }

    @Nonnull
    public static <T> T getItem(Response response, JsonMapper mapper, Class<T> cls) {
        try {
            return mapper.readValue(getInputStream(response), cls);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static InputStream getInputStream(Response response) {
        ResponseBody body = response.body();
        if (body == null) throw new IllegalStateException("Body should never be null");
        String encoding = response.header("Content-Encoding");
        if (encoding != null) {
            switch (encoding.toLowerCase()) {
                case "gzip":
                    try {
                        return new GZIPInputStream(body.byteStream());
                    } catch (IOException e) {
                        throw new IllegalStateException("Received Content-Encoding header of gzip, but data is not valid gzip", e);
                    }
                case "deflate":
                    return new InflaterInputStream(body.byteStream());
            }
        }
        return body.byteStream();
    }

    public static <T> void handleError(RequestContext<T> context) {
        Response response = context.getResponse();
        ResponseBody body = response.body();
        if (body == null) {
            context.getErrorConsumer().accept(new RequestException("Unexpected status code " + response.code() + " (No body)", context.getCallStack()));
            return;
        }

        JsonNode json = null;

        try {
            json = toJSONObject(response);
        } catch (IOException ignored) {
        }

        switch (response.code()) {
            // json is never null in cases of 403 and 404
            case 403:
                context.getErrorConsumer().accept(new MissingPermissionException(json.get("message").asText(), context.getCallStack()));
                break;
            case 404:
                context.getErrorConsumer().accept(new NotFoundException(json.get("message").asText(), context.getCallStack()));
                context.getSuccessConsumer().accept(null);
                break;
            default:
                if (json != null) {
                    context.getErrorConsumer().accept(new RequestException("Unexpected status code " + response.code() + ": " + json.get("message").asText(), context.getCallStack()));
                } else {
                    context.getErrorConsumer().accept(new RequestException("Unexpected status code " + response.code(), context.getCallStack()));
                }
        }
    }
}
