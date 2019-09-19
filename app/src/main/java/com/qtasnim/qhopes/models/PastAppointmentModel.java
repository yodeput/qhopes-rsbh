package com.qtasnim.qhopes.models;

public class PastAppointmentModel {

    String
        poliklinik,
        dokter,
        tanggal,
        jam;


    public PastAppointmentModel(String poliklinik, String dokter, String tanggal, String jam){
        this.poliklinik = poliklinik;
        this.dokter = dokter;
        this.tanggal = tanggal;
        this.jam = jam;
    }

    public String getPoliklinik() {
        return poliklinik;
    }

    public void setPoliklinik(String poliklinik) {
        this.poliklinik = poliklinik;
    }

    public String getDokter() {
        return dokter;
    }

    public void setDokter(String dokter) {
        this.dokter = dokter;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }
}
