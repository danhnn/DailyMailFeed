package com.hoasen.studio.dailymailfeed.AppIntro.Presenter;

import android.os.Bundle;

import com.hoasen.studio.dailymailfeed.AppIntro.View.IAppIntroView;
import com.hoasen.studio.dailymailfeed.Base.IPresenter;

import java.util.List;

/**
 * Created by Harry Nguyen on 29-Feb-16.
 */
public interface IAppintroPresenter extends IPresenter<IAppIntroView> {
     List<Bundle> getIntroData();
     void skip();
     void done();
}
