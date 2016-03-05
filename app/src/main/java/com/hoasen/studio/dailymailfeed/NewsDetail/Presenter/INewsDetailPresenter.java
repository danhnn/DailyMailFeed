package com.hoasen.studio.dailymailfeed.NewsDetail.Presenter;

import android.view.View;

import com.hoasen.studio.dailymailfeed.Base.IPresenter;
import com.hoasen.studio.dailymailfeed.NewsDetail.View.INewsDetailView;

/**
 * Created by Harry Nguyen on 02-Mar-16.
 */
public interface INewsDetailPresenter extends IPresenter<INewsDetailView> {
    void showSnackBar();
    void updateData();
}
