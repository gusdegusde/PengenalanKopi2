package com.example.pengenalankopi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.text.Html;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    protected EditText editTextUsername, editTextPassword;
    protected String username, password;
    protected TextInputLayout textInputLayoutUsername, textInputLayoutPassword;
    public Button buttonLogin;

    DatabaseHelper databaseHelper;
//    SharedPreferences sharedPreferences;
//    SharedPreferences.Editor editor;
//    Boolean session = false;
//    public static final String my_shared_preferences = "my_shared_preferences";
//    public static final String session_status = "session_status";
//    private static final String IS_LOGIN = "isLoggedIn";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        tampilkanTextDaftarPegawai();
        lihatLogin();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validasiLogin()){
                    username = editTextUsername.getText().toString();
                    password = editTextPassword.getText().toString();

                    PegawaiModel pegawaiLogin = databaseHelper.validasiLogin(new PegawaiModel(null, null, username, password));

                    if (pegawaiLogin != null){
                        Snackbar.make(buttonLogin, "Berhasil login !", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
//                                checkSession();
                                sessionManager.buatLoginSession();
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    } else {
                        Snackbar.make(buttonLogin, "Gagal login, coba lagi", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

//
//    private void checkSession(){
//        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
//        session = sharedPreferences.getBoolean(session_status, true);
//        editor.putBoolean(IS_LOGIN, true);
//        editor.commit();
//        String previousActivity =  getIntent().getStringExtra("previousActivity");
//        Log.d("PREVIOUS ACTIVITY", previousActivity);
//        if(session){
//            try {
//                Class fromClass = Class.forName(previousActivity);
//                Intent i = new Intent(LoginActivity.this, fromClass);
//                finish();
//                startActivity(i);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private void tampilkanTextDaftarPegawai(){
        TextView textViewBuatAkun = findViewById(R.id.textViewBuatAkun);
        textViewBuatAkun.setText(fromHtml("<font color = '#FFFFFF'>Saya tidak memiliki akun. </font><font color='#FFA000'>Buat Akun</font>"));
        textViewBuatAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, DaftarActivity.class);
                startActivity(i);
            }
        });
    }

    private void lihatLogin() {
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        textInputLayoutUsername = findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    public boolean validasiLogin(){
        boolean valid;
        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();

        if (username.isEmpty()){
            valid = false;
            textInputLayoutUsername.setError("Mohon inputkan username anda");
        } else {
            valid = true;
            textInputLayoutUsername.setError(null);
        }

        if (password.isEmpty()){
            valid = false;
            textInputLayoutPassword.setError("Mohon inputkan password anda");
        } else {
            if (password.length() > 5){
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password terlalu pendek");
            }

        }
        return valid;
    }
}
