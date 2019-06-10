package com.example.pengenalankopi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "SessionPengenalanKopi";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";


    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void buatLoginSession(String username){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, username);
        editor.commit();
    }

    public void cekLogin(){
        if (!this.isLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public HashMap<String, String> getUserLogin(){
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_NAME, sharedPreferences.getString(KEY_NAME, null));
        return user;
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
    }
}
