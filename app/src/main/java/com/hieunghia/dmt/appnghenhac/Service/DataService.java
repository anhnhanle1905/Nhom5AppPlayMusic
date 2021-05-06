package com.hieunghia.dmt.appnghenhac.Service;

import com.hieunghia.dmt.appnghenhac.Model.QuangCao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    
}
