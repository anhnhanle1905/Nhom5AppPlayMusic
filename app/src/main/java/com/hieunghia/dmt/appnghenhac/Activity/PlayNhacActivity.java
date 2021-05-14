package com.hieunghia.dmt.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hieunghia.dmt.appnghenhac.Adapter.ViewPagerPlaylistnhac;
import com.hieunghia.dmt.appnghenhac.Model.BaiHat;
import com.hieunghia.dmt.appnghenhac.R;
import com.hieunghia.dmt.appnghenhac.fragment.Fragment_Dia_Nhac;
import com.hieunghia.dmt.appnghenhac.fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;

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
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragment_play_danh_sach_cac_bai_hat;
    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylistnhac adapternhac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        init();
        GetDataInTent();

    }

    private void GetDataInTent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null)
        {
            if (intent.hasExtra("cakhuc")){
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baiHat);
            }
            if (intent.hasExtra("cacbaihat"))
            {
                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baiHatArrayList;
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
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danh_sach_cac_bai_hat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        adapternhac = new ViewPagerPlaylistnhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_dia_nhac);
        adapternhac.AddFragment(fragment_play_danh_sach_cac_bai_hat);
        viewPagerplaynhac.setAdapter(adapternhac);
    }
}