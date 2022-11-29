package com.example.rcgnwhrsinventory.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Memployee {
    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("img_profil")
    @Expose
    private String profil;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }
}
