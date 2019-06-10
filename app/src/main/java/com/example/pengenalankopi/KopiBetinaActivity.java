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

public class KopiBetinaActivity extends AppCompatActivity implements View.OnClickListener {

//    deklarasi variabel nya
    private SliderLayout sliderLayoutBetina;
    private BottomNavigationView navigationView;
    private ImageView back_utama, addKopiBetina, editKopiBetina, deleteKopiBetina;
    private TextView deskripsiKopiBetina1, deskripsiKopiBetina2, deskripsiKopiBetina3, deskripsiKopiBetina4;
    private MenuItem login_button, logout_button;
    private Intent i;
    private DatabaseHelper databaseHelper;
    String currentActivity;
    SessionManager sessionManager;
    String[] img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kopi_betina);

        back_utama = findViewById(R.id.back_utama);
        back_utama.setOnClickListener(this);
        currentActivity = KopiBetinaActivity.this.getClass().getName();
        Log.d("nama", "current activity" + currentActivity);

        deskripsiKopiBetina1 = findViewById(R.id.text_kopi_betina_1);
        deskripsiKopiBetina2 = findViewById(R.id.text_kopi_betina_2);
        deskripsiKopiBetina3 = findViewById(R.id.text_kopi_betina_3);
        deskripsiKopiBetina4 = findViewById(R.id.text_kopi_betina_4);
        databaseHelper = new DatabaseHelper(this);


        sliderLayoutBetina = findViewById(R.id.imageSlider);
        sliderLayoutBetina.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayoutBetina.setScrollTimeInSec(5);
        buatImageSliderBetina();

        navigationView = findViewById(R.id.navigation);
        navigationDrawer();

        addKopiBetina = findViewById(R.id.tambah_kopi_betina);
        addKopiBetina.setOnClickListener(this);
        editKopiBetina = findViewById(R.id.edit_kopi_betina);
        editKopiBetina.setOnClickListener(this);
        deleteKopiBetina = findViewById(R.id.hapus_kopi_betina);
        deleteKopiBetina.setOnClickListener(this);

        Menu selectedItem = navigationView.getMenu();
        for(int i = 0; i< navigationView.getMenu().size(); i++){
            login_button = selectedItem.getItem(0);
            logout_button = selectedItem.getItem(1);
        }

        sessionManager = new SessionManager(this);
        HashMap <String, String> user = sessionManager.getUserLogin();
        String userHasLogin  = user.get(SessionManager.KEY_NAME);
        if (userHasLogin == null){
            Log.d("SOMEONE LOGIN? :", "ndak ada apa de");
        } else {
            Log.d("SOMEONE LOGIN? :", userHasLogin);
            addKopiBetina.setVisibility(View.VISIBLE);
            editKopiBetina.setVisibility(View.VISIBLE);
            deleteKopiBetina.setVisibility(View.VISIBLE);
            login_button.setVisible(false);
            logout_button.setVisible(true);
        }
        ambilData();
    }

    private void ambilData(){
//        1: Kopi Jantan, 2: Kopi Betina, 3: Kopi Luwak
        Cursor data = databaseHelper.ambilDeskripsiKopi(2);
        if (data !=null){
            int i =0;
            String[] dataKopiBetina = new String[data.getCount()];
            data.moveToFirst();
            while (!data.isAfterLast()){
                dataKopiBetina[i] = data.getString(1);
                i++;
                data.moveToNext();
            }
            if(data.getCount() == 0){
                editKopiBetina.setVisibility(View.GONE);
                deleteKopiBetina.setVisibility(View.GONE);
            } else if (data.getCount() == 1){
                deskripsiKopiBetina1.setText(dataKopiBetina[0]);
            } else if(data.getCount() == 2){
                deskripsiKopiBetina1.setText(dataKopiBetina[0]);
                deskripsiKopiBetina2.setText(dataKopiBetina[1]);
                deskripsiKopiBetina2.setVisibility(View.VISIBLE);
            } else if(data.getCount() == 3){
                deskripsiKopiBetina1.setText(dataKopiBetina[0]);
                deskripsiKopiBetina2.setText(dataKopiBetina[1]);
                deskripsiKopiBetina2.setVisibility(View.VISIBLE);
                deskripsiKopiBetina3.setText(dataKopiBetina[2]);
                deskripsiKopiBetina3.setVisibility(View.VISIBLE);
            } else if(data.getCount() == 4){
                deskripsiKopiBetina1.setText(dataKopiBetina[0]);
                deskripsiKopiBetina2.setText(dataKopiBetina[1]);
                deskripsiKopiBetina2.setVisibility(View.VISIBLE);
                deskripsiKopiBetina3.setText(dataKopiBetina[2]);
                deskripsiKopiBetina3.setVisibility(View.VISIBLE);
                deskripsiKopiBetina4.setText(dataKopiBetina[3]);
                deskripsiKopiBetina4.setVisibility(View.VISIBLE);
                addKopiBetina.setVisibility(View.GONE);
            }

        }
    }

    private void navigationDrawer(){
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.loginButton:
                        i = new Intent(KopiBetinaActivity.this, LoginActivity.class);
                        i.putExtra("previousActivity", currentActivity);
                        startActivity(i);
                        break;
                    case R.id.proses_pembuatan_kopi:
                        i = new Intent(KopiBetinaActivity.this, PengolahanKopiActivity.class);
                        i.putExtra("previousActivity", currentActivity);
                        startActivity(i);
                        break;
                    case R.id.tentang_saya:
                        i = new Intent(KopiBetinaActivity.this, TentangSayaActivity.class);
                        i.putExtra("previousActivity", currentActivity);
                        startActivity(i);
                        break;
                    case R.id.logoutButton:
                        Toast.makeText(KopiBetinaActivity.this, "Anda berhasil logout", Toast.LENGTH_SHORT).show();
                        sessionManager.logoutUser();
                        finish();
                        startActivity(getIntent());
                }
                return true;
            }
        });
    }

    private void buatImageSliderBetina() {
//        1: Kopi Jantan, 2: Kopi Betina, 3: Kopi Luwak
        Cursor data = databaseHelper.ambilFotoKopi(2);
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
        for (int i = 0; i <data.getCount(); i++){
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView.setImageUrl(img[i]);
            sliderView.setDescription("Foto Kopi Betina ke - " + (i+1));
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(KopiBetinaActivity.this, "Foto Kopi Betina ke -" + (finalI+1), Toast.LENGTH_SHORT).show();
                }
            });

            sliderLayoutBetina.addSliderView(sliderView);
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
            case R.id.tambah_kopi_betina:
                i = new Intent(this, tambahKopi.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("previousActivity", currentActivity);
                tambahKopi.sFrom = "Kopi Betina";
                startActivity(i);
                break;
            case R.id.edit_kopi_betina:
                i = new Intent(this, editKopi.class);
//                Toast.makeText(KopiBetinaActivity.this, "Klik edit Kopi", Toast.LENGTH_SHORT).show();
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("previousActivity", currentActivity);
                editKopi.editForm = "Kopi Betina";
                startActivity(i);
                break;
            case R.id.hapus_kopi_betina:
                i = new Intent(this, hapusKopi.class);
//                Toast.makeText(KopiBetinaActivity.this, "Klik edit Kopi", Toast.LENGTH_SHORT).show();
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("previousActivity", currentActivity);
                hapusKopi.hapusForm = "Kopi Betina";
                startActivity(i);
                break;
            default: break;
        }
    }
}
