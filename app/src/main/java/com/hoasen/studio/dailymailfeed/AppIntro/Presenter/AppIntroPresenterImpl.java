package com.hoasen.studio.dailymailfeed.AppIntro.Presenter;

import android.os.Bundle;

import com.hoasen.studio.dailymailfeed.AppIntro.AppIntroductionFragment;
import com.hoasen.studio.dailymailfeed.AppIntro.View.IAppIntroView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry Nguyen on 29-Feb-16.
 */
public class AppIntroPresenterImpl implements IAppintroPresenter{

    IAppIntroView iAppIntroView;

    @Override
    public List<Bundle> getIntroData() {
        List<Bundle> bundleList = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            Bundle args = new Bundle();
            args.putString("intro_title","TITLE " + i);
            args.putInt("intro_image",i);
            bundleList.add(args);
         }

        return bundleList;
    }

    @Override
    public void skip() {
        iAppIntroView.showMainView();
    }

    @Override
    public void done() {
        iAppIntroView.showMainView();
    }

    @Override
    public void setView(IAppIntroView view) {
        this.iAppIntroView = view;
    }
}
