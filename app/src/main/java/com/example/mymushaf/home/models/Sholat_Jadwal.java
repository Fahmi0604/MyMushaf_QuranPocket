package com.example.mymushaf.home.models;

import com.google.gson.annotations.SerializedName;

public class Sholat_Jadwal {

    @SerializedName("data")
    private Sholat_Jadwal_Data sholatJadwalData;

    public Sholat_Jadwal_Data getSholatJadwalData() {
        return sholatJadwalData;
    }

    public void setSholatJadwalData(Sholat_Jadwal_Data sholatJadwalData) {
        this.sholatJadwalData = sholatJadwalData;
    }
}
