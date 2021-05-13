package com.hieunghia.dmt.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hieunghia.dmt.appnghenhac.Model.BaiHat;
import com.hieunghia.dmt.appnghenhac.R;

import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {

    //TextView txtCasi, txttenbaihat, txtthoigianbatdau, txtthoigianketthuc;
    //ImageView imgbtnback;
    //ImageButton imgPlay, imgPre, imgNext, imgrepeat;
    //SeekBar seekbartime;

    Toolbar toolbarplaynhac;
    TextView txtTimeSong, txtTotaltimesong;
    //ImageView imgbtnback;
    ImageButton imgPlay, imgPre, imgNext, imgrepeat, imgrand;
    SeekBar seekbartime;
    ViewPager viewPagerplaynhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        init();
        Intent intent = getIntent();
        if (intent.hasExtra("cakhuc")){
            BaiHat baiHat = intent.getParcelableExtra("cakhuc");
            Toast.makeText(this, baiHat.getTenBaiHat(), Toast.LENGTH_SHORT).show();
        }
        if (intent.hasExtra("cacbaihat"))
        {
            ArrayList<BaiHat> mangbaihat =intent.getParcelableArrayListExtra("cacbaihat");
            for (int i = 0; i < mangbaihat.size(); i++)
            {
                Log.d("BBB",mangbaihat.get(i).getTenBaiHat());
            }
        }
    }

    private void init() {
        toolbarplaynhac = findViewById(R.id.toobarplaynhac);
        txtTimeSong = findViewById(R.id.textviewtimesong);
        txtTotaltimesong = findViewById(R.id.textviewtotaltimesong);
        seekbartime = findViewById(R.id.seekbarsong);
        imgPlay = findViewById(R.id.imagebuttonplay);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        imgNext = findViewById(R.id.imagebuttonnext);
        imgrand = findViewById(R.id.imagebuttonsuffle);
        imgPre = findViewById(R.id.imagebuttonpre);
        viewPagerplaynhac = findViewById(R.id.viewpaperplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}