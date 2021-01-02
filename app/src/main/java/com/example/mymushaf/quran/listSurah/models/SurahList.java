package com.example.mymushaf.quran.listSurah.models;

import com.google.gson.annotations.SerializedName;

public class SurahList {

    @SerializedName("name")
    private String name;

    @SerializedName("name_translations")
    private SurahList_nameTranslations nameTranslations;

    @SerializedName("number_of_ayah")
    private int number_of_ayah;

    @SerializedName("number_of_surah")
    private int number_of_surah;

    @SerializedName("place")
    private String place;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SurahList_nameTranslations getNameTranslations() {
        return nameTranslations;
    }

    public void setNameTranslations(SurahList_nameTranslations nameTranslations) {
        this.nameTranslations = nameTranslations;
    }

    public int getNumber_of_ayah() {
        return number_of_ayah;
    }

    public void setNumber_of_ayah(int number_of_ayah) {
        this.number_of_ayah = number_of_ayah;
    }

    public int getNumber_of_surah() {
        return number_of_surah;
    }

    public void setNumber_of_surah(int number_of_surah) {
        this.number_of_surah = number_of_surah;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
