package com.example.rcgnwhrsinventory.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mactivity {
    @SerializedName("id_material")
    @Expose
    private int id_material;

    @SerializedName("material_name")
    @Expose
    private String material_name;

    @SerializedName("material_number")
    @Expose
    private String material_number;

    @SerializedName("file")
    @Expose
    private String  file;

    @SerializedName("container")
    @Expose
    private String container;

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("uom")
    @Expose
    private String uom;

    public int getId_material() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material = id_material;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getMaterial_number() {
        return material_number;
    }

    public void setMaterial_number(String material_number) {
        this.material_number = material_number;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }
}
