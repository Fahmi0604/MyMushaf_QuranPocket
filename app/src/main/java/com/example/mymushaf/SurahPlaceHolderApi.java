package com.example.mymushaf;

import com.example.mymushaf.models.Surah;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SurahPlaceHolderApi {

    @GET
    Call<Surah> getSurah(@Url String url);

}
