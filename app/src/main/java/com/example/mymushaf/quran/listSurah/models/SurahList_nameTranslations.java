package com.example.mymushaf.quran.listSurah.models;

import com.google.gson.annotations.SerializedName;

public class SurahList_nameTranslations {

    @SerializedName("ar")
    private String arab;

    @SerializedName("en")
    private String english;

    @SerializedName("id")
    private String indonesia;

    public String getArab() {
        return arab;
    }

    public void setArab(String arab) {
        this.arab = arab;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getIndonesia() {
        return indonesia;
    }

    public void setIndonesia(String indonesia) {
        this.indonesia = indonesia;
    }
}
