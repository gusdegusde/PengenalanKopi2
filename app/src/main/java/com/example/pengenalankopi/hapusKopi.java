package com.example.pengenalankopi;

import android.content.Intent;
import android.database.Cursor;
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

public class hapusKopi extends AppCompatActivity implements View.OnClickListener {
    public static String hapusForm;
    private TextView judulText;
    private TextInputLayout textInputPertama, textInputKedua, textInputKetiga, textInputKeempat;
    private EditText editTextPertama, editTextKedua, editTextKetiga, editTextKeempat;
    private String idDeskripsi1, idDeskripsi2, idDeskripsi3, idDeskripsi4;
    private ImageView buttonHapus1, buttonHapus2, buttonHapus3, buttonHapus4, backToKopi;
    private Button buttoHapus;
    private DatabaseHelper databaseHelper;
    private Cursor data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hapus_kopi);

        backToKopi = findViewById(R.id.back_to_kopi);
        backToKopi.setOnClickListener(this);
        setDataTextField();
        databaseHelper = new DatabaseHelper(this);
        getDeskripsiFromActivity();

    }

    private void setDataTextField(){
        judulText = findViewById(R.id.judulText);
        editTextPertama = findViewById(R.id.editTextPertama);
        editTextKedua = findViewById(R.id.editTextKedua);
        editTextKetiga = findViewById(R.id.editTextKetiga);
        editTextKeempat = findViewById(R.id.editTextKeempat);

        textInputPertama = findViewById(R.id.textInputPertama);
        textInputKedua = findViewById(R.id.textInputKedua);
        textInputKetiga = findViewById(R.id.textInputKetiga);
        textInputKeempat = findViewById(R.id.textInputKeempat);

        buttoHapus = findViewById(R.id.buttonHapus);
        buttoHapus.setOnClickListener(this);
        buttonHapus1 = findViewById(R.id.buttonHapusSatu);
        buttonHapus1.setOnClickListener(this);
        buttonHapus2 = findViewById(R.id.buttonHapusDua);
        buttonHapus2.setOnClickListener(this);
        buttonHapus3 = findViewById(R.id.buttonHapusTiga);
        buttonHapus3.setOnClickListener(this);
        buttonHapus4 = findViewById(R.id.buttonHapusEmpat);
        buttonHapus4.setOnClickListener(this);
    }

    private void getDeskripsiFromActivity(){
        Log.d("STRING DIKIRIM", hapusForm);
        if (hapusForm == "Kopi Jantan"){
            data = databaseHelper.ambilDeskripsiKopi(1);
            if (data != null){
                int i = 0;
                String[] dataKopiJantan = new String[data.getCount()];
                String[] idKopiJantan = new String[data.getCount()];
                data.moveToFirst();
                while (!data.isAfterLast()){
                    idKopiJantan[i] = data.getString(0);
                    dataKopiJantan[i] = data.getString(1);
                    i++;
                    data.moveToNext();
                }
                judulText.setText("Hapus deskripsi Kopi Jantan");
                buttoHapus.setId(R.id.buttonHapusKopiJantan);
                buttoHapus.setText("Hapus deskripsi Kopi Jantan");
                setVisibility(idKopiJantan, dataKopiJantan);
            }
        } else if(hapusForm == "Kopi Betina"){
            data = databaseHelper.ambilDeskripsiKopi(2);
            if (data != null){
                int i = 0;
                String[] dataKopiBetina = new String[data.getCount()];
                String[] idKopiBetina = new String[data.getCount()];
                data.moveToFirst();
                while (!data.isAfterLast()){
                    idKopiBetina[i] = data.getString(0);
                    dataKopiBetina[i] = data.getString(1);
                    i++;
                    data.moveToNext();
                }
                judulText.setText("Hapus deskripsi Kopi Betina");
                buttoHapus.setId(R.id.buttonHapusKopiJantan);
                buttoHapus.setText("Hapus deskripsi Kopi Betina");
                setVisibility(idKopiBetina, dataKopiBetina);
            }
        } else {
            data = databaseHelper.ambilDeskripsiKopi(3);
            if (data != null){
                int i = 0;
                String[] dataKopiLuwak = new String[data.getCount()];
                String[] idKopiLuwak = new String[data.getCount()];
                data.moveToFirst();
                while (!data.isAfterLast()){
                    idKopiLuwak[i] = data.getString(0);
                    dataKopiLuwak[i] = data.getString(1);
                    i++;
                    data.moveToNext();
                }
                judulText.setText("Hapus deskripsi Kopi Betina");
                buttoHapus.setId(R.id.buttonHapusKopiJantan);
                buttoHapus.setText("Hapus deskripsi Kopi Betina");
                setVisibility(idKopiLuwak, dataKopiLuwak);
            }
        }
    }

    private void setVisibility(String[] id, String[] dataKopi){
        if (data.getCount() == 1){
            textInputPertama.setVisibility(View.VISIBLE);
            editTextPertama.setVisibility(View.VISIBLE);
            buttonHapus1.setVisibility(View.VISIBLE);
            editTextPertama.setText(dataKopi[0]);
            idDeskripsi1 = id[0];
        } else if(data.getCount() == 2){
            textInputPertama.setVisibility(View.VISIBLE);
            editTextPertama.setVisibility(View.VISIBLE);
            editTextPertama.setText(dataKopi[0]);
            textInputKedua.setVisibility(View.VISIBLE);
            editTextKedua.setVisibility(View.VISIBLE);
            editTextKedua.setText(dataKopi[1]);
            idDeskripsi1 = id[0];
            idDeskripsi2 = id[1];
            buttonHapus1.setVisibility(View.VISIBLE);
            buttonHapus2.setVisibility(View.VISIBLE);
        } else if(data.getCount() == 3){
            textInputPertama.setVisibility(View.VISIBLE);
            editTextPertama.setVisibility(View.VISIBLE);
            editTextPertama.setText(dataKopi[0]);
            textInputKedua.setVisibility(View.VISIBLE);
            editTextKedua.setVisibility(View.VISIBLE);
            editTextKedua.setText(dataKopi[1]);
            textInputKetiga.setVisibility(View.VISIBLE);
            editTextKetiga.setVisibility(View.VISIBLE);
            editTextKetiga.setText(dataKopi[2]);
            idDeskripsi1 = id[0];
            idDeskripsi2 = id[1];
            idDeskripsi3 = id[2];
            buttonHapus1.setVisibility(View.VISIBLE);
            buttonHapus2.setVisibility(View.VISIBLE);
            buttonHapus3.setVisibility(View.VISIBLE);
        } else {
            textInputPertama.setVisibility(View.VISIBLE);
            editTextPertama.setVisibility(View.VISIBLE);
            editTextPertama.setText(dataKopi[0]);
            textInputKedua.setVisibility(View.VISIBLE);
            editTextKedua.setVisibility(View.VISIBLE);
            editTextKedua.setText(dataKopi[1]);
            textInputKetiga.setVisibility(View.VISIBLE);
            editTextKetiga.setVisibility(View.VISIBLE);
            editTextKetiga.setText(dataKopi[2]);
            textInputKeempat.setVisibility(View.VISIBLE);
            editTextKeempat.setVisibility(View.VISIBLE);
            editTextKeempat.setText(dataKopi[3]);
            idDeskripsi1 = id[0];
            idDeskripsi2 = id[1];
            idDeskripsi3 = id[2];
            idDeskripsi4 = id[3];
            buttonHapus1.setVisibility(View.VISIBLE);
            buttonHapus2.setVisibility(View.VISIBLE);
            buttonHapus3.setVisibility(View.VISIBLE);
            buttonHapus4.setVisibility(View.VISIBLE);
        }
    }

    private boolean validasiDeskripsiWillDelete(){
        boolean valid;
        if (data.getCount() == 4){
            if (editTextPertama.getVisibility() == View.GONE){
                if(editTextKedua.getVisibility() == View.GONE){
                    if (editTextKetiga.getVisibility() == View.GONE){
                        if (editTextKeempat.getVisibility() == View.GONE){
                            valid = true;
                            databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi1, null, null, null, null, null));
                            databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, null, null, null));
                            databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, null, null));
                            databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi4, null, null, null, null, null));
                            Toast.makeText(hapusKopi.this, "EDIT TEKS 1,2,3,4 DIDELETE", Toast.LENGTH_SHORT).show();
                        } else {
                            valid = true;
                            databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi1, null, null, null, null, null));
                            databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, null, null, null));
                            databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, null, null));
                            Toast.makeText(hapusKopi.this, "EDIT TEKS 1,2,3 DIDELETE", Toast.LENGTH_SHORT).show();
                        }
                    } else if(editTextKeempat.getVisibility() == View.GONE) {
                        valid = true;
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi1, null, null, null, null, null));
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, null, null, null));
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi4, null, null, null, null, null));
                        Toast.makeText(hapusKopi.this, "EDIT TEKS 1,2, 4 DIDELETE", Toast.LENGTH_SHORT).show();
                    } else {
                        valid = true;
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi1, null, null, null, null, null));
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, null, null, null));
                        Toast.makeText(hapusKopi.this, "EDIT TEKS 1,2 DIDELETE", Toast.LENGTH_SHORT).show();
                    }
                } else if(editTextKeempat.getVisibility() == View.GONE){
                    valid = true;
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi1, null, null, null, null, null));
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi4, null, null, null, null, null));
                    Toast.makeText(hapusKopi.this, "EDIT TEKS 1,4 DIDELETE", Toast.LENGTH_SHORT).show();
                } else if(editTextKetiga.getVisibility() == View.GONE){
                    valid = true;
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi1, null, null, null, null, null));
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, null, null));
                    Toast.makeText(hapusKopi.this, "EDIT TEKS 1,3 DIDELETE", Toast.LENGTH_SHORT).show();
                } else {
                    valid = true;
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi1, null, null, null, null, null));
                    Toast.makeText(hapusKopi.this, "EDIT TEKS 1 DIDELETE", Toast.LENGTH_SHORT).show();
                }
            } else if(editTextKedua.getVisibility() == View.GONE){
                if (editTextKetiga.getVisibility() == View.GONE){
                    if (editTextKeempat.getVisibility() == View.GONE){
                        valid = true;
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, null, null, null));
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, null, null));
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi4, null, null, null, null, null));
                        Toast.makeText(hapusKopi.this, "EDIT TEKS 2,3,4 DIDELETE", Toast.LENGTH_SHORT).show();
                    } else {
                        valid = true;
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, null, null, null));
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, null, null));
                        Toast.makeText(hapusKopi.this, "EDIT TEKS 2,3 DIDELETE", Toast.LENGTH_SHORT).show();
                    }
                } else if(editTextKeempat.getVisibility() == View.GONE){
                    valid = true;
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, null, null, null));
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi4, null, null, null, null, null));
                    Toast.makeText(hapusKopi.this, "EDIT TEKS 2,4 DIDELETE", Toast.LENGTH_SHORT).show();
                } else {
                    valid = true;
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, null, null, null));
                    Toast.makeText(hapusKopi.this, "EDIT TEKS 2 DIDELETE", Toast.LENGTH_SHORT).show();
                }
            } else if(editTextKetiga.getVisibility() == View.GONE){
                if (editTextKeempat.getVisibility() == View.GONE){
                    valid = true;
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, null, null));
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi4, null, null, null, null, null));
                    Toast.makeText(hapusKopi.this, "EDIT TEKS 3,4 DIDELETE", Toast.LENGTH_SHORT).show();
                } else{
                    valid = true;
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, null, null));
                    Toast.makeText(hapusKopi.this, "EDIT TEKS 3 DIDELETE", Toast.LENGTH_SHORT).show();
                }
            } else if(editTextKeempat.getVisibility() == View.GONE){
                valid = true;
                databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi4, null, null, null, null, null));
                Toast.makeText(hapusKopi.this, "EDIT TEKS 4 DIDELETE", Toast.LENGTH_SHORT).show();
            } else {
                valid = false;
                Toast.makeText(hapusKopi.this, "TIDAK ADA YANG DIDELETE", Toast.LENGTH_SHORT).show();
            }
        } else if(data.getCount() == 3){
            if (editTextPertama.getVisibility() == View.GONE){
                if(editTextKedua.getVisibility() == View.GONE){
                    if (editTextKetiga.getVisibility() == View.GONE){
                        valid = true;
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi1, null, null, null, null, null));
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, null, null, null));
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, null, null));
                        Toast.makeText(hapusKopi.this, "EDIT TEKS 1,2,3 DIDELETE", Toast.LENGTH_SHORT).show();
                    } else {
                        valid = true;
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi1, null, null, null, null, null));
                        databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, null, null, null));
                        Toast.makeText(hapusKopi.this, "EDIT TEKS 1,2 DIDELETE", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    valid = true;
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi1, null, null, null, null, null));
                    Toast.makeText(hapusKopi.this, "EDIT TEKS 1 DIDELETE", Toast.LENGTH_SHORT).show();
                }
            } else if(editTextKedua.getVisibility() == View.GONE){
                if (editTextKetiga.getVisibility() == View.GONE){
                    valid = true;
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, null, null, null));
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, null, null));
                    Toast.makeText(hapusKopi.this, "EDIT TEKS 2,3 DIDELETE", Toast.LENGTH_SHORT).show();
                } else {
                    valid = true;
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, null, null, null));
                    Toast.makeText(hapusKopi.this, "EDIT TEKS 2 DIDELETE", Toast.LENGTH_SHORT).show();
                }
            } else if(editTextKetiga.getVisibility() == View.GONE){
                valid = true;
                databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, null, null));
                Toast.makeText(hapusKopi.this, "EDIT TEKS 3 DIDELETE", Toast.LENGTH_SHORT).show();
            } else {
                valid = false;
                Toast.makeText(hapusKopi.this, "TIDAK ADA YANG DIDELETE", Toast.LENGTH_SHORT).show();
            }
        } else if(data.getCount() == 2){
            if (editTextPertama.getVisibility() == View.GONE){
                if(editTextKedua.getVisibility() == View.GONE){
                    valid = true;
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi1, null, null, null, null, null));
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, null, null, null));
                    Toast.makeText(hapusKopi.this, "EDIT TEKS 1,2 DIDELETE", Toast.LENGTH_SHORT).show();
                } else {
                    valid = true;
                    databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi1, null, null, null, null, null));
                    Toast.makeText(hapusKopi.this, "EDIT TEKS 1 DIDELETE", Toast.LENGTH_SHORT).show();
                }
            } else if(editTextKedua.getVisibility() == View.GONE){
                valid = true;
                databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, null, null, null));
                Toast.makeText(hapusKopi.this, "EDIT TEKS 2 DIDELETE", Toast.LENGTH_SHORT).show();
            } else {
                valid = false;
                Toast.makeText(hapusKopi.this, "TIDAK ADA YANG DIDELETE", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (editTextPertama.getVisibility() == View.GONE){
                valid = true;
                databaseHelper.hapusDeskripsiKopi(new KopiModel(idDeskripsi1, null, null, null, null, null));
                Toast.makeText(hapusKopi.this, "EDIT TEKS PERTAMA DIDELETE", Toast.LENGTH_SHORT).show();
            } else {
                valid = false;
                Toast.makeText(hapusKopi.this, "TIDAK ADA YANG DIDELETE", Toast.LENGTH_SHORT).show();
            }
        }
        return valid;
    }

    private void linkKeActivity(){
        String previousActivity = getIntent().getStringExtra("previousActivity");
        Log.d("PREVIOUS ACTIVITY", previousActivity);
        try {
            Class fromClass = Class.forName(previousActivity);
            Intent i = new Intent(hapusKopi.this, fromClass);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_to_kopi:
                finish();
                linkKeActivity();
                break;
            case R.id.buttonHapusSatu:
                textInputPertama.setHint("Paragraf Pertama Akan Dihapus");
                editTextPertama.setVisibility(View.GONE);
                buttonHapus1.setVisibility(View.GONE);
                break;
            case R.id.buttonHapusDua:
                textInputKedua.setHint("Paragraf Kedua Akan Dihapus");
                editTextKedua.setVisibility(View.GONE);
                buttonHapus2.setVisibility(View.GONE);
                break;
            case R.id.buttonHapusTiga:
                textInputKetiga.setHint("Paragraf Ketiga Akan Dihapus");
                editTextKetiga.setVisibility(View.GONE);
                buttonHapus3.setVisibility(View.GONE);
                break;
            case R.id.buttonHapusEmpat:
                textInputKeempat.setHint("Paragraf Keempat Akan Dihapus");
                editTextKeempat.setVisibility(View.GONE);
                buttonHapus4.setVisibility(View.GONE);
                break;
            case R.id.buttonHapusKopiJantan:
                if(validasiDeskripsiWillDelete()){
                    finish();
                    linkKeActivity();
                }
                break;
            default:
                break;
        }
    }
}
