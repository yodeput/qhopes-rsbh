package com.qtasnim.qhopes.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JadwalDokter {
    @Expose
    @SerializedName("antri")
    private String antri;
    @Expose
    @SerializedName("terlayani")
    private String terlayani;
    @Expose
    @SerializedName("daftar")
    private String daftar;
    @Expose
    @SerializedName("kuota")
    private String kuota;
    @Expose
    @SerializedName("poli")
    private String poli;
    @Expose
    @SerializedName("nama_dokter")
    private String namaDokter;

    public String getAntri() {
        return antri;
    }

    public void setAntri(String antri) {
        this.antri = antri;
    }

    public String getTerlayani() {
        return terlayani;
    }

    public void setTerlayani(String terlayani) {
        this.terlayani = terlayani;
    }

    public String getDaftar() {
        return daftar;
    }

    public void setDaftar(String daftar) {
        this.daftar = daftar;
    }

    public String getKuota() {
        return kuota;
    }

    public void setKuota(String kuota) {
        this.kuota = kuota;
    }

    public String getPoli() {
        return poli;
    }

    public void setPoli(String poli) {
        this.poli = poli;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }
}
