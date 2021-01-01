package com.example.mymushaf.models;

import com.google.gson.annotations.SerializedName;

public class Surah_recitations {

    @SerializedName("name")
    private String nameQari;

    @SerializedName("audio_url")
    private String audio_url;

    public String getNameQari() {
        return nameQari;
    }

    public void setNameQari(String nameQari) {
        this.nameQari = nameQari;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }
}
