package com.hoasen.studio.dailymailfeed.Networks;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Harry Nguyen on 06-Mar-16.
 */
public class DMNetworkServiceMock {
    static DMAPIService service = null;
    static String baseURL = "http://mockserver.com";

    static public DMAPIService getInstance(){
        if(service == null){
            return createRetrofitService();
        }

        return service;
    }

    static DMAPIService createRetrofitService(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        httpClient.interceptors().add(new FakeInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient.build())
                .build();

        service = retrofit.create(DMAPIService.class);
        return service;
    }

}

