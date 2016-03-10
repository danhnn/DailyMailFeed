package com.hoasen.studio.dailymailfeed.Injection.Interceptor;

import android.content.Context;
import android.content.res.AssetManager;
import android.provider.MediaStore;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * Created by Harry Nguyen on 06-Mar-16.
 */
public class FakeInterceptor implements Interceptor {
      String responseString = "";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        response = new Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/xml"), responseString.getBytes()))
                .addHeader("content-type", "application/xml")
                .build();


        return response;
    }

    public void setResponseString(String xml) {
        responseString = xml;
    }

}