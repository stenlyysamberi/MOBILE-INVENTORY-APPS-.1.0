package com.example.rcgnwhrsinventory.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Main {
    @SerializedName("activity")
    @Expose
    private List<Mactivity> aktivitas = null;

    @SerializedName("employee")
    @Expose
    private List<Memployee> employee = null;

    @SerializedName("material")
    @Expose
    private List<Mmaterial> material = null;

    public List<Mactivity> getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(List<Mactivity> aktivitas) {
        this.aktivitas = aktivitas;
    }

    public List<Memployee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Memployee> employee) {
        this.employee = employee;
    }

    public List<Mmaterial> getMaterial() {
        return material;
    }

    public void setMaterial(List<Mmaterial> material) {
        this.material = material;
    }
}
