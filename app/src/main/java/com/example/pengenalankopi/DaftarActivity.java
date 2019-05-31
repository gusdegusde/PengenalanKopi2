package com.example.pengenalankopi;

import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DaftarActivity extends AppCompatActivity {

    EditText editTextNama, editTextUsername, editTextPassword;
    TextInputLayout textInputLayoutNama, textInputLayoutUsername, textInputLayoutPassword;
    Button buttonRegister;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        databaseHelper = new DatabaseHelper(this);
        tampilkanTextLoginPegawai();
        lihatViewDaftar();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validasiPegawaiBaru()){
                    String nama_lengkap = editTextNama.getText().toString();
                    String username = editTextUsername.getText().toString();
                    String password = editTextPassword.getText().toString();

                    if (!databaseHelper.jikaUsernamePegawaiAda(username, nama_lengkap)){
                        databaseHelper.tambahPegawai(new PegawaiModel(null, nama_lengkap, username, password));
                        Snackbar.make(buttonRegister, "Berhasil menambah akun ! Harap Login..", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    } else {
                        Snackbar.make(buttonRegister, "Username telah digunakan", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void tampilkanTextLoginPegawai(){
        TextView textViewLogin = findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void lihatViewDaftar(){
        editTextNama = findViewById(R.id.editTextNama);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        textInputLayoutNama = findViewById(R.id.textInputLayoutNama);
        textInputLayoutUsername = findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
    }

    public boolean validasiPegawaiBaru(){
        boolean valid;
        String nama_lengkap = editTextNama.getText().toString();
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if (nama_lengkap.isEmpty()){
            valid = false;
            textInputLayoutNama.setError("Mohon inputkan nama anda");
        } else {
            if (nama_lengkap.length()<5){
                valid = false;
                textInputLayoutNama.setError("Panjang minimal nama lengkap 5 karakter");
            } else {
                valid = true;
                textInputLayoutNama.setError(null);
            }
        }

        if (username.isEmpty()){
            valid = false;
            textInputLayoutUsername.setError("Mohon inputkan username anda");
        } else {
            if (username.length()<5){
                valid = false;
                textInputLayoutUsername.setError("Panjang minimal username 5 karakter");
            } else {
                valid = true;
                textInputLayoutUsername.setError(null);
            }
        }

        if (password.isEmpty()){
            valid = false;
            textInputLayoutPassword.setError("Mohon inputkan password anda");
        } else {
            if (password.length()<5){
                valid = false;
                textInputLayoutPassword.setError("Panjang minimal password 5 karakter");
            } else {
                valid = true;
                textInputLayoutPassword.setError(null);
            }
        }
        return valid;
    }


}
