package com.example.pengenalankopi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class tambahKopi extends AppCompatActivity implements View.OnClickListener {
    public static String sFrom;
    private TextInputLayout textInputPertama, textInputKedua, textInputKetiga, textInputKeempat;
    private EditText editTextPertama, editTextKedua, editTextKetiga, editTextKeempat;
    private String deskripsi1, deskripsi2, deskripsi3, deskripsi4;
    private Button buttonTambah;
    private ImageView backToKopi;
    private DatabaseHelper databaseHelper;
    private TextView judulTambah;
    private Cursor data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kopi);
        backToKopi = findViewById(R.id.back_to_kopi);
        backToKopi.setOnClickListener(this);
        setDataTextField();

        databaseHelper = new DatabaseHelper(this);
        getDeskripsiFromActivity();
    }

    private void setDataTextField(){
        judulTambah = findViewById(R.id.judulText);
        editTextPertama = findViewById(R.id.editTextPertama);
        editTextKedua = findViewById(R.id.editTextKedua);
        editTextKetiga = findViewById(R.id.editTextKetiga);
        editTextKeempat = findViewById(R.id.editTextKeempat);

        textInputPertama = findViewById(R.id.textInputPertama);
        textInputKedua = findViewById(R.id.textInputKedua);
        textInputKetiga = findViewById(R.id.textInputKetiga);
        textInputKeempat = findViewById(R.id.textInputKeempat);

        buttonTambah = findViewById(R.id.buttonTambah);
        buttonTambah.setOnClickListener(this);
    }

    private void getDeskripsiFromActivity(){
        Log.d("STRING DIKIRIM: ", sFrom);
        if (sFrom == "Kopi Jantan"){
            data = databaseHelper.ambilDeskripsiKopi(1);
            Log.d("JUMLAH DESKRIPSI 1:", String.valueOf(data.getCount()));
            buttonTambah.setId(R.id.buttonTambahKopiJantan);
            setDeskripsiVisibility("Tambah Deskripsi Kopi Jantan", "Tambah deskripsi Kopi Jantan !");
        } else if(sFrom == "Kopi Betina"){
            data = databaseHelper.ambilDeskripsiKopi(2);
            buttonTambah.setId(R.id.buttonTambahKopiBetina);
            Log.d("JUMLAH DESKRIPSI 2: ", String.valueOf(data.getCount()));
            setDeskripsiVisibility("Tambah Deskripsi Kopi Betina", "Tambah deskripsi Kopi Betina !");
        } else {
            data = databaseHelper.ambilDeskripsiKopi(3);
            buttonTambah.setId(R.id.buttonTambahKopiLuwak);
            Log.d("JUMLAH DESKRIPSI 3: ", String.valueOf(data.getCount()));
            setDeskripsiVisibility("Tambah Deskripsi Kopi Luwak", "Tambah deskripsi Kopi Luwak !");
        }
    }

    private void setDeskripsiVisibility(String judulText, String buttonText){
        judulTambah.setText(judulText);

        buttonTambah.setText(buttonText);
        if (data.getCount() == 0){
            textInputPertama.setVisibility(View.VISIBLE);
            textInputKedua.setVisibility(View.VISIBLE);
            textInputKetiga.setVisibility(View.VISIBLE);
            textInputKeempat.setVisibility(View.VISIBLE);
            editTextPertama.setVisibility(View.VISIBLE);
            editTextKedua.setVisibility(View.VISIBLE);
            editTextKetiga.setVisibility(View.VISIBLE);
            editTextKeempat.setVisibility(View.VISIBLE);
        } else if(data.getCount() == 1){
            textInputKedua.setVisibility(View.VISIBLE);
            textInputKetiga.setVisibility(View.VISIBLE);
            textInputKeempat.setVisibility(View.VISIBLE);
            editTextKedua.setVisibility(View.VISIBLE);
            editTextKetiga.setVisibility(View.VISIBLE);
            editTextKeempat.setVisibility(View.VISIBLE);
        } else if (data.getCount() == 2){
            textInputKetiga.setVisibility(View.VISIBLE);
            textInputKeempat.setVisibility(View.VISIBLE);
            editTextKetiga.setVisibility(View.VISIBLE);
            editTextKeempat.setVisibility(View.VISIBLE);
        } else if(data.getCount() == 3){
            textInputKeempat.setVisibility(View.VISIBLE);
            editTextKeempat.setVisibility(View.VISIBLE);
        }
    }

    private void linkKeActivity(){
        String previousActivity = getIntent().getStringExtra("previousActivity");
        Log.d("PREVIOUS ACTIVITY", previousActivity);
        try{
            Class fromClass = Class.forName(previousActivity);
            Intent i = new Intent(tambahKopi.this, fromClass);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private boolean validasiDeskripsiKopi(){
        boolean valid;
        deskripsi1 = editTextPertama.getText().toString();
        deskripsi2 = editTextKedua.getText().toString();
        deskripsi3 = editTextKetiga.getText().toString();
        deskripsi4 = editTextKeempat.getText().toString();

        if (editTextPertama.getVisibility() == View.VISIBLE){
            if ((deskripsi1.isEmpty() || deskripsi2.isEmpty()) || (deskripsi1.isEmpty() && deskripsi2.isEmpty())){
                valid =  false;
                if (deskripsi1.isEmpty()){
                    textInputPertama.setError("Mohon inputkan deskripsi pertama");
                } else if(deskripsi2.isEmpty()) {
                    textInputKedua.setError("Mohon inputkan deskripsi kedua");
                } else {
                    textInputPertama.setError("Mohon inputkan deskripsi pertama");
                    textInputKedua.setError("Mohon inputkan deskripsi kedua");
                }
            } else {
                valid = true;
                textInputPertama.setError(null);
                textInputKedua.setError(null);
            }
        } else if(editTextKedua.getVisibility() == View.VISIBLE){
            if (deskripsi2.isEmpty()){
                valid = false;
                textInputKedua.setError("Mohon inputkan deskripsi kedua");
            } else {
                valid = true;
                textInputKedua.setError(null);
            }
        } else if(editTextKetiga.getVisibility() == View.VISIBLE){
            if (deskripsi3.isEmpty()){
                valid = false;
                textInputKetiga.setError("Mohon inputkan deskripsi ketiga");
            } else {
                valid = true;
                textInputKetiga.setError(null);
            }
        } else {
            if (deskripsi4.isEmpty()){
                valid = false;
                textInputKeempat.setError("Mohon inputkan deskripsi keempat");
            } else {
                valid = true;
                textInputKeempat.setError(null);
            }
        }
        return valid;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back_to_kopi:
                linkKeActivity();
                break;
            case R.id.buttonTambahKopiJantan:
                if(validasiDeskripsiKopi()){
                    deskripsi1 = editTextPertama.getText().toString();
                    deskripsi2 = editTextKedua.getText().toString();
                    deskripsi3 = editTextKetiga.getText().toString();
                    deskripsi4 = editTextKeempat.getText().toString();
                    databaseHelper.tambahDeskripsiKopi(new KopiModel(null,"1", deskripsi1, deskripsi2, deskripsi3, deskripsi4));
                    Snackbar.make(buttonTambah, "Berhasil menambahkan deskripsi!", Snackbar.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            linkKeActivity();
                        }
                    }, Snackbar.LENGTH_LONG);
                }
                break;
            case R.id.buttonTambahKopiBetina:
                if(validasiDeskripsiKopi()){
                    deskripsi1 = editTextPertama.getText().toString();
                    deskripsi2 = editTextKedua.getText().toString();
                    deskripsi3 = editTextKetiga.getText().toString();
                    deskripsi4 = editTextKeempat.getText().toString();
                    databaseHelper.tambahDeskripsiKopi(new KopiModel(null,"2", deskripsi1, deskripsi2, deskripsi3, deskripsi4));
                    Snackbar.make(buttonTambah, "Berhasil menambahkan deskripsi!", Snackbar.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            linkKeActivity();
                        }
                    }, Snackbar.LENGTH_LONG);
                }
                break;
            case R.id.buttonTambahKopiLuwak:
                if(validasiDeskripsiKopi()){
                    deskripsi1 = editTextPertama.getText().toString();
                    deskripsi2 = editTextKedua.getText().toString();
                    deskripsi3 = editTextKetiga.getText().toString();
                    deskripsi4 = editTextKeempat.getText().toString();
                    databaseHelper.tambahDeskripsiKopi(new KopiModel(null,"3", deskripsi1, deskripsi2, deskripsi3, deskripsi4));
                    Snackbar.make(buttonTambah, "Berhasil menambahkan deskripsi!", Snackbar.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            linkKeActivity();
                        }
                    }, Snackbar.LENGTH_LONG);
                }
                break;
            default:
                break;
        }
    }
}
