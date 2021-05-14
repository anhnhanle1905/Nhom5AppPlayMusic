package com.hieunghia.dmt.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

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
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // kiểm tra tín hiệu mạng
        StrictMode.setThreadPolicy(policy);
        GetDataInTent();
        init();
        evenClick();


    }

    private void evenClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1) != null) {
                    if (mangbaihat.size() > 0){
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(0).getHinhBaiHat());
                        // trường hợp khi run lên mà load bài hát quá lâu ko lấy đc bài hát thì hàm này sẽ đợi đến khi load đc
                        handler.removeCallbacks(this);
                    }else  {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.iconplay);
                }else {
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });
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
        adapternhac.AddFragment(fragment_play_danh_sach_cac_bai_hat);
        adapternhac.AddFragment(fragment_dia_nhac);
        viewPagerplaynhac.setAdapter(adapternhac);
        fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(1);
        if (mangbaihat.size() > 0)
        {
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenBaiHat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkBaiHat());
            imgPlay.setImageResource(R.drawable.iconpause);
        }
    }

    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });
            mediaPlayer.setDataSource(baihat);
            mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekbartime.setMax(mediaPlayer.getDuration());

    }
}