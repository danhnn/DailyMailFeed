package com.hoasen.studio.dailymailfeed;

import android.content.Intent;
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
                    Intent i = new Intent(SplashActivity.this, AppIntroActivity.class);
                    startActivity(i);
            }
        });

        // Start the thread
        t.start();
    }
}