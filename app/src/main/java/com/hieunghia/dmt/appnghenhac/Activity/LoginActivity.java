package com.hieunghia.dmt.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hieunghia.dmt.appnghenhac.Model.User;
import com.hieunghia.dmt.appnghenhac.R;
import com.hieunghia.dmt.appnghenhac.Service.APIService;
import com.hieunghia.dmt.appnghenhac.Service.DataService;
import com.hieunghia.dmt.appnghenhac.fragment.Fragment_Trang_Chu;

import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName,edtPassWord;
    CircularProgressButton crpLogin;
    String name,passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        Init();
        crpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edtUserName.getText().toString();
                passWord = edtPassWord.getText().toString();

                if (name.length() > 0 && passWord.length() > 0) {
                    DataService dataService = APIService.GetUserAccount();
                    Call<List<User>> callback = dataService.GetLoginData(name,passWord);
                    callback.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            ArrayList<User> arrUser = (ArrayList<User>) response.body();
                            if (arrUser.size() > 0)
                            {
                                Intent intent = new Intent(LoginActivity.this, Fragment_Trang_Chu.class);
                                intent.putExtra("UserInfo",arrUser);
                                startActivity(intent);
                            }
                        }
                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Tài Khoản này không tồn tại!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void Init() {
        edtUserName = findViewById(R.id.edtTextName);
        edtPassWord = findViewById(R.id.edtTextPassword);
        crpLogin = findViewById(R.id.cirLoginButton);
    }

    public void onLoginClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }
}