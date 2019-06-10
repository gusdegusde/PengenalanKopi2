package com.example.pengenalankopi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import timber.log.Timber;

public class SplashScreenActivity extends AppCompatActivity {

//    deklarasi variabel
    private ProgressBar mProgress;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mProgress = findViewById(R.id.splash_screen_progress_bar);

//        untuk inisialisasi database nya
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.inisialisasiMasterMenuKopi();
        databaseHelper.inisialisasiDetilMenuKopi();
        databaseHelper.inisialisasiFotoKopi();
        databaseHelper.updateHasilCounter();

        new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

//    progress bar selama 10 detik
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

//    fungsi untuk menjalankan aktivity selanjutnya
    private void startApp(){
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
