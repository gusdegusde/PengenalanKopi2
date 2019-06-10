package com.example.pengenalankopi;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.HashMap;

public class KopiLuwakActivity extends AppCompatActivity implements View.OnClickListener {

    private SliderLayout sliderLayoutLuwak;
    private BottomNavigationView navigationView;
    private ImageView back_utama, addKopiLuwak, editKopiLuwak, deleteKopiLuwak;
    private TextView deskripsiKopiLuwak1, deskripsiKopiLuwak2, deskripsiKopiLuwak3, deskripsiKopiLuwak4;
    private MenuItem login_button, logout_button;
    private Intent i;
    private DatabaseHelper databaseHelper;
    String currentActivity;
    SessionManager sessionManager;
    String[] img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kopi_luwak);

        back_utama = findViewById(R.id.back_utama);
        back_utama.setOnClickListener(this);
        currentActivity = KopiLuwakActivity.this.getClass().getName();
        Log.d("nama", "current activvity"+currentActivity);

        deskripsiKopiLuwak1 = findViewById(R.id.text_kopi_luwak_1);
        deskripsiKopiLuwak2 = findViewById(R.id.text_kopi_luwak_2);
        deskripsiKopiLuwak3 = findViewById(R.id.text_kopi_luwak_3);
        deskripsiKopiLuwak4 = findViewById(R.id.text_kopi_luwak_4);
        databaseHelper = new DatabaseHelper(this);


        sliderLayoutLuwak = findViewById(R.id.imageSlider);
        sliderLayoutLuwak.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayoutLuwak.setScrollTimeInSec(5);
        buatImageSliderLuwak();

        navigationView = findViewById(R.id.navigation);
        navigationDrawer();

        addKopiLuwak = findViewById(R.id.tambah_kopi_luwak);
        addKopiLuwak.setOnClickListener(this);
        editKopiLuwak = findViewById(R.id.edit_kopi_luwak);
        editKopiLuwak.setOnClickListener(this);
        deleteKopiLuwak = findViewById(R.id.hapus_kopi_luwak);
        deleteKopiLuwak.setOnClickListener(this);

        Menu selectedItem = navigationView.getMenu();
        for (int i = 0; i < navigationView.getMenu().size(); i++){
            login_button = selectedItem.getItem(0);
            logout_button = selectedItem.getItem(1);
        }

        sessionManager = new SessionManager(this);
        HashMap <String, String> user = sessionManager.getUserLogin();
        String userHasLogin = user.get(SessionManager.KEY_NAME);
        if (userHasLogin == null){
            Log.d("SOMEONE LOGIN? :", "ndak ada apa de");
        } else {
            Log.d("SOMEONE LOGIN? :", userHasLogin);
            addKopiLuwak.setVisibility(View.VISIBLE);
            editKopiLuwak.setVisibility(View.VISIBLE);
            deleteKopiLuwak.setVisibility(View.VISIBLE);
            login_button.setVisible(false);
            logout_button.setVisible(true);
        }
        ambilData();
    }
    private void ambilData(){
//        1: Kopi Jantan, 2: Kopi Betina, 3: Kopi Luwak
        Cursor data = databaseHelper.ambilDeskripsiKopi(3);
        if (data != null){
            int i = 0;
            String[] dataKopiLuwak = new String[data.getCount()];
            data.moveToFirst();
            while (!data.isAfterLast()){
                dataKopiLuwak[i] = data.getString(1);
                i++;
                data.moveToNext();
            }
            if (data.getCount() == 0){
                editKopiLuwak.setVisibility(View.GONE);
                deleteKopiLuwak.setVisibility(View.GONE);
            } else if (data.getCount() == 1){
                deskripsiKopiLuwak1.setText(dataKopiLuwak[0]);
            } else if(data.getCount() == 2){
                deskripsiKopiLuwak1.setText(dataKopiLuwak[0]);
                deskripsiKopiLuwak2.setText(dataKopiLuwak[1]);
                deskripsiKopiLuwak2.setVisibility(View.VISIBLE);
            } else if(data.getCount() == 3){
                deskripsiKopiLuwak1.setText(dataKopiLuwak[0]);
                deskripsiKopiLuwak2.setText(dataKopiLuwak[1]);
                deskripsiKopiLuwak2.setVisibility(View.VISIBLE);
                deskripsiKopiLuwak3.setText(dataKopiLuwak[2]);
                deskripsiKopiLuwak3.setVisibility(View.VISIBLE);
            } else if(data.getCount() == 4){
                deskripsiKopiLuwak1.setText(dataKopiLuwak[0]);
                deskripsiKopiLuwak2.setText(dataKopiLuwak[1]);
                deskripsiKopiLuwak2.setVisibility(View.VISIBLE);
                deskripsiKopiLuwak3.setText(dataKopiLuwak[2]);
                deskripsiKopiLuwak3.setVisibility(View.VISIBLE);
                deskripsiKopiLuwak4.setText(dataKopiLuwak[3]);
                deskripsiKopiLuwak4.setVisibility(View.VISIBLE);
                addKopiLuwak.setVisibility(View.GONE);
            }

        }
    }

    private void navigationDrawer(){
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.loginButton:
                        i = new Intent(KopiLuwakActivity.this, LoginActivity.class);
                        i.putExtra("previousActivity", currentActivity);
                        startActivity(i);
                        break;
                    case R.id.proses_pembuatan_kopi:
                        i = new Intent(KopiLuwakActivity.this, PengolahanKopiActivity.class);
                        i.putExtra("previousActivity", currentActivity);
                        startActivity(i);
                        break;
                    case R.id.tentang_saya:
                        i = new Intent(KopiLuwakActivity.this, TentangSayaActivity.class);
                        i.putExtra("previousActivity", currentActivity);
                        startActivity(i);
                        break;
                    case R.id.logoutButton:
                        Toast.makeText(KopiLuwakActivity.this, "Anda berhasil logout", Toast.LENGTH_SHORT).show();
                        sessionManager.logoutUser();
                        finish();
                        startActivity(getIntent());
                }
                return true;
            }
        });
    }

    private void buatImageSliderLuwak() {
//        1: Kopi Jantan, 2: Kopi Betina, 3: Kopi Luwak
        Cursor data = databaseHelper.ambilFotoKopi(3);
        Log.d("JUMLAH GAMBAR :", String.valueOf(data.getCount()));
        if (data != null) {
            //more to the first row
            int i = 0;
            img = new String[data.getCount()];
            //iterate over rows
            data.moveToFirst();
            while (!data.isAfterLast()){
                img[i] = data.getString(0);
                i++;
                data.moveToNext();
            }
        }
        for (int i =0; i <data.getCount(); i++){
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView.setImageUrl(img[i]);
            sliderView.setDescription("Foto Kopi Luwak ke - "+ (i+1));

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(KopiLuwakActivity.this, "Foto Kopi Luwak ke- " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            sliderLayoutLuwak.addSliderView(sliderView);
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
            case R.id.tambah_kopi_luwak:
                i = new Intent(this, tambahKopi.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("previousActivity", currentActivity);
                tambahKopi.sFrom = "Kopi Luwak";
                startActivity(i);
                break;
            case R.id.edit_kopi_luwak:
                i = new Intent(this, editKopi.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("previousActivity", currentActivity);
                editKopi.editForm = "Kopi Luwak";
                startActivity(i);
                break;
            case R.id.hapus_kopi_luwak:
                i = new Intent(this, hapusKopi.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("previousActivity", currentActivity);
                hapusKopi.hapusForm = "Kopi Luwak";
                startActivity(i);
                break;
            default: break;
        }
    }
}
