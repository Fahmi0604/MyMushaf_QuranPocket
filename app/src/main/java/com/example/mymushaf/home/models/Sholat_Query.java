package com.example.mymushaf.home.models;

import com.google.gson.annotations.SerializedName;

public class Sholat_Query {

    @SerializedName("tanggal")
    private String tanggal;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
