package com.hoasen.studio.dailymailfeed.Injection;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.Interceptor;

/**
 * Created by Harry Nguyen on 09-Mar-16.
 */

@Singleton
@Component(modules = {DMNetworkClientModule.class})
public interface DMNetworkClientComponent extends AppClientComponent {

}

