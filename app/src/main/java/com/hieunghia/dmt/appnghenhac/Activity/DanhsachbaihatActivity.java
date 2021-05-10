package com.hieunghia.dmt.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hieunghia.dmt.appnghenhac.Model.QuangCao;
import com.hieunghia.dmt.appnghenhac.R;

public class DanhsachbaihatActivity extends AppCompatActivity {

    QuangCao quangCao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        DataIntent();
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent != null)
        {
            if (intent.hasExtra("banner")){
                quangCao = (QuangCao) intent.getSerializableExtra("banner");
                Toast.makeText(this, quangCao.getTenBaiHat(),Toast.LENGTH_LONG).show();
            }
        }
    }
}