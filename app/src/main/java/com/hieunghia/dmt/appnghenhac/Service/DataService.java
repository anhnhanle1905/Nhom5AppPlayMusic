package com.hieunghia.dmt.appnghenhac.Service;

import android.provider.MediaStore;

import com.hieunghia.dmt.appnghenhac.Model.Album;
import com.hieunghia.dmt.appnghenhac.Model.PlayList;
import com.hieunghia.dmt.appnghenhac.Model.QuangCao;
import com.hieunghia.dmt.appnghenhac.Model.TheLoaiHomNay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlistforcurent.php")
    Call<List<PlayList>> GetPlayListCurrentDay();

    @GET("chudevatheloai.php")
    Call<TheLoaiHomNay> GetCateGoryMusic();

    @GET("album.php")
    Call<List<Album>> GetAlbumHot();
}
