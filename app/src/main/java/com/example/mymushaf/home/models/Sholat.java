package com.example.mymushaf.home.models;

import com.google.gson.annotations.SerializedName;

public class Sholat {

    @SerializedName("jadwal")
    private Sholat_Jadwal sholatJadwal;

    @SerializedName("query")
    private Sholat_Query sholat_query;

    public Sholat_Jadwal getSholatJadwal() {
        return sholatJadwal;
    }

    public void setSholatJadwal(Sholat_Jadwal sholatJadwal) {
        this.sholatJadwal = sholatJadwal;
    }

    public Sholat_Query getSholat_query() {
        return sholat_query;
    }

    public void setSholat_query(Sholat_Query sholat_query) {
        this.sholat_query = sholat_query;
    }
}
