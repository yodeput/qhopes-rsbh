package com.qtasnim.qhopes.models;

public class BeritaModel {

    String judul, tanggalTerbit, foto, konten;



    public BeritaModel(String judul, String tanggalTerbit, String foto, String konten){
        this.judul = judul;
        this.tanggalTerbit = tanggalTerbit;
        this.foto = foto;
        this.konten = konten;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggalTerbit() {
        return tanggalTerbit;
    }

    public void setTanggalTerbit(String tanggalTerbit) {
        this.tanggalTerbit = tanggalTerbit;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }
}

