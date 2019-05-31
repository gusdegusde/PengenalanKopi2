package com.example.pengenalankopi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView kopi_jantan,kopi_betina,kopi_luwak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kopi_jantan = findViewById(R.id.kopi_jantan);
        kopi_betina = findViewById(R.id.kopi_betina);
        kopi_luwak = findViewById(R.id.kopi_luwak);

        kopi_jantan.setOnClickListener(this);
        kopi_betina.setOnClickListener(this);
        kopi_luwak.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.kopi_jantan:
                i = new Intent (this, KopiJantanActivity.class);
                startActivity(i);
                break;
            case R.id.kopi_betina:
                i = new Intent(this, KopiBetinaActivity.class);
                startActivity(i);
                break;
            case R.id.kopi_luwak:
                i = new Intent(this, KopiLuwakActivity.class);
                startActivity(i);
                break;
            default: break;
        }

    }
}
