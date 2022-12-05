package com.example.rcgnwhrsinventory.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Main {

    @SerializedName("summery")
    @Expose
    private List<Mhistory> history = null;

    public List<Mhistory> getHistory() {
        return history;
    }

    @SerializedName("activity")
    @Expose
    private List<Mactivity> aktivitas = null;

    @SerializedName("total")
    @Expose
    private Mtotal total;

    public Mtotal getTotal() {
        return total;
    }

    @SerializedName("Viewall")
    @Expose
    private List<Mviewall> viewall = null;

    @SerializedName("employee")
    @Expose
    private List<Memployee> employee = null;

    @SerializedName("material")
    @Expose
    private List<Mmaterial> material = null;


    public List<Mviewall> getViewall() {
        return viewall;
    }

    public void setViewall(List<Mviewall> viewall) {
        this.viewall = viewall;
    }

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
