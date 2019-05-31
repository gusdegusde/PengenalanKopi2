package com.example.pengenalankopi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import timber.log.Timber;

public class SplashScreenActivity extends AppCompatActivity {
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mProgress = findViewById(R.id.splash_screen_progress_bar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork(){
        for (int progress = 0; progress<100; progress+=10){
            try{
                Thread.sleep(1000);
                mProgress.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Timber.e(e.getMessage());
            }
        }
    }

    private void startApp(){
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
