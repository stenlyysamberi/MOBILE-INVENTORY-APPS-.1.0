package com.example.rcgnwhrsinventory.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mhistory {
    @SerializedName("material_number")
    @Expose
    private String materialNumber;
    @SerializedName("material_name")
    @Expose
    private String materialName;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("container")
    @Expose
    private String container;
    @SerializedName("uom")
    @Expose
    private String uom;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("nama")
    @Expose
    private String nama;

    public String getMaterialNumber() {
        return materialNumber;
    }

    public String getMaterialName() {
        return materialName;
    }

    public String getFile() {
        return file;
    }

    public String getContainer() {
        return container;
    }

    public String getUom() {
        return uom;
    }

    public String getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getNama() {
        return nama;
    }
}
