package com.example.pengenalankopi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "db_pengenalan_kopi.db";
    private static int DATABASE_VERSION = 1;

//    Deklarasi variabel untuk Tabel Pegawai - Login
    private static String TABLE_PEGAWAI = "tb_pegawai";
    private static String KEY_PEGAWAI_ID = "id_pegawai";
    private static String KEY_PEGAWAI_NAMA = "nama_lengkap";
    private static String KEY_PEGAWAI_USERNAME = "username";
    private static String KEY_PEGAWAI_PASSWORD = "password";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tb_pegawai (id_pegawai INTEGER PRIMARY KEY AUTOINCREMENT, nama_lengkap TEXT, username TEXT, password TEXT)");
        Log.d("CREATE DATABASE", "Create " + TABLE_PEGAWAI + " Successfully.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PEGAWAI);
        Log.d("DROP TABLE ", "Drop" +TABLE_PEGAWAI+ "Successfully");
        onCreate(db);
    }

    public void tambahPegawai(PegawaiModel pegawaiModel){
        SQLiteDatabase db = this.getWritableDatabase();

//        deklarasi input pegawai
        ContentValues values = new ContentValues();
        values.put(KEY_PEGAWAI_NAMA, pegawaiModel.nama_lengkap);
        values.put(KEY_PEGAWAI_USERNAME, pegawaiModel.username);
        values.put(KEY_PEGAWAI_PASSWORD, pegawaiModel.password);

//        insert ke database
        long insert_pegawai = db.insert(TABLE_PEGAWAI, null, values);
    }

    public PegawaiModel validasiLogin(PegawaiModel pegawaiModel){
        SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.query(TABLE_PEGAWAI ,
                new String[]{KEY_PEGAWAI_ID, KEY_PEGAWAI_NAMA, KEY_PEGAWAI_USERNAME, KEY_PEGAWAI_USERNAME, KEY_PEGAWAI_PASSWORD},
                KEY_PEGAWAI_USERNAME + "=? AND " + KEY_PEGAWAI_PASSWORD + "=?" ,
                new String[]{pegawaiModel.username, pegawaiModel.password},
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount()>0){
            PegawaiModel pegawaiModel1 = new PegawaiModel(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                    );

            if (pegawaiModel.password.equalsIgnoreCase(pegawaiModel1.password)){
                return pegawaiModel1;
            }
        }
        return null;
    }

    public boolean jikaUsernamePegawaiAda(String username, String nama_lengkap){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PEGAWAI ,
                new String[]{KEY_PEGAWAI_ID, KEY_PEGAWAI_NAMA, KEY_PEGAWAI_USERNAME, KEY_PEGAWAI_USERNAME, KEY_PEGAWAI_PASSWORD},
                KEY_PEGAWAI_USERNAME + "=? AND " + KEY_PEGAWAI_NAMA + "=?" ,
                new String[]{username, nama_lengkap},
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount()>0){
            return true;
        }
        return false;
    }
}
