package com.hieunghia.dmt.appnghenhac.Service;

import android.provider.MediaStore;

import com.hieunghia.dmt.appnghenhac.Model.PlayList;
import com.hieunghia.dmt.appnghenhac.Model.QuangCao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlistforcurent.php")
    Call<List<PlayList>> GetPlayListCurrentDay();
}
