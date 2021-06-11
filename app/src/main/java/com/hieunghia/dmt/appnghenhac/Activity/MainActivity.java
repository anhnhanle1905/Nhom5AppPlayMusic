package com.hieunghia.dmt.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.hieunghia.dmt.appnghenhac.Adapter.MainViewPaperAdapter;
import com.hieunghia.dmt.appnghenhac.Model.User;
import com.hieunghia.dmt.appnghenhac.R;
import com.hieunghia.dmt.appnghenhac.fragment.Fragment_Ho_So;
import com.hieunghia.dmt.appnghenhac.fragment.Fragment_Tim_Kiem;
import com.hieunghia.dmt.appnghenhac.fragment.Fragment_Trang_Chu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    String getName,getPhone,getEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();

        getName = getIntent().getStringExtra("UserName");
        getEmail = getIntent().getStringExtra("UserEmail");
        getPhone = getIntent().getStringExtra("UserPhone");

        init();

    }


    private void init() {
        MainViewPaperAdapter mainViewPaperAdapter = new MainViewPaperAdapter(getSupportFragmentManager());
        mainViewPaperAdapter.addFragment(new Fragment_Trang_Chu(), "Trang chu");
        mainViewPaperAdapter.addFragment(new Fragment_Tim_Kiem(), "Tim Kiem");
        mainViewPaperAdapter.addFragment(new Fragment_Ho_So(), "Ho So");
        viewPager.setAdapter(mainViewPaperAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconhoso);
    }

    private void anhxa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPaper);
    }

    public String getGetName() {
        return getName;
    }
    public String getGetPhone() {
        return getPhone;
    }
    public String getGetEmail() {
        return getEmail;
    }

}