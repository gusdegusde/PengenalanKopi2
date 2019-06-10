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

public class KopiJantanActivity extends AppCompatActivity implements View.OnClickListener {

    private SliderLayout sliderLayoutJantan;
    private BottomNavigationView navigationView;
    private ImageView back_utama, add_button, edit_button, delete_button;
    private TextView deskripsiKopiJantan1, deskripsiKopiJantan2, deskripsiKopiJantan3, deskripsiKopiJantan4;
    private MenuItem login_button, logout_button;
    private Intent i;
    private DatabaseHelper databaseHelper;
    String currentActivity;
    SessionManager sessionManager;
    String[] img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kopi_jantan);

        back_utama = findViewById(R.id.back_utama);
        back_utama.setOnClickListener(this);
        currentActivity = KopiJantanActivity.this.getClass().getName();
        Log.d("nama", "current activity" + currentActivity);

        deskripsiKopiJantan1 = findViewById(R.id.text_kopi_jantan_1);
        deskripsiKopiJantan2 = findViewById(R.id.text_kopi_jantan_2);
        deskripsiKopiJantan3 = findViewById(R.id.text_kopi_jantan_3);
        deskripsiKopiJantan4 = findViewById(R.id.text_kopi_jantan_4);

//        Ambil data dari Database Helper (ambilDataKopi)
        databaseHelper = new DatabaseHelper(this);


        sliderLayoutJantan = findViewById(R.id.imageSlider);
        sliderLayoutJantan.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayoutJantan.setScrollTimeInSec(5);
        buatImageSliderJantan();

        navigationView = findViewById(R.id.navigation);
        navigationDrawer();

        add_button = findViewById(R.id.tambah_kopi_jantan);
        add_button.setOnClickListener(this);
        edit_button = findViewById(R.id.edit_kopi_jantan);
        edit_button.setOnClickListener(this);
        delete_button = findViewById(R.id.hapus_kopi_jantan);
        delete_button.setOnClickListener(this);

        Menu selectedItem = navigationView.getMenu();
        for(int i = 0; i< navigationView.getMenu().size(); i++){
            login_button = selectedItem.getItem(0);
            logout_button = selectedItem.getItem(1);
        }

//        untuk login/ daftar
        sessionManager = new SessionManager(this);
        HashMap <String, String> user = sessionManager.getUserLogin();
        String userHasLogin = user.get(SessionManager.KEY_NAME);

//        untuk ngecek apakah sudah ada yang login atau belum?
        if (userHasLogin == null){
            Log.d("SOMEONE LOGIN? :", "ndak ada apa de");
        } else {
//            jika dia sudah ada yang login, maka baru dia bisa nambah , ngedit, hapus deskripsi
            Log.d("SOMEONE LOGIN? :", userHasLogin);
            add_button.setVisibility(View.VISIBLE);
            edit_button.setVisibility(View.VISIBLE);
            delete_button.setVisibility(View.VISIBLE);
//            kalau dia sudah login, tombol login nya hilang, diganti sama tomol logout
            login_button.setVisible(false);
            logout_button.setVisible(true);
        }
        ambilData();

    }

    private void ambilData(){
//        1: Kopi Jantan, 2: Kopi Betina, 3: Kopi Luwak
        Cursor data = databaseHelper.ambilDeskripsiKopi(1);
        if (data != null) {
            //more to the first row
            int i = 0;
            String[] dataKopiJantan = new String[data.getCount()];
            //iterate over rows
            data.moveToFirst();
            while (!data.isAfterLast()){
                dataKopiJantan[i] = data.getString(1);
                i++;
                data.moveToNext();
            }
            if (data.getCount() == 0){
                edit_button.setVisibility(View.GONE);
                delete_button.setVisibility(View.GONE);
            } if (data.getCount() == 1){
                deskripsiKopiJantan1.setText(dataKopiJantan[0]);
            } else if(data.getCount() == 2){
                deskripsiKopiJantan1.setText(dataKopiJantan[0]);
                deskripsiKopiJantan2.setText(dataKopiJantan[1]);
                deskripsiKopiJantan2.setVisibility(View.VISIBLE);
            } else if(data.getCount() == 3){
                deskripsiKopiJantan1.setText(dataKopiJantan[0]);
                deskripsiKopiJantan2.setText(dataKopiJantan[1]);
                deskripsiKopiJantan2.setVisibility(View.VISIBLE);
                deskripsiKopiJantan3.setText(dataKopiJantan[2]);
                deskripsiKopiJantan3.setVisibility(View.VISIBLE);
            } else if(data.getCount() == 4) {
                deskripsiKopiJantan1.setText(dataKopiJantan[0]);
                deskripsiKopiJantan2.setText(dataKopiJantan[1]);
                deskripsiKopiJantan2.setVisibility(View.VISIBLE);
                deskripsiKopiJantan3.setText(dataKopiJantan[2]);
                deskripsiKopiJantan3.setVisibility(View.VISIBLE);
                deskripsiKopiJantan4.setText(dataKopiJantan[3]);
                deskripsiKopiJantan4.setVisibility(View.VISIBLE);
                add_button.setVisibility(View.GONE);
            }
        }
    }

    private void navigationDrawer(){
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.loginButton:
                        i = new Intent(KopiJantanActivity.this, LoginActivity.class);
                        i.putExtra("previousActivity", currentActivity);
                        startActivity(i);
                        break;
                    case R.id.proses_pembuatan_kopi:
                        i = new Intent(KopiJantanActivity.this, PengolahanKopiActivity.class);
                        i.putExtra("previousActivity", currentActivity);
                        startActivity(i);
                        break;
                    case R.id.tentang_saya:
                        i = new Intent(KopiJantanActivity.this, TentangSayaActivity.class);
                        i.putExtra("previousActivity", currentActivity);
                        startActivity(i);
                        break;
                    case R.id.logoutButton:
                        Toast.makeText(KopiJantanActivity.this, "Anda berhasil logout", Toast.LENGTH_SHORT).show();
                        sessionManager.logoutUser();
                        finish();
                        startActivity(getIntent());
                }
                return true;
            }
        });
    }

//    untuk menampilkan foto slider di bawah deskripsi
    private void buatImageSliderJantan() {
//        1: Kopi Jantan, 2: Kopi Betina, 3: Kopi Luwak
        Cursor data = databaseHelper.ambilFotoKopi(1);
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
        for (int i = 0; i < data.getCount(); i++) {
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView.setImageUrl(img[i]);
            sliderView.setDescription("Foto Kopi Jantan ke - "+ (i+1));
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(KopiJantanActivity.this, "Foto Kopi Jantan ke- " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });
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
                Log.d("Button Clicked", "Button back Clicked");
                startActivity(i);
                break;
            case R.id.tambah_kopi_jantan:
                i = new Intent(this, tambahKopi.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("previousActivity", currentActivity);
                tambahKopi.sFrom = "Kopi Jantan";
                startActivity(i);
                Log.d("Button Clicked", "Button Tambah Kopi Jantan Clicked");
                break;
            case R.id.edit_kopi_jantan:
                i = new Intent(this, editKopi.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("previousActivity", currentActivity);
                editKopi.editForm = "Kopi Jantan";
                startActivity(i);
                Log.d("Button Clicked", "Button Edit Kopi Jantan Clicked");
                break;
            case R.id.hapus_kopi_jantan:
                i = new Intent(this, hapusKopi.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("previousActivity", currentActivity);
                hapusKopi.hapusForm = "Kopi Jantan";
                startActivity(i);
                Log.d("Button Clicked", "Button Hapus Kopi Jantan Clicked");
                break;
            default: break;
        }
    }
}
