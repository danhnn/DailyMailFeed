package com.hoasen.studio.dailymailfeed;

import com.hoasen.studio.dailymailfeed.NewsDetail.Presenter.INewsDetailPresenter;
import com.hoasen.studio.dailymailfeed.NewsDetail.Presenter.NewDetailPresenterImpl;
import com.hoasen.studio.dailymailfeed.NewsDetail.View.INewsDetailView;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

/**
 * Created by Harry Nguyen on 05-Mar-16.
 */
public class NewsDetailPresenterTest extends ApplicationTestCase{

    @Mock
    INewsDetailView view;
    INewsDetailPresenter iNewsDetailPresenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        iNewsDetailPresenter = new NewDetailPresenterImpl();
        iNewsDetailPresenter.setView(view);
    }

    @Test
    public void showSnakeBarTest(){
        iNewsDetailPresenter.showSnackBar();
        verify(view).showSnackBar();
    }

    @Test
    public void updateDataTest(){
        iNewsDetailPresenter.updateData();
        verify(view).updateData();
    }
}
