package com.hieunghia.dmt.appnghenhac.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hieunghia.dmt.appnghenhac.Adapter.BaihathotAdapter;
import com.hieunghia.dmt.appnghenhac.Model.BaiHat;
import com.hieunghia.dmt.appnghenhac.R;
import com.hieunghia.dmt.appnghenhac.Service.APIService;
import com.hieunghia.dmt.appnghenhac.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Bai_Hat_Hot extends Fragment {
    View view;
    RecyclerView recyclerViewbaihathot;
    BaihathotAdapter baihathotAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bai_hat_thich_nhat, container,false);
        recyclerViewbaihathot = view.findViewById(R.id.recyclerviewbaihathot);
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetBaiHatHot();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatsArraylist = (ArrayList<BaiHat>) response.body();
                baihathotAdapter = new BaihathotAdapter(getActivity(),baiHatsArraylist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewbaihathot.setLayoutManager(linearLayoutManager);
                recyclerViewbaihathot.setAdapter(baihathotAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });   // nhận dữ liệu sau khi tương tác vs service

    }
}
