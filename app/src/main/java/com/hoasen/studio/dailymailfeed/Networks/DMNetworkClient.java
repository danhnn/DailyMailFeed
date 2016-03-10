package com.hoasen.studio.dailymailfeed.Networks;

import com.hoasen.studio.dailymailfeed.Injection.MyApplication;
import com.hoasen.studio.dailymailfeed.MainNews.Model.VnreviewModel;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import rx.Observable;


/**
 * Created by Harry Nguyen on 01-Mar-16.
 */
public class DMNetworkClient {
    static DMNetworkClient instance = null;
    static String baseURL = "http://vnreview.vn/feed/-/rss/13612/";
    DMAPIService service = null;

    @Inject
    Interceptor interceptor;

    static public DMNetworkClient getInstance(){
        if(instance == null){
            return new DMNetworkClient();
        }

        return instance;
    }

    DMNetworkClient(){
        MyApplication.getInstance().getAppComponent().inject(this);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        httpClient.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient.build())
                .build();

        service = retrofit.create(DMAPIService.class);
    }

    public Observable<VnreviewModel> getMobileReview(){
        return service.getMobileReview();
    }
}
