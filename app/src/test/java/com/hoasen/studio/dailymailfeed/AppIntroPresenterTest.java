package com.hoasen.studio.dailymailfeed;

import android.os.Bundle;

import com.hoasen.studio.dailymailfeed.AppIntro.Presenter.AppIntroPresenterImpl;
import com.hoasen.studio.dailymailfeed.AppIntro.Presenter.IAppintroPresenter;
import com.hoasen.studio.dailymailfeed.AppIntro.View.IAppIntroView;
import com.hoasen.studio.dailymailfeed.Utilities.ConstantValue;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by Harry Nguyen on 05-Mar-16.
 */

public class AppIntroPresenterTest extends ApplicationTestCase{

    @Mock
    IAppIntroView view;

    IAppintroPresenter iAppintroPresenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        iAppintroPresenter = spy(new AppIntroPresenterImpl());
        iAppintroPresenter.setView(view);
    }

    @Test
    public void skipTest(){
        iAppintroPresenter.skip();
        verify(view).showMainView();
    }

    @Test
    public void doneTest(){
        iAppintroPresenter.done();
        verify(view).showMainView();
    }

    @Test
    public void getIntroDataTest(){
        List<Bundle> bundleList = iAppintroPresenter.getIntroData();
        Bundle data = bundleList.get(0);
        String title = data.getString(ConstantValue.INTRO_TITLE_KEY);
        assertThat(title,is("INTRO 0"));
    }
}
