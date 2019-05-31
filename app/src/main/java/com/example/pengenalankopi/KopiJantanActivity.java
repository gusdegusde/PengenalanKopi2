package com.example.pengenalankopi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.HashMap;

public class KopiJantanActivity extends AppCompatActivity implements View.OnClickListener {

    private SliderLayout sliderLayoutJantan;
    private BottomNavigationView navigationView;
    private ImageView back_utama, add_button, edit_button, delete_button;
    String currentActivity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kopi_jantan);

        back_utama = findViewById(R.id.back_utama);
        back_utama.setOnClickListener(this);
        currentActivity = KopiJantanActivity.this.getClass().getName();
        Log.d("nama", "current activity" + currentActivity);

        sessionManager = new SessionManager(this);

        sliderLayoutJantan = findViewById(R.id.imageSlider);
        sliderLayoutJantan.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayoutJantan.setScrollTimeInSec(1);
        buatImageSliderJantan();

        navigationView = findViewById(R.id.navigation);
        navigationDrawer();

        add_button = findViewById(R.id.tambah_kopi_jantan);
        edit_button = findViewById(R.id.edit_kopi_jantan);
        delete_button = findViewById(R.id.edit_kopi_jantan);


        sessionManager.cekLogin();

//        if (sessionManager.buatLoginSession(value) == true){
//            HashMap<String , String> user = sessionManager.getUserLogin();
//            String isLoggedIn = user.get(SessionManager.SUDAH_LOGIN);
//            Log.d("SESSION LOGIN", isLoggedIn );
//        } else {
//            Log.d("SESSION LOGIN", "Belum login" );
//        }

    }

    private void navigationDrawer(){
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.loginButton:
                        Intent i = new Intent(KopiJantanActivity.this, LoginActivity.class);
                        i.putExtra("previousActivity", currentActivity);
                        startActivity(i);
                        break;
                    case R.id.proses_pembuatan_kopi:
                        Toast.makeText(KopiJantanActivity.this, "Teknik Pembuatan Kopi", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tentang_saya:
                        Toast.makeText(KopiJantanActivity.this, "Tentang saya", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }


    private void buatImageSliderJantan() {

        for (int i = 0; i <= 1; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(this);

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.kopijantan1);
                    sliderView.setDescription("Foto Kopi Jantan Pertama");
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.kopijantan2);
                    sliderView.setDescription("Foto Kopi Jantan Kedua");
                    break;

            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(KopiJantanActivity.this, "Foto Kopi Jantan ke- " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            sliderLayoutJantan.addSliderView(sliderView);
        }
    }

    @Override
    public void onClick(View v){
        Intent i;
        switch (v.getId()){
            case R.id.back_utama:
                i = new Intent(this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            default: break;
        }
    }
}
