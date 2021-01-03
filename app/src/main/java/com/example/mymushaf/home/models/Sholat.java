package com.example.mymushaf.home.models;

import com.google.gson.annotations.SerializedName;

public class Sholat {

    @SerializedName("jadwal")
    private Sholat_Jadwal sholatJadwal;

    public Sholat_Jadwal getSholatJadwal() {
        return sholatJadwal;
    }

    public void setSholatJadwal(Sholat_Jadwal sholatJadwal) {
        this.sholatJadwal = sholatJadwal;
    }
}
