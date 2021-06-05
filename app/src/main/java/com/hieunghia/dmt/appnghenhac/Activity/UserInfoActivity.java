package com.hieunghia.dmt.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hieunghia.dmt.appnghenhac.Model.User;
import com.hieunghia.dmt.appnghenhac.R;

import java.util.ArrayList;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class UserInfoActivity extends AppCompatActivity {

    TextView txtUserName,txtPassWord;
    CircularProgressButton crpbLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        Init();
    }

    private void Init() {

        txtUserName = findViewById(R.id.textviewUserName);
        txtPassWord = findViewById(R.id.textviewPassword);
        crpbLogout = findViewById(R.id.cirLogoutButton);

        Intent intent = getIntent();
        ArrayList<User> UserInfo = intent.getParcelableArrayListExtra("UserInfo");

        txtUserName.setText(UserInfo.get(0).getTaiKhoan());
        txtPassWord.setText(UserInfo.get(0).getMatKhau());
    }
}