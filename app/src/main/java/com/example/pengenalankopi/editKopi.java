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

public class editKopi extends AppCompatActivity implements View.OnClickListener {
    public static String editForm;
    private TextView judulText;
    private TextInputLayout textInputPertama, textInputKedua, textInputKetiga, textInputKeempat;
    private EditText editTextPertama, editTextKedua, editTextKetiga, editTextKeempat;
    private String deskripsi1, deskripsi2, deskripsi3, deskripsi4;
    private String newDeskripsi1, newDeskripsi2, newDeskripsi3, newDeskripsi4;
    private String idDeskripsi1, idDeskripsi2, idDeskripsi3, idDeskripsi4;
    private Button buttonEdit;
    private ImageView backToKopi;
    private DatabaseHelper databaseHelper;
    private Cursor data;
    private String[] deskripsiArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kopi);

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

        buttonEdit = findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(this);
    }

    private void getDeskripsiFromActivity(){
        Log.d("STRING DIKIRIM", editForm);
        if (editForm == "Kopi Jantan"){
//            1: Kopi Jantan, 2: Kopi Betina, 3: Kopi Luwak
            data = databaseHelper.ambilDeskripsiKopi(1);
            if (data != null){
                int i = 0;
                String[] dataKopiJantan  = new String[data.getCount()];
                String [] idKopiJantan = new String[data.getCount()];
                data.moveToFirst();
                while (!data.isAfterLast()){
                    idKopiJantan[i] = data.getString(0);
                    dataKopiJantan[i] = data.getString(1);
                    i++;
                    data.moveToNext();
                }
                judulText.setText("Edit deskripsi Kopi Jantan");
                buttonEdit.setId(R.id.buttonEditKopiJantan);
                buttonEdit.setText("Edit deskripsi Kopi Jantan");
                setVisibility(idKopiJantan, dataKopiJantan);
            }
        } else if (editForm == "Kopi Betina"){
//            1: Kopi Jantan, 2: Kopi Betina, 3: Kopi Luwak
            data = databaseHelper.ambilDeskripsiKopi(2);
            if (data != null){
                int i = 0;
                String[] dataKopiBetina  = new String[data.getCount()];
                String[] idKopiBetina = new String[data.getCount()];
                data.moveToFirst();
                while (!data.isAfterLast()){
                    idKopiBetina[i] = data.getString(0);
                    dataKopiBetina[i] = data.getString(1);
                    i++;
                    data.moveToNext();
                }
                judulText.setText("Edit deskripsi Kopi Betina");
                buttonEdit.setId(R.id.buttonEditKopiBetina);
                buttonEdit.setText("Edit deskripsi Kopi Betina");
                setVisibility(idKopiBetina, dataKopiBetina);
            }
        } else {
//            1: Kopi Jantan, 2: Kopi Betina, 3: Kopi Luwak
            data = databaseHelper.ambilDeskripsiKopi(3);
            if (data != null){
                int i = 0;
                String[] dataKopiLuwak  = new String[data.getCount()];
                String[] idKopiLuwak = new String[data.getCount()];
                data.moveToFirst();
                while (!data.isAfterLast()){
                    idKopiLuwak[i] = data.getString(0);
                    dataKopiLuwak[i] = data.getString(1);
                    i++;
                    data.moveToNext();
                }
                judulText.setText("Edit deskripsi Kopi Luwak");
                buttonEdit.setId(R.id.buttonEditKopiLuwak);
                buttonEdit.setText("Edit deskripsi Kopi Luwak");
                setVisibility(idKopiLuwak, dataKopiLuwak);
            }
        }
    }

    private void setVisibility(String[] id, String[] dataKopi){
        if (data.getCount() == 1){
            textInputPertama.setVisibility(View.VISIBLE);
            editTextPertama.setVisibility(View.VISIBLE);
            editTextPertama.setText(dataKopi[0]);
            deskripsi1 = editTextPertama.getText().toString();
            idDeskripsi1 = id[0];
        } else if(data.getCount() == 2){
            textInputPertama.setVisibility(View.VISIBLE);
            editTextPertama.setVisibility(View.VISIBLE);
            editTextPertama.setText(dataKopi[0]);
            textInputKedua.setVisibility(View.VISIBLE);
            editTextKedua.setVisibility(View.VISIBLE);
            editTextKedua.setText(dataKopi[1]);
            deskripsi1 = editTextPertama.getText().toString();
            deskripsi2 = editTextKedua.getText().toString();
            idDeskripsi1 = id[0];
            idDeskripsi2 = id[1];
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
            deskripsi1 = editTextPertama.getText().toString();
            deskripsi2 = editTextKedua.getText().toString();
            deskripsi3 = editTextKetiga.getText().toString();
            idDeskripsi1 = id[0];
            idDeskripsi2 = id[1];
            idDeskripsi3 = id[2];
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
            deskripsi1 = editTextPertama.getText().toString();
            deskripsi2 = editTextKedua.getText().toString();
            deskripsi3 = editTextKetiga.getText().toString();
            deskripsi4 = editTextKeempat.getText().toString();
            idDeskripsi1 = id[0];
            idDeskripsi2 = id[1];
            idDeskripsi3 = id[2];
            idDeskripsi4 = id[3];
        }
    }

    private void linkKeActivity(){
        String previousActivity = getIntent().getStringExtra("previousActivity");
        Log.d("PREVIOUS ACTIVITY", previousActivity);
        try {
        Class fromClass = Class.forName(previousActivity);
        Intent i = new Intent(editKopi.this, fromClass);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}

    private static boolean compareString (String oldDeskripsi, String newDeskripsi){
        boolean sama;
        if (newDeskripsi.equals(oldDeskripsi)){
            sama = true;
        } else {
            sama = false;
        }
        return sama;
    }

    private boolean validasiDeskripsiKopi(){
        boolean valid;
        newDeskripsi1 = editTextPertama.getText().toString();
        newDeskripsi2 = editTextKedua.getText().toString();
        newDeskripsi3 = editTextKetiga.getText().toString();
        newDeskripsi4 = editTextKeempat.getText().toString();
        if (data.getCount() == 4) {
            if (compareString(deskripsi1, newDeskripsi1)) {
                if (compareString(deskripsi2, newDeskripsi2)) {
                    if (compareString(deskripsi3, newDeskripsi3)) {
                        if (compareString(deskripsi4, newDeskripsi4)) {
                            valid = false;
                            Toast.makeText(editKopi.this, "SEMUA SAMA BANG (lewat jalur 1 getCount 4)", Toast.LENGTH_SHORT).show();
                        } else {
                            valid = true;
                            deskripsiArray = new String[]{newDeskripsi4};
                            databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi4, null, null, null, null, deskripsiArray[0]));
                            Toast.makeText(editKopi.this, "CUMA DESKRIPSI 4 YANG BEDA", Toast.LENGTH_SHORT).show();
                        }
                    } else if (compareString(deskripsi4, newDeskripsi4)) {
                        valid = true;
                        deskripsiArray = new String[]{newDeskripsi3};
                        databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, deskripsiArray[0], null));
                        Toast.makeText(editKopi.this, "CUMA DESKRIPSI 3 YANG BEDA", Toast.LENGTH_SHORT).show();
                    } else {
                        valid = true;
                        deskripsiArray = new String[]{newDeskripsi3, newDeskripsi4};
                        databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, deskripsiArray[0], null));
                        databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi4, null, null, null, null, deskripsiArray[1]));
                        Toast.makeText(editKopi.this, "CUMA DESKRIPSI 3 dan 4 YANG BEDA", Toast.LENGTH_SHORT).show();
                    }
                } else if (compareString(deskripsi3, newDeskripsi3)) {
                    valid = true;
                    deskripsiArray = new String[]{newDeskripsi2, newDeskripsi4};
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[0], null, null));
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi4, null, null, null, null, deskripsiArray[1]));
                    Toast.makeText(editKopi.this, "CUMA DESKRIPSI 2  dan 4 YANG BEDA", Toast.LENGTH_SHORT).show();
                } else if (compareString(deskripsi4, newDeskripsi4)) {
                    valid = true;
                    deskripsiArray = new String[]{newDeskripsi2, newDeskripsi3};
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[0], null, null));
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, deskripsiArray[1], null));
                    Toast.makeText(editKopi.this, "CUMA DESKRIPSI 2 dan 3 YANG BEDA", Toast.LENGTH_SHORT).show();
                } else if ((compareString(deskripsi3, newDeskripsi3)) && (compareString(deskripsi4, newDeskripsi4))) {
                    valid = true;
                    deskripsiArray = new String[]{newDeskripsi2};
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[0], null, null));
                    Toast.makeText(editKopi.this, "CUMA DESKRIPSI 2 YANG BEDA", Toast.LENGTH_SHORT).show();
                } else {
                    valid = true;
                    deskripsiArray = new String[]{newDeskripsi2, newDeskripsi3, newDeskripsi4};
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[0], null, null));
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, deskripsiArray[1], null));
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi4, null, null, null, null, deskripsiArray[2]));
                    Toast.makeText(editKopi.this, "CUMA DESKRIPSI 2, 3 dan 4 YANG BEDA", Toast.LENGTH_SHORT).show();
                }
            } else if (compareString(deskripsi2, newDeskripsi2)) {
                if (compareString(deskripsi3, newDeskripsi3)) {
                    if (compareString(deskripsi4, newDeskripsi4)) {
                        if (compareString(deskripsi1, newDeskripsi1)) {
                            valid = false;
                            Toast.makeText(editKopi.this, "SEMUA SAMA BANG (lewat jalur 2)", Toast.LENGTH_SHORT).show();
                        } else {
                            valid = true;
                            deskripsiArray = new String[]{newDeskripsi1};
                            databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi1, null,deskripsiArray[0], null, null, null));
                            Toast.makeText(editKopi.this, "CUMA DESKRIPSI 1 YANG BEDA", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        valid = true;
                        deskripsiArray = new String[]{newDeskripsi1, newDeskripsi4};
                        databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi1, null, deskripsiArray[0], null, null, null));
                        databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi4, null, null, null, null, deskripsiArray[1]));
                        Toast.makeText(editKopi.this, "CUMA DESKRIPSI 4 dan 1 YANG BEDA", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    valid = true;
                    deskripsiArray = new String[]{newDeskripsi1, newDeskripsi3, newDeskripsi4};
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi1, null, deskripsiArray[0], null, null, null));
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, deskripsiArray[1], null));
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi4, null, null, null, null, deskripsiArray[2]));
                    Toast.makeText(editKopi.this, "CUMA DESKRIPSI 3, 4 dan 1 YANG BEDA", Toast.LENGTH_SHORT).show();
                }
            } else if (compareString(deskripsi3, newDeskripsi3)) {
                if (compareString(deskripsi4, newDeskripsi4)) {
                    if (compareString(deskripsi1, newDeskripsi1)) {
                        if (compareString(deskripsi2, newDeskripsi2)) {
                            valid = false;
                            Toast.makeText(editKopi.this, "SEMUA SAMA BANG (lewat jalur 3)", Toast.LENGTH_SHORT).show();
                        } else {
                            valid = true;
                            deskripsiArray = new String[]{newDeskripsi2};
                            databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[0], null, null));
                            Toast.makeText(editKopi.this, "CUMA DESKRIPSI 2 YANG BEDA", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        valid = true;
                        deskripsiArray = new String[]{newDeskripsi1, newDeskripsi2};
                        databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi1, null, deskripsiArray[0], null, null, null));
                        databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[1], null, null));
                        Toast.makeText(editKopi.this, "CUMA DESKRIPSI 1 dan 2 YANG BEDA", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    valid = true;
                    deskripsiArray = new String[]{newDeskripsi1, newDeskripsi2, newDeskripsi4};
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi1, null, deskripsiArray[0], null, null, null));
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[1], null, null));
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi4, null, null, null, null, deskripsiArray[2]));
                    Toast.makeText(editKopi.this, "CUMA DESKRIPSI 4, 1 dan 2 YANG BEDA", Toast.LENGTH_SHORT).show();
                }
            } else if (compareString(deskripsi4, newDeskripsi4)) {
                if (compareString(deskripsi1, newDeskripsi1)) {
                    if (compareString(deskripsi2, newDeskripsi2)) {
                        if (compareString(deskripsi3, newDeskripsi3)) {
                            valid = false;
                            Toast.makeText(editKopi.this, "SEMUA SAMA BANG (lewat jalur 4)", Toast.LENGTH_SHORT).show();
                        } else {
                            valid = true;
                            deskripsiArray = new String[]{newDeskripsi3};
                            databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, deskripsiArray[0], null));
                            Toast.makeText(editKopi.this, "CUMA DESKRIPSI 3 YANG BEDA", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        valid = true;
                        deskripsiArray = new String[]{newDeskripsi2, newDeskripsi3};
                        databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[0], null, null));
                        databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, deskripsiArray[1], null));
                        Toast.makeText(editKopi.this, "CUMA DESKRIPSI 2 dan 3 YANG BEDA", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    valid = true;
                    deskripsiArray = new String[]{newDeskripsi1, newDeskripsi2, newDeskripsi3};
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi1, null, deskripsiArray[0], null, null, null));
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[1], null, null));
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, deskripsiArray[2], null));
                    Toast.makeText(editKopi.this, "CUMA DESKRIPSI 1, 2 dan 3 YANG BEDA", Toast.LENGTH_SHORT).show();
                }
            } else {
                valid = true;
                deskripsiArray = new String[]{newDeskripsi1, newDeskripsi2, newDeskripsi3, newDeskripsi4};
                databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi1, null, deskripsiArray[0], null, null, null));
                databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[1], null, null));
                databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, deskripsiArray[2], null));
                databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, null, deskripsiArray[3]));
                Toast.makeText(editKopi.this, "SEMUA BEDA BOSS", Toast.LENGTH_SHORT).show();
            }
        } else if(data.getCount() == 3){
            if (compareString(deskripsi1, newDeskripsi1)) {
                if (compareString(deskripsi2, newDeskripsi2)) {
                    if (compareString(deskripsi3, deskripsi3)) {
                        valid = false;
                        Toast.makeText(editKopi.this, "SEMUA SAMA BANG (lewat jalur 1)", Toast.LENGTH_SHORT).show();
                    } else {
                        valid = true;
                        deskripsiArray = new String[]{newDeskripsi3};
                        databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, deskripsiArray[0], null));
                        Toast.makeText(editKopi.this, "CUMA DESKRIPSI 3 YANG BEDA", Toast.LENGTH_SHORT).show();
                    }
                } else if (compareString(deskripsi3, newDeskripsi3)) {
                    valid = true;
                    deskripsiArray = new String[]{newDeskripsi2};
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[0], null, null));
                    Toast.makeText(editKopi.this, "CUMA DESKRIPSI 2 YANG BEDA", Toast.LENGTH_SHORT).show();
                } else {
                    valid = true;
                    deskripsiArray = new String[]{newDeskripsi2, newDeskripsi3};
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[0], null, null));
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, deskripsiArray[1], null));
                    Toast.makeText(editKopi.this, "CUMA DESKRIPSI 2 dan 3 YANG BEDA", Toast.LENGTH_SHORT).show();
                }
            } else if(compareString(deskripsi2, newDeskripsi2)){
                if (compareString(deskripsi3, newDeskripsi3)){
                    if (compareString(deskripsi1, newDeskripsi1)){
                        valid = false;
                        Toast.makeText(editKopi.this, "SEMUA SAMA BANG (lewat jalur 2)", Toast.LENGTH_SHORT).show();
                    } else {
                        valid = true;
                        deskripsiArray = new String[]{newDeskripsi1};
                        databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi1, null, deskripsiArray[0], null, null, null));
                        Toast.makeText(editKopi.this, "CUMA DESKRIPSI 1 YANG BEDA", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    valid = true;
                    deskripsiArray = new String[]{newDeskripsi1, newDeskripsi3};
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi1, null, deskripsiArray[0], null, null, null));
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, deskripsiArray[1], null));
                    Toast.makeText(editKopi.this, "CUMA DESKRIPSI 1 dan 3 YANG BEDA", Toast.LENGTH_SHORT).show();
                }
            } else if(compareString(deskripsi3, newDeskripsi3)){
                if (compareString(deskripsi1, newDeskripsi1)){
                    if (compareString(deskripsi2, newDeskripsi2)){
                        valid = false;
                        Toast.makeText(editKopi.this, "SEMUA SAMA BANG (lewat jalur 3)", Toast.LENGTH_SHORT).show();
                    } else {
                        valid = true;
                        deskripsiArray = new String[]{newDeskripsi2};
                        databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[0], null, null));
                        Toast.makeText(editKopi.this, "CUMA DESKRIPSI 2 YANG BEDA", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    valid = true;
                    deskripsiArray = new String[]{newDeskripsi1, newDeskripsi2};
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi1, null, deskripsiArray[0], null, null, null));
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[1], null, null));
                    Toast.makeText(editKopi.this, "CUMA DESKRIPSI 1 dan 2 YANG BEDA", Toast.LENGTH_SHORT).show();
                }
            } else {
                valid = true;
                deskripsiArray = new String[]{newDeskripsi1, newDeskripsi2, newDeskripsi3};
                databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi1, null, deskripsiArray[0], null, null, null));
                databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[1], null, null));
                databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi3, null, null, null, deskripsiArray[2], null));
                Toast.makeText(editKopi.this, "SEMUA BEDA BOS", Toast.LENGTH_SHORT).show();
            }
        } else if(data.getCount() == 2){
            if (compareString(deskripsi1, newDeskripsi1)){
                if (compareString(deskripsi2, newDeskripsi2)){
                    valid = false;
                    Toast.makeText(editKopi.this, "SEMUA SAMA BANG (lewat jalur 1)", Toast.LENGTH_SHORT).show();
                } else{
                    valid = true;
                    deskripsiArray = new String[]{newDeskripsi2};
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[0], null, null));
                    Toast.makeText(editKopi.this, "CUMA DESKRIPSI 2 YANG BEDA BANG", Toast.LENGTH_SHORT).show();
                }
            } else if(compareString(deskripsi2, newDeskripsi2)) {
                if (compareString(deskripsi1, newDeskripsi1)){
                    valid = false;
                    Toast.makeText(editKopi.this, "SEMUA SAMA BANG (lewat jalur 2)", Toast.LENGTH_SHORT).show();
                } else {
                    valid = true;
                    deskripsiArray = new String[]{newDeskripsi1};
                    databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi1, null, deskripsiArray[0], null, null, null));
                    Toast.makeText(editKopi.this, "CUMA DESKRIPSI 1 YANG BEDA BANG", Toast.LENGTH_SHORT).show();
                }
            } else {
                valid = true;
                deskripsiArray = new String[]{newDeskripsi1, newDeskripsi2};
                databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi1, null, deskripsiArray[0], null, null, null));
                databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi2, null, null, deskripsiArray[1], null, null));
                Toast.makeText(editKopi.this, "SEMUA BEDA BANG", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (compareString(deskripsi1, newDeskripsi1)){
                valid = false;
                Toast.makeText(editKopi.this, "SEMUA SAMA BANG (lewat jalur 1)", Toast.LENGTH_SHORT).show();
            } else {
                valid = true;
                deskripsiArray = new String[]{newDeskripsi1};
                databaseHelper.editDeskripsiKopi(new KopiModel(idDeskripsi1, null, deskripsiArray[0], null, null, null));
                Toast.makeText(editKopi.this, "DESKRIPSI 1 BEDA BANG", Toast.LENGTH_SHORT).show();
            }
        }
        return valid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_to_kopi:
                linkKeActivity();
                break;
            case R.id.buttonEditKopiJantan:
                if (validasiDeskripsiKopi()){
                    finish();
                    linkKeActivity();
                }
                break;
            case R.id.buttonEditKopiBetina:
                if (validasiDeskripsiKopi()){
                    finish();
                    linkKeActivity();
                }
                break;
            case R.id.buttonEditKopiLuwak:
                if (validasiDeskripsiKopi()){
                    finish();
                    linkKeActivity();
                }
                break;
            default:
                break;
        }
    }
}
