package com.example.mymushaf.home.getKota;

import com.google.gson.annotations.SerializedName;

public class Kota_Detail {

    @SerializedName("id")
    private String id;

    @SerializedName("nama")
    private String nama;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
