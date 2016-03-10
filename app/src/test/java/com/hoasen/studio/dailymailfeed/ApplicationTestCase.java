package com.hoasen.studio.dailymailfeed;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import java.lang.reflect.Field;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.plugins.RxJavaTestPlugins;
import rx.schedulers.Schedulers;

/**
 * Base class for Robolectric data layer tests running with a custom Test Runner.
 * Inherit from this class to create a test.
 */
@RunWith(ApplicationTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public abstract class ApplicationTestCase {

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        overiderRXAndroidThreading();
    }

    void overiderRXAndroidThreading(){
        RxJavaTestPlugins.resetPlugins();
        RxJavaPlugins.getInstance().registerSchedulersHook(new RxJavaSchedulersHook() {
            @Override
            public Scheduler getIOScheduler() {
                return Schedulers.immediate();
            }
        });

        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });

    }
    @After
    public void tearDown() throws Exception {
        RxAndroidPlugins.getInstance().reset();
    }

    /**
     * Resets a Singleton class.
     * This works using reflection and looking for a private field in the singleton called
     * "INSTANCE".
     * It is actually a workaround (hack?) to avoid global state when testing in isolation.
     *
     * @param clazz The class to reset.
     */
    protected void resetSingleton(Class clazz) {
        Field instance;
        try {
            instance = clazz.getDeclaredField("INSTANCE");
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
