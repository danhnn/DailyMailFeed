package com.hoasen.studio.dailymailfeed.Networks;

import android.content.Context;
import android.content.res.AssetManager;
import android.provider.MediaStore;

import com.hoasen.studio.dailymailfeed.BuildConfig;

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
    // FAKE RESPONSES.
     String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><a><b></b><c></c></a>";
    static public String responseString = "";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        if(BuildConfig.DEBUG) {

            response = new Response.Builder()
                    .code(200)
                    .message(responseString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/xml"), responseString.getBytes()))
                    .addHeader("content-type", "application/xml")
                    .build();
        }
        else {
            response = chain.proceed(chain.request());
        }

        return response;
    }


}