package com.hoasen.studio.dailymailfeed;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * Created by Harry Nguyen on 10-Mar-16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BaseUITest {
    final int delayTime = 500;

    @Before
    public void setUp() throws InterruptedException {
        Thread.sleep(delayTime);
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(delayTime);
    }
}
