package com.hoasen.studio.dailymailfeed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hoasen.studio.dailymailfeed.AppIntro.AppIntroActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //  Declare a new thread to do a preference check
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Launch app intro
                if(isFirstStart()){
                    launchAppIntro();
                }else{
                    launchMainActivity();
                }
            }
        });

        // Start the thread
        t.start();
    }

    boolean isFirstStart(){
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        boolean isFirstStart = getPrefs.getBoolean("firstStart", true);
        return isFirstStart;
    }

    void launchAppIntro(){
        Intent i = new Intent(SplashActivity.this, AppIntroActivity.class);
        startActivity(i);

        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor e = getPrefs.edit();
        e.putBoolean("firstStart", false);
        e.apply();
    }

    void launchMainActivity(){
        Intent i = new Intent(SplashActivity.this, MainNewsActivity.class);
        startActivity(i);
    }
}