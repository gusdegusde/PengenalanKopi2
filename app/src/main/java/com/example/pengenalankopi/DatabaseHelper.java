package com.example.pengenalankopi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

//    deklarasi variabel nama database, nama tabel dll nya
    Context ctx;
    private static String DATABASE_NAME = "db_pengenalan_kopi.db";
    private static int DATABASE_VERSION = 1;
    private int hasil_cursor;

//    Deklarasi variabel untuk Tabel Pegawai - Login
    private static String TABLE_PEGAWAI = "tb_pegawai";
    private static String KEY_PEGAWAI_ID = "id_pegawai";
    private static String KEY_PEGAWAI_NAMA = "nama_lengkap";
    private static String KEY_PEGAWAI_USERNAME = "username";
    private static String KEY_PEGAWAI_PASSWORD = "password";

//    Deklarasi variabel untuk Tabel Master Kopi - Kopi Jantan, Kopi Betina, Kopi Luwak;
    private static String TABLE_MASTER_KOPI = "tb_master_kopi";
    private static String TABLE_DETIL_KOPI = "tb_detil_kopi";
    private static String KEY_MASTER_KOPI_ID = "id_master_kopi";
    private static String KEY_NAMA_KOPI = "nama_kopi";
    private static String KEY_DETIL_KOPI_ID = "id_detil_kopi";
    private static String KEY_DESKRIPSI_KOPI = "deskripsi_kopi";
    private static String TABLE_FOTO_KOPI = "tb_foto_kopi";
    private static String KEY_FOTO_KOPI = "foto_kopi";

//    Deklarasi variabel untuk tabel view;
    private static String TABLE_VIEW_KOPI = "v_detil_kopi";

//    Deklarasi variabel untuk tabel counter
    private static String TABLE_COUNTER = "tb_counter";
    private static String KEY_COUNTER = "counter";
    private String[] hasilArray;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        untuk bikin tabel didalam database db_pengenalan_kopi.db
        db.execSQL("CREATE TABLE tb_pegawai (id_pegawai INTEGER PRIMARY KEY AUTOINCREMENT, nama_lengkap TEXT, username TEXT, password TEXT)");
        db.execSQL("CREATE TABLE tb_master_kopi (id_master_kopi INTEGER PRIMARY KEY AUTOINCREMENT, nama_kopi TEXT)");
        db.execSQL("CREATE TABLE tb_detil_kopi (id_detil_kopi INTEGER PRIMARY KEY AUTOINCREMENT, id_master_kopi INTEGER, deskripsi_kopi TEXT)");
        db.execSQL("CREATE TABLE tb_foto_kopi (id_foto_kopi INTEGER PRIMARY KEY AUTOINCREMENT, id_master_kopi INTEGER, foto_kopi TEXT)");
        db.execSQL("CREATE TABLE tb_counter (counter INTEGER)");
        db.execSQL("INSERT INTO tb_counter(counter) VALUES (1) ");
        Log.d("CREATE DATABASE", "Create " + TABLE_PEGAWAI + " Successfully.");

        Log.d("CREATE DATABASE", "Create " + TABLE_MASTER_KOPI + " Successfully.");
        Log.d("CREATE DATABASE", "Create " + TABLE_DETIL_KOPI + "Successfully");

        Log.d("CREATE DATABASE", "Create " + TABLE_FOTO_KOPI + " Successfully.");
//        Log.d("CREATE DATABASE", "Create " + TABLE_VIEW_KOPI + "Successfully");
        Log.d("CREATE DATABASE", "Create Counter Successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PEGAWAI);
        Log.d("DROP TABLE ", "Drop" +TABLE_PEGAWAI+ "Successfully");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_MASTER_KOPI);
        Log.d("DROP TABLE ", "Drop" +TABLE_MASTER_KOPI+ "Successfully");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_DETIL_KOPI);
        Log.d("DROP TABLE ", "Drop" +TABLE_DETIL_KOPI+ "Successfully");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_FOTO_KOPI);
        Log.d("DROP TABLE ", "Drop" +TABLE_FOTO_KOPI+ "Successfully");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_COUNTER);
        Log.d("DROP TABLE ", "Drop" +TABLE_COUNTER+ "Successfully");
        onCreate(db);
    }

