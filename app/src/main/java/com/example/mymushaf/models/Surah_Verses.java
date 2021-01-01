package com.example.mymushaf.models;

import com.google.gson.annotations.SerializedName;

public class Surah_Verses {

    @SerializedName("number")
    private int number;

    @SerializedName("text")
    private String ayat;

    @SerializedName("translation_id")
    private String translation_id;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAyat() {
        return ayat;
    }

    public void setAyat(String ayat) {
        this.ayat = ayat;
    }

    public String getTranslation_id() {
        return translation_id;
    }

    public void setTranslation_id(String translation_id) {
        this.translation_id = translation_id;
    }
}
