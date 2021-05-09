package com.hieunghia.dmt.appnghenhac.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hieunghia.dmt.appnghenhac.Model.PlayList;
import com.hieunghia.dmt.appnghenhac.R;
import com.hieunghia.dmt.appnghenhac.Service.APIRetrofitClient;
import com.hieunghia.dmt.appnghenhac.Service.APIService;
import com.hieunghia.dmt.appnghenhac.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPlaylist extends Fragment {

    View view;

    @Nullable
    @Override // onCreateView de gan layout vao` fragment nay`
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentplaylist, container, false);
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();      // doc du lieu, gui len service nhung cau hinh` de cho no tuong tac vs du lieu nhu the nao
        Call<List<PlayList>> callback = dataService.GetPlayListCurrentDay(); // gui phuong thuc getplaylistcurrentday();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) { // su kien lang nghe
                ArrayList<PlayList> mangplaylist = (ArrayList<PlayList>) response.body();
                Log.d("BBB",mangplaylist.get(0).getTen());
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }
}
