package com.example.mymushaf.home;

import com.example.mymushaf.home.models.Sholat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SholatPlaceHolderApi {

    @GET
    Call<Sholat> getSholat(@Url String url);

}

