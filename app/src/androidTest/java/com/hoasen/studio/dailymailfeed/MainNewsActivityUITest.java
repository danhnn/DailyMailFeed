package com.hoasen.studio.dailymailfeed;

import android.support.test.annotation.UiThreadTest;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import android.test.suitebuilder.annotation.LargeTest;

import com.mikepenz.materialdrawer.Drawer;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsAnything.anything;

/**
 * Created by Harry Nguyen on 10-Mar-16.
 */

public class MainNewsActivityUITest extends BaseUITest {
    @Rule
    public ActivityTestRule<MainNewsActivity> mActivityRule = new ActivityTestRule(MainNewsActivity.class);

    @Override
    public void setUp() throws InterruptedException {
        super.setUp();

    }

    @Override
    public void tearDown() throws InterruptedException {
        super.tearDown();
    }

    void openDrawer(){
        mActivityRule.getActivity().runOnUiThread(() -> {
            mActivityRule.getActivity().getDrawer().openDrawer();
        });
    }

    void closeDrawer(){
        mActivityRule.getActivity().runOnUiThread(() -> {
            mActivityRule.getActivity().getDrawer().closeDrawer();
        });
    }

    @Test
    public void ItemNameOnDrawerTest(){
        openDrawer();
        onView(withText("Home")).check(matches(isDisplayed()));
        onView(withText("Tutorial")).check(matches(isDisplayed()));
        onView(withText("Github")).check(matches(isDisplayed()));
        closeDrawer();
    }


    @Test
    public void clickItemTest() throws InterruptedException {
        onView(withId(R.id.cardList)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.tvTitle)).check(matches(anything()));
    }

}
