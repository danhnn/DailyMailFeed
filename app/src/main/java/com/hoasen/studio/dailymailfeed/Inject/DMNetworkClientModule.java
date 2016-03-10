package com.hoasen.studio.dailymailfeed.Inject;

import com.hoasen.studio.dailymailfeed.Inject.Interceptor.NormalInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;

/**
 * Created by Harry Nguyen on 09-Mar-16.
 */
@Module
public class DMNetworkClientModule {

    @Provides
    @Singleton
    Interceptor provideInterceptor() {
        return new NormalInterceptor();
    }
}
