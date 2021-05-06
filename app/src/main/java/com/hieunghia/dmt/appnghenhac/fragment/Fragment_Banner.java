package com.hieunghia.dmt.appnghenhac.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hieunghia.dmt.appnghenhac.Model.QuangCao;
import com.hieunghia.dmt.appnghenhac.R;
import com.hieunghia.dmt.appnghenhac.Service.APIService;
import com.hieunghia.dmt.appnghenhac.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService(); //

        Call<List<QuangCao>> callback = dataService.GetDataBanner();
        callback.enqueue(new Callback<List<QuangCao>>() { // sự kiện lắng nghe
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) { //lắng nghe có kết quả trả về
                ArrayList<QuangCao> banners = (ArrayList<QuangCao>) response.body();
                Log.d("BBB",banners.get(0).getTenBaiHat());
            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) { // thất bại

            }
        });
    }
}
