package com.hoasen.studio.dailymailfeed.Injection;


import com.hoasen.studio.dailymailfeed.Injection.Interceptor.FakeInterceptor;
import com.hoasen.studio.dailymailfeed.Utilities.Utilities;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;

/**
 * Created by Harry Nguyen on 09-Mar-16.
 */
@Module
public class DMNetworkClientModuleTest {

    @Provides
    @Singleton
    Interceptor provideInterceptor() {
        FakeInterceptor interceptor = new FakeInterceptor();
        String xmlString = Utilities.getXml("xml_test.xml");
        interceptor.setResponseString(xmlString);

        return interceptor;
    }


}