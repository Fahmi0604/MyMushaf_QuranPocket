package com.example.mymushaf.home.models;

import com.google.gson.annotations.SerializedName;

public class Sholat_Jadwal_Data {

    @SerializedName("ashar")
    private String ashar;

    @SerializedName("dhuha")
    private String dhuha;

    @SerializedName("dzuhur")
    private String dzuhur;

    @SerializedName("isya")
    private String isya;

    @SerializedName("maghrib")
    private String maghrib;

    @SerializedName("subuh")
    private String subuh;

    public String getAshar() {
        return ashar;
    }

    public void setAshar(String ashar) {
        this.ashar = ashar;
    }

    public String getDhuha() {
        return dhuha;
    }

    public void setDhuha(String dhuha) {
        this.dhuha = dhuha;
    }

    public String getDzuhur() {
        return dzuhur;
    }

    public void setDzuhur(String dzuhur) {
        this.dzuhur = dzuhur;
    }

    public String getIsya() {
        return isya;
    }

    public void setIsya(String isya) {
        this.isya = isya;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public String getSubuh() {
        return subuh;
    }

    public void setSubuh(String subuh) {
        this.subuh = subuh;
    }
}
