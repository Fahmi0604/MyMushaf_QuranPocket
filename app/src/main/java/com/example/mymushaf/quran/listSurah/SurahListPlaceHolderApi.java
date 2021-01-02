package com.example.mymushaf.quran.listSurah;

import com.example.mymushaf.quran.listSurah.models.SurahList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SurahListPlaceHolderApi {

    @GET("quran.json")
    Call<List<SurahList>> getSurahList();

}
