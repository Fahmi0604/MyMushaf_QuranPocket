package com.example.mymushaf.surah.models;

import com.google.gson.annotations.SerializedName;

public class Surah_nameTranslation {

    @SerializedName("id")
    private String id;

    @SerializedName("ar")
    private String ar;

    @SerializedName("en")
    private String en;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAr() {
        return ar;
    }

    public void setAr(String ar) {
        this.ar = ar;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }
}
