package com.example.mymushaf.home.getKota;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface KotaPlaceHolderApi {

    @GET
    Call<Kota> getKota(@Url String url);

}
