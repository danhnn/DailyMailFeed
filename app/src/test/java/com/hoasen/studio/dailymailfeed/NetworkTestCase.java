package com.hoasen.studio.dailymailfeed;

import com.hoasen.studio.dailymailfeed.Inject.AppClientComponent;
import com.hoasen.studio.dailymailfeed.Inject.DMNetworkClientModuleTest;
import com.hoasen.studio.dailymailfeed.Inject.DaggerDMNetworkClientComponentTest;
import com.hoasen.studio.dailymailfeed.Inject.MyApplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.plugins.RxJavaTestPlugins;
import rx.schedulers.Schedulers;

/**
 * Created by Harry Nguyen on 10-Mar-16.
 */
@Ignore
public class NetworkTestCase extends ApplicationTestCase {
    public NetworkTestCase(){
        overrideRXJavaThreading();
        injectTestComponent();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        overrideRXAndroidThreading();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        RxAndroidPlugins.getInstance().reset();
    }

    void overrideRXJavaThreading(){
        RxJavaTestPlugins.resetPlugins();
        RxJavaPlugins.getInstance().registerSchedulersHook(new RxJavaSchedulersHook() {
            @Override
            public Scheduler getIOScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    void overrideRXAndroidThreading(){
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    void injectTestComponent(){
        AppClientComponent appClientComponent  =  DaggerDMNetworkClientComponentTest.builder().dMNetworkClientModuleTest(new DMNetworkClientModuleTest()).build();
        ((MyApplication) RuntimeEnvironment.application).setTestComponent(appClientComponent);
    }
}