/*
   * DI BAWAH INI SEMUA MERUPAKAN SEGALA FUNGSI UNTUK LOGIN DAN DAFTAR PEGAWAI
   * tambahPegawai(), validasiLogin(),jikaUsernamePegawaiAda()
 */

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
                new String[]{KEY_PEGAWAI_ID, KEY_PEGAWAI_NAMA, KEY_PEGAWAI_USERNAME, KEY_PEGAWAI_PASSWORD},
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
/*
   * DI BAWAH INI SEMUA MERUPAKAN SEGALA FUNGSI UNTUK INISIALISASI DATA MASTER KOPI DAN DETIL DATA KOPI
   * hasilCursor(), inisialisasiMenuKopi(),inisialisasiDetilMenuKopi(), updateHasilCounter()
 */

    public void hasilCursor(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_COUNTER, new String[]{KEY_COUNTER}, KEY_COUNTER + "=?" , new String[]{"1"}, null, null, null);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount()>0){
            hasil_cursor = cursor.getInt(0);
        }
        Log.d("HASIL CURSOR", String.valueOf(hasil_cursor));
    }

    public void inisialisasiMasterMenuKopi() {
        hasilCursor();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String[] data_kopi = new String[]{"Kopi Jantan", "Kopi Betina", "Kopi Luwak"};
        if (hasil_cursor == 1){
            for(int i = 0; i < data_kopi.length; i++){
                values.put(KEY_NAMA_KOPI, data_kopi[i]);
                int insert_detil_kopi = (int) db.insert(TABLE_MASTER_KOPI, null, values);
                Log.d("NAMA KOPI", String.valueOf(insert_detil_kopi));
            }
        }

    }

    public void inisialisasiDetilMenuKopi(){
        hasilCursor();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String[][] deskripsi_kopi = new String[][]{
                {"Biji kopi jantan PT. Kopindo Jaya dipasok dari Pupuan Tabanan. Kopi Jantan ini merupakan biji kopi pada umumnya. Kopi ini berbuntuk bulat dan tunggal yang hanya memiliki kapasitas produksi yang sedikit, berkisar 5-10% dari keseluruhan produksi kopi Arabika. Karakter kopi ini tidak terlalu kuat, tetapi memiliki profil yang seimbang dan bercitarasa herbal seperti kebanyakan kopi pada umumnya atau berkarakter daerah dimana kopi tersebut itu ditanam. Ukuran kopi ini lebih panjang, biasa disebut sebagai longberry", "Kopi ini memiliki kekentalan menengah ringan dengan keasaman lembut. Sedikit aroma kacang segar dan bunga kopi ini berwarna coklat gelap. Rasa kopi jantan saat diseduh sangat mantap. Selain ringan dilidah jugaa tidak membuat perut kembung, aromanya sangat kuat dan harum. Harga kopi jantan per tas (isi 6 bungkus) adalah Rp 1.260.000 (Satu Juta Dua Ratus Enam Puluh Ribu)."},
                {"Biji kopi betina PT. Kopindo Jaya dipasok dari Pupuan Tabanan. Kopi Betina memiliki belahan ditengah yang membagi dua biji kopinnya tersebut, biasa disebut dengan tanaman berbiji dua atau dikotil. Tanaman Kopi Jantan tidak tahan pada temperatur yang mendekati titik beku. Untuk berbunga dan menghasilkan buah, tanaman ini membutuhkan periode kering selama 4-5 bulan.","Rasa Kopi Betina agak asam dan aromanya jauh berbeda dengan Kopi Jantan. Citarasa kopi betina akan sangat ditentukan oleh cara pengolahan di pabrik. Teknik penyangraian biji kopi juga dapat merubah unsur kandungan kimiawi didalam biji kopi. Kopi Betina sangat laris karena harganya lebih murah dari Kopi Jantan. Harga satu tas Kopi Betina (isi 6 bungkus) adalah Rp.630.000 (Enam Ratuh Tiga Puluh Ribu Rupiah)."},
                {"Kopi Luwak berasal dari Lampung. Dinamakan luwak karena berasal dari proses nya yang sangat unik, yaitu kotoran luwak. Aroma rasa yang istimewa membuat kopi luwak menjadi begitu menarik untuk kita coba. Kopi Luwak memiliki karakter yang berbeda dari kopi lainnya. Biji Kopi Luwak berasal dari biji kopi terbaik yang matang dengan sempurna. Biji kopi tersebut kemudian diberikan pada luwak. Luwak menyeleksi kembali biji kopi pilihan tersebut dengan indera penciuman yang sangat tajam. Setelah itu luwak akan memakan biji kopi yang dipilihnya, kemudian melalui proses fermentasi satu malam didalam perut luwak. Biji kopi yang keluar bersama dengan kotoran luwak ini yang menjadi cikal bakal lahirnya Kopi Luwak.","Ketika kita melihat green bean Kopi Luwak dan green bean kopi biasa pasti akan terdapat perbedaaan. Letak perbedaaan green bean Kopi Luwak biasanya terlihat dari green bean size, uniformity, dan aroma green bean yang lebih wangi serta rasa yang lebih harum dari kopi biasa. Kopi Luwak memiliki sensasi bersih (clean) dimulut, terasa ringan ketika bersatu dirongga-rongga mulut dan tenggorokan, tanpa meninggalkan rasa sepat yang biasa terdapat didalam kopi biasa.","Kopi Luwak dijual per tas berisi 1 bks (100gr) Rp 1.780.000 (Satu juta tujuh ratus delapan puluh ribu), 2 bks (200gr) Rp 3.200.000 (Tiga juta dua ratus ribu), 5 bks (500gr) Rp 8.400.000 (delapan juta empat ratus ribu)."}
        };
        if (hasil_cursor == 1){
            for(int i = 0; i < deskripsi_kopi.length; i++){
                for(int j = 0; j <deskripsi_kopi[i].length; j++){
                    values.put(KEY_MASTER_KOPI_ID, i+1);
                    values.put(KEY_DESKRIPSI_KOPI, deskripsi_kopi[i][j]);
                    int insert_detil_kopi = (int) db.insert(TABLE_DETIL_KOPI, null, values);
                    Log.d("DETIL KOPI", String.valueOf(insert_detil_kopi));
                }
            }
        }
    }

    public void inisialisasiFotoKopi(){
        hasilCursor();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (hasil_cursor == 1){
            Uri img = Uri.parse("android.resource://com.example.pengenalankopi/" + R.drawable.kopijantan1);
            String stringImg1 = img.toString();
            Uri img2 = Uri.parse("android.resource://com.example.pengenalankopi/" + R.drawable.kopijantan2);
            String stringImg2 = img2.toString();
            Uri img3 = Uri.parse("android.resource://com.example.pengenalankopi/" + R.drawable.kopibetina1);
            String stringImg3 = img3.toString();
            Uri img4 = Uri.parse("android.resource://com.example.pengenalankopi/" + R.drawable.kopibetina2);
            String stringImg4 = img4.toString();
            Uri img5 = Uri.parse("android.resource://com.example.pengenalankopi/" + R.drawable.kopiluwak100);
            String stringImg5 = img5.toString();
            Uri img6 = Uri.parse("android.resource://com.example.pengenalankopi/" + R.drawable.kopiluwak200gr);
            String stringImg6 = img6.toString();
            Uri img7 = Uri.parse("android.resource://com.example.pengenalankopi/" + R.drawable.kopiluwak500);
            String stringImg7 = img7.toString();

            String[] imagePath = {stringImg1, stringImg2, stringImg3, stringImg4, stringImg5, stringImg6, stringImg7};
            Log.d("LENGTH BYTE: ", String.valueOf(imagePath.length));

            for (int i = 0; i< imagePath.length; i++){
                if (i<2){
                    values.put(KEY_MASTER_KOPI_ID, 1);
                    values.put(KEY_FOTO_KOPI, imagePath[i]);
                    int insert_detil_kopi = (int) db.insert(TABLE_FOTO_KOPI, null, values);
                } else if(i<4){
                    values.put(KEY_MASTER_KOPI_ID, 2);
                    values.put(KEY_FOTO_KOPI, imagePath[i]);
                    int insert_detil_kopi = (int) db.insert(TABLE_FOTO_KOPI, null, values);
                } else {
                    values.put(KEY_MASTER_KOPI_ID, 3);
                    values.put(KEY_FOTO_KOPI, imagePath[i]);
                    int insert_detil_kopi = (int) db.insert(TABLE_FOTO_KOPI, null, values);
                }
            }
        }
    }

    public void updateHasilCounter(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COUNTER, 2);
        db.update(TABLE_COUNTER, values, KEY_COUNTER+ "=?", new String[]{"1"});
    }
