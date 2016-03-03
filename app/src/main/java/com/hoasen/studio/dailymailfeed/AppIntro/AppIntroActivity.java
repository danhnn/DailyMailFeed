package com.hoasen.studio.dailymailfeed.AppIntro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.hoasen.studio.dailymailfeed.AppIntro.Presenter.AppIntroPresenterImpl;
import com.hoasen.studio.dailymailfeed.AppIntro.Presenter.IAppintroPresenter;
import com.hoasen.studio.dailymailfeed.AppIntro.View.IAppIntroView;
import com.hoasen.studio.dailymailfeed.MainNews.MainNewsActivity;

import java.util.List;

public class AppIntroActivity extends AppIntro implements IAppIntroView {

    IAppintroPresenter appintroPresenter;

    @Override
    public void init(Bundle savedInstanceState) {
        // Add your slide's fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        appintroPresenter = new AppIntroPresenterImpl();
        appintroPresenter.setView(this);

        List<Bundle> bundleList = appintroPresenter.getIntroData();

        for(int i = 0; i < 4; i++) {
            addSlide(AppIntroductionFragment.newInstance(bundleList.get(i)));
        }

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }


    @Override
    public void onSkipPressed() {
        appintroPresenter.skip();
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        appintroPresenter.done();
    }

    @Override
    public void onSlideChanged() {

    }

    @Override
    public void showMainView() {
        Intent i = new Intent(this, MainNewsActivity.class);
        startActivity(i);
    }
}
