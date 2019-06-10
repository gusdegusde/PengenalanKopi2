package com.example.pengenalankopi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class TentangSayaActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back_to_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_saya);

        back_to_menu = findViewById(R.id.back_to_kopi);
        back_to_menu.setOnClickListener(this);

    }

    private void linkKeActivity(){
        String previousActivity = getIntent().getStringExtra("previousActivity");
        Log.d("PREVIOUS ACTIVITY", previousActivity);
        try {
            Class fromClass = Class.forName(previousActivity);
            Intent i = new Intent(TentangSayaActivity.this, fromClass);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back_to_kopi:
                linkKeActivity();
                break;
            default: break;
        }
    }
}
