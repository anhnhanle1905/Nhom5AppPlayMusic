package com.hieunghia.dmt.appnghenhac.fragment;

import android.os.Bundle;
import android.text.NoCopySpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hieunghia.dmt.appnghenhac.Model.TheLoaiHomNay;
import com.hieunghia.dmt.appnghenhac.R;
import com.hieunghia.dmt.appnghenhac.Service.APIService;
import com.hieunghia.dmt.appnghenhac.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Chu_De_The_Loai extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chu_de_the_loai,container,false);
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<TheLoaiHomNay> callback = dataService.GetCateGoryMusic();
        callback.enqueue(new Callback<TheLoaiHomNay>() {
            @Override
            public void onResponse(Call<TheLoaiHomNay> call, Response<TheLoaiHomNay> response) {
                TheLoaiHomNay theLoaiHomNay = response.body();
                Log.d("BBB",theLoaiHomNay.getTheLoai().get(1).getTenTheLoai());
            }

            @Override
            public void onFailure(Call<TheLoaiHomNay> call, Throwable t) {

            }
        });
    }
}
