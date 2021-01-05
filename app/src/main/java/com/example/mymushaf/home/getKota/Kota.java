package com.example.mymushaf.home.getKota;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Kota {

    @SerializedName("kota")
    private List<Kota_Detail> kota_details;

    public List<Kota_Detail> getKota_details() {
        return kota_details;
    }

    public void setKota_details(List<Kota_Detail> kota_details) {
        this.kota_details = kota_details;
    }
}
