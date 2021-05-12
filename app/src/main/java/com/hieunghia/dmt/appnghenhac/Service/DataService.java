package com.hieunghia.dmt.appnghenhac.Service;

import android.provider.MediaStore;

import com.hieunghia.dmt.appnghenhac.Model.Album;
import com.hieunghia.dmt.appnghenhac.Model.BaiHat;
import com.hieunghia.dmt.appnghenhac.Model.PlayList;
import com.hieunghia.dmt.appnghenhac.Model.QuangCao;
import com.hieunghia.dmt.appnghenhac.Model.TheLoaiHomNay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlistforcurent.php")
    Call<List<PlayList>> GetPlayListCurrentDay();

    @GET("chudevatheloai.php")
    Call<TheLoaiHomNay> GetCateGoryMusic();

    @GET("album.php")
    Call<List<Album>> GetAlbumHot();

    @GET("baihatduocyeuthich.php")
    Call<List<BaiHat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")                                                                  // vi` ở phần service đặt tương tác vs service là POST nên client phải trùng vs phương thức.
    Call<List<BaiHat>> GetDanhSachBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);   // gửi dữ liệu lên và trả dữ liệu về.
}
