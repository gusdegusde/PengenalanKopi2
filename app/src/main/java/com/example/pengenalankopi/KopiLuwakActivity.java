package com.example.pengenalankopi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

public class KopiLuwakActivity extends AppCompatActivity implements View.OnClickListener {

    SliderLayout sliderLayoutLuwak;
    private ImageView back_utama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kopi_luwak);

        back_utama = findViewById(R.id.back_utama);
        back_utama.setOnClickListener(this);

        sliderLayoutLuwak = findViewById(R.id.imageSlider);
        sliderLayoutLuwak.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayoutLuwak.setScrollTimeInSec(1);
        buatImageSliderLuwak();
    }

    private void buatImageSliderLuwak() {
        for (int i =0; i <=2; i++){
            DefaultSliderView sliderView = new DefaultSliderView(this);

            switch (i){
                case 0:
                    sliderView.setImageDrawable(R.drawable.foto_kopi_jantan_1);
                    sliderView.setDescription("Foto Kopi Luwak Pertama");
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.foto_kopi_jantan_2);
                    sliderView.setDescription("Foto Kopi Luwak Kedua");
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.foto_kopi_jantan_3);
                    sliderView.setDescription("Foto Kopi Luwak Ketiga");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(KopiLuwakActivity.this, "Foto Kopi Luwak ke- " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            sliderLayoutLuwak.addSliderView(sliderView);
        }
    }

    @Override
    public void onClick(View v){
        Intent i;
        switch (v.getId()){
            case R.id.back_utama:
                i = new Intent(this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            default: break;
        }
    }
}
