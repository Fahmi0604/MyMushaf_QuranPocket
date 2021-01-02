package com.example.mymushaf.surah;

import com.example.mymushaf.surah.models.Surah;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SurahPlaceHolderApi {

    @GET
    Call<Surah> getSurah(@Url String url);

}