/*
* DI BAWAH INI SEMUA MERUPAKAN SEGALA FUNGSI UNTUK CREATE, READ, UPDATE, DELETE DATA KOPI
*
* */
    public Cursor ambilDeskripsiKopi(int where){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id_detil_kopi, deskripsi_kopi FROM tb_detil_kopi WHERE id_master_kopi = ?", new String[]{String.valueOf(where)} );
        return  cursor;
    }

    public Cursor ambilFotoKopi(int where){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT foto_kopi FROM tb_foto_kopi WHERE id_master_kopi = ?", new String[]{String.valueOf(where)});
        return cursor;
    }

    public void tambahDeskripsiKopi(KopiModel kopiModel){
        SQLiteDatabase db_get = this.getReadableDatabase();
        SQLiteDatabase db_insert = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String nama_kopi = kopiModel.nama_kopi;
        String deskripsi1 = kopiModel.deskripsi1;
        String deskripsi2 = kopiModel.deskripsi2;
        String deskripsi3 = kopiModel.deskripsi3;
        String deskripsi4 = kopiModel.deskripsi4;
        String[] deskripsiArray = {deskripsi1, deskripsi2,deskripsi3,deskripsi4};

        Cursor getData = db_get.rawQuery("SELECT deskripsi_kopi FROM tb_detil_kopi WHERE id_master_kopi = ?", new String[]{String.valueOf(nama_kopi)} );
        int hasil_insert = deskripsiArray.length;
        int hasil_get = getData.getCount();
        Log.d("HASIL QUERY = ", String.valueOf(hasil_get));
        Log.d("HASIL INSERT = ", String.valueOf(hasil_insert));
        int hasil_pengurangan = hasil_insert - hasil_get;
        Log.d("HASIL PENGURANGAN ", String.valueOf(hasil_pengurangan));

        if (hasil_pengurangan == 4){
            if (deskripsi1.isEmpty()){
                if (deskripsi2.isEmpty()){
                    if (deskripsi3.isEmpty()){
                        if (deskripsi4.isEmpty()){
                            Log.d("HASIL ARRAY" , "NDAK ADA");
                        } else {
                            hasilArray = new String[]{deskripsi4};
                        }
                    } else if(deskripsi4.isEmpty()){
                        hasilArray = new String[]{ deskripsi3};
                    } else {
                        hasilArray = new String[]{deskripsi3, deskripsi4};
                    }
                } else if(deskripsi4.isEmpty()){
                    hasilArray = new String[] {deskripsi2, deskripsi3};
                } else if(deskripsi3.isEmpty()){
                    hasilArray = new String[] {deskripsi2, deskripsi4};
                } else if( (deskripsi3.isEmpty()) && (deskripsi4.isEmpty())){
                    hasilArray = new String[]{deskripsi2};
                } else {
                    hasilArray = new String[]{deskripsi2, deskripsi3, deskripsi4};
                }
            } else if (deskripsi2.isEmpty()){
                if (deskripsi3.isEmpty()){
                    if (deskripsi4.isEmpty()){
                        if (deskripsi1.isEmpty()){
                            Log.d("HASIL ARRAY" , "NDAK ADA");
                        } else {
                            hasilArray = new String[]{deskripsi1};
                        }
                    } else if(deskripsi1.isEmpty()){
                        hasilArray = new String[]{deskripsi4};
                    } else {
                        hasilArray = new String[]{deskripsi1, deskripsi4};
                    }
                } else if(deskripsi1.isEmpty()){
                    hasilArray = new String[]{deskripsi3, deskripsi4};
                } else if(deskripsi4.isEmpty()){
                    hasilArray = new String[]{deskripsi1, deskripsi3};
                } else if( (deskripsi1.isEmpty()) && (deskripsi4.isEmpty()) ){
                    hasilArray = new String[]{deskripsi3};
                } else {
                    hasilArray = new String[]{deskripsi1, deskripsi3, deskripsi4};
                }
            } else if(deskripsi3.isEmpty()){
                if (deskripsi4.isEmpty()){
                    if (deskripsi1.isEmpty()){
                        if (deskripsi2.isEmpty()){
                            Log.d("HASIL ARRAY" , "NDAK ADA");
                        } else {
                            hasilArray = new String[]{deskripsi2};
                        }
                    } else if(deskripsi2.isEmpty()){
                        hasilArray = new String[]{deskripsi1};
                    } else {
                        hasilArray = new String[]{deskripsi1, deskripsi2};
                    }
                } else if(deskripsi2.isEmpty()) {
                    hasilArray = new String[]{deskripsi1, deskripsi4};
                } else if(deskripsi1.isEmpty()){
                    hasilArray = new String[]{deskripsi2, deskripsi4};
                } else if((deskripsi1.isEmpty())&&(deskripsi2.isEmpty())){
                    hasilArray = new String[]{deskripsi1};
                } else {
                    hasilArray = new String[]{deskripsi1, deskripsi2, deskripsi4};
                }
            } else if(deskripsi4.isEmpty()){
                if (deskripsi1.isEmpty()){
                    if (deskripsi2.isEmpty()){
                        if (deskripsi3.isEmpty()){
                            Log.d("HASIL ARRAY" , "NDAK ADA");
                        } else {
                            hasilArray = new String[]{deskripsi3};
                        }
                    } else if(deskripsi3.isEmpty()){
                        hasilArray = new String[]{deskripsi2};
                    } else {
                        hasilArray = new String[]{deskripsi2, deskripsi3};
                    }
                } else if(deskripsi3.isEmpty()){
                    hasilArray = new String[]{deskripsi1, deskripsi2};
                } else if(deskripsi2.isEmpty()){
                    hasilArray = new String[]{deskripsi1, deskripsi3};
                } else if((deskripsi2.isEmpty()) && (deskripsi3.isEmpty())){
                    hasilArray = new String[]{deskripsi1};
                } else {
                    hasilArray = new String[]{deskripsi1, deskripsi2, deskripsi3};
                }
            } else {
                hasilArray = new String[]{deskripsi1, deskripsi2, deskripsi3, deskripsi4};
            }
        } else if (hasil_pengurangan == 2){
            if (deskripsi3.isEmpty()){
                if (deskripsi4.isEmpty()){
                    Log.d("HASIL ARRAY" , "NDAK ADA");
                } else {
                    hasilArray = new String[]{deskripsi4};
                }
            } else if(deskripsi4.isEmpty()) {
                hasilArray = new String[]{deskripsi3};
            } else {
                hasilArray = new String[]{deskripsi3, deskripsi4};
            }
        } else {
            hasilArray = new String[]{deskripsi4};
        }

        Log.d("HASIL ARRAY ", String.valueOf(hasilArray.length));

        for (int i = 0; i< hasilArray.length; i++){
            values.put(KEY_MASTER_KOPI_ID, nama_kopi);
            values.put(KEY_DESKRIPSI_KOPI, hasilArray[i]);
            long insert_kopi = db_insert.insert(TABLE_DETIL_KOPI, null, values);
        }
    }

    public void editDeskripsiKopi(KopiModel kopiModel){
        SQLiteDatabase db_update = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String id_detil_kopi = kopiModel.id_deskripsi_kopi;
        String deskripsi1 = kopiModel.deskripsi1;
        String deskripsi2 = kopiModel.deskripsi2;
        String deskripsi3 = kopiModel.deskripsi3;
        String deskripsi4 = kopiModel.deskripsi4;
        if (deskripsi1 == null){
            if (deskripsi2 == null){
                if (deskripsi3 == null){
                    if (deskripsi4 == null){
                        Log.d("STRING ", "SEMUA KOSONG");
                    } else {
                        values.put(KEY_DESKRIPSI_KOPI, deskripsi4);
                        db_update.update(TABLE_DETIL_KOPI, values, KEY_DETIL_KOPI_ID + "= ?", new String[]{id_detil_kopi});
                        Log.d("STRING ", "4 GAK KOSONG");
                    }
                } else if(deskripsi4 == null){
                    values.put(KEY_DESKRIPSI_KOPI, deskripsi3);
                    db_update.update(TABLE_DETIL_KOPI, values, KEY_DETIL_KOPI_ID + "= ?", new String[]{id_detil_kopi});
                    Log.d("STRING ", "CUMA 3 yang ndak kosong");
                }
            } else if((deskripsi3 == null) && (deskripsi4 == null)){
                values.put(KEY_DESKRIPSI_KOPI, deskripsi2);
                db_update.update(TABLE_DETIL_KOPI, values, KEY_DETIL_KOPI_ID + "= ?", new String[]{id_detil_kopi});
                Log.d("STRING ", "CUMA 2 GAK KOSONG");
            }
        } else if(deskripsi2 == null) {
            if (deskripsi3 == null){
                if (deskripsi4 == null){
                    if (deskripsi1 == null){
                        Log.d("STRING ", "SEMUA KOSONG");
                    } else {
                        values.put(KEY_DESKRIPSI_KOPI, deskripsi1);
                        db_update.update(TABLE_DETIL_KOPI, values, KEY_DETIL_KOPI_ID + "= ?", new String[]{id_detil_kopi});
                        Log.d("STRING ", "1 GAK KOSONG");
                    }
                } else if(deskripsi1 == null){
                    values.put(KEY_DESKRIPSI_KOPI, deskripsi4);
                    db_update.update(TABLE_DETIL_KOPI, values, KEY_DETIL_KOPI_ID + "= ?", new String[]{id_detil_kopi});
                    Log.d("STRING ", "CUMA 4 yang ndak kosong");
                }
            } else if((deskripsi4 == null) && (deskripsi1 == null)){
                values.put(KEY_DESKRIPSI_KOPI, deskripsi3);
                db_update.update(TABLE_DETIL_KOPI, values, KEY_DETIL_KOPI_ID + "= ?", new String[]{id_detil_kopi});
                Log.d("STRING ", "CUMA 3 GAK KOSONG");
            }
        } else if(deskripsi3 == null){
            if (deskripsi4 == null){
                if (deskripsi1 == null){
                    if (deskripsi2 == null){
                        Log.d("STRING ", "SEMUA KOSONG");
                    } else {
                        values.put(KEY_DESKRIPSI_KOPI, deskripsi2);
                        db_update.update(TABLE_DETIL_KOPI, values, KEY_DETIL_KOPI_ID + "= ?", new String[]{id_detil_kopi});
                        Log.d("STRING ", "2 GAK KOSONG");
                    }
                } else if(deskripsi2 == null){
                    values.put(KEY_DESKRIPSI_KOPI, deskripsi1);
                    db_update.update(TABLE_DETIL_KOPI, values, KEY_DETIL_KOPI_ID + "= ?", new String[]{id_detil_kopi});
                    Log.d("STRING ", "CUMA 1 yang ndak kosong");
                }
            } else if((deskripsi1 == null) && (deskripsi2 == null)){
                values.put(KEY_DESKRIPSI_KOPI, deskripsi4);
                db_update.update(TABLE_DETIL_KOPI, values, KEY_DETIL_KOPI_ID + "= ?", new String[]{id_detil_kopi});
                Log.d("STRING ", "CUMA 4 GAK KOSONG");
            }
        } else if(deskripsi4 == null){
            if (deskripsi1 == null){
                if (deskripsi2 == null){
                    if (deskripsi3 == null){
                        Log.d("STRING ", "SEMUA KOSONG");
                    } else {
                        values.put(KEY_DESKRIPSI_KOPI, deskripsi3);
                        db_update.update(TABLE_DETIL_KOPI, values, KEY_DETIL_KOPI_ID + "= ?", new String[]{id_detil_kopi});
                        Log.d("STRING ", "3 GAK KOSONG");
                    }
                } else if(deskripsi3 == null){
                    values.put(KEY_DESKRIPSI_KOPI, deskripsi2);
                    db_update.update(TABLE_DETIL_KOPI, values, KEY_DETIL_KOPI_ID + "= ?", new String[]{id_detil_kopi});
                    Log.d("STRING ", "CUMA 2 yang ndak kosong");
                }
            } else if((deskripsi2 == null) && (deskripsi3 == null)){
                values.put(KEY_DESKRIPSI_KOPI, deskripsi1);
                db_update.update(TABLE_DETIL_KOPI, values, KEY_DETIL_KOPI_ID + "= ?", new String[]{id_detil_kopi});
                Log.d("STRING ", "CUMA 1 GAK KOSONG");
            }
        }
    }

    public void hapusDeskripsiKopi(KopiModel kopiModel){
        String id_detil_kopi = kopiModel.id_deskripsi_kopi;
        SQLiteDatabase db_delete = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_DETIL_KOPI_ID, id_detil_kopi);
        db_delete.delete(TABLE_DETIL_KOPI, KEY_DETIL_KOPI_ID + "= ?", new String[]{id_detil_kopi});
    }

}
