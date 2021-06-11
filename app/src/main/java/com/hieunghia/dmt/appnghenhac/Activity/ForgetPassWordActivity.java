package com.hieunghia.dmt.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hieunghia.dmt.appnghenhac.R;
import com.hieunghia.dmt.appnghenhac.Service.APIService;
import com.hieunghia.dmt.appnghenhac.Service.DataService;

import java.util.HashMap;
import java.util.Map;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;

public class ForgetPassWordActivity extends AppCompatActivity {

    EditText edtResetPassWord;
    CircularProgressButton crpbReset;
    String email;
    StringRequest stringRequest;
    String URL = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass_word);
        Init();
        crpbReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtResetPassWord.getText().toString();
//                if (email.length() > 0){
//                    DataService dataService = APIService.GetUserAccount();
//                    Call<String> callback = dataService.GetResetPassWord(email);
//                    callback.enqueue(new Callback<String>() {
//                        @Override
//                        public void onResponse(Call<String> call, Response<String> response) {
//                            String result = response.body();
//                            if (result.equals("Success")){
//                                Toast.makeText(ForgetPassWordActivity.this, "Đã gửi Email thành công hãy kiểm tra hộp thư của bạn ", Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                Toast.makeText(ForgetPassWordActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<String> call, Throwable t) {
//
//                        }
//                    });
//
//
//                }

                stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")){
                            Toast.makeText(ForgetPassWordActivity.this, "Đã gửi Email thành công hãy kiểm tra hộp thư của bạn ", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ForgetPassWordActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ForgetPassWordActivity.this, "Kiểm tra kết nối! ", Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("Email",email);
                        return super.getParams();
                    }
                };
            }
        });
    }

    private void Init() {
        edtResetPassWord = findViewById(R.id.edittextResetPassWord);
        crpbReset = findViewById(R.id.cirResetButton);
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}