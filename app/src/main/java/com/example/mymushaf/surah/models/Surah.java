package com.example.mymushaf.surah.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Surah {

    @SerializedName("name")
    private String name;

    @SerializedName("number_of_ayah")
    private int number_of_ayah;

    @SerializedName("number_of_surah")
    private int number_of_surah;

    @SerializedName("recitations")
    private List<Surah_recitations> surah_recitations;

    @SerializedName("name_translations")
    private Surah_nameTranslation surah_nameTranslation;

    @SerializedName("place")
    private String place;

    @SerializedName("type")
    private String type;

    @SerializedName("verses")
    private List<Surah_Verses> surah_verses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Surah_nameTranslation getSurah_nameTranslation() {
        return surah_nameTranslation;
    }

    public void setSurah_nameTranslation(Surah_nameTranslation surah_nameTranslation) {
        this.surah_nameTranslation = surah_nameTranslation;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Surah_Verses> getSurah_verses() {
        return surah_verses;
    }

    public void setSurah_verses(List<Surah_Verses> surah_verses) {
        this.surah_verses = surah_verses;
    }

    public List<Surah_recitations> getSurah_recitations() {
        return surah_recitations;
    }

    public void setSurah_recitations(List<Surah_recitations> surah_recitations) {
        this.surah_recitations = surah_recitations;
    }
}
