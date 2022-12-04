package com.example.rcgnwhrsinventory.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mdetail {
    @SerializedName("id_material")
    @Expose
    private Integer idMaterial;
    @SerializedName("material_name")
    @Expose
    private String materialName;
    @SerializedName("material_number")
    @Expose
    private String materialNumber;
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
    private Integer total;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public String getMaterialName() {
        return materialName;
    }

    public String getMaterialNumber() {
        return materialNumber;
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

    public Integer getTotal() {
        return total;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
