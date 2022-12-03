package com.example.rcgnwhrsinventory.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mtotal {
    @SerializedName("stok")
    @Expose
    private int stok;

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
