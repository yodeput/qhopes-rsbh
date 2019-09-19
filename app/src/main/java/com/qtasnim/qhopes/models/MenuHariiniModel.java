package com.qtasnim.qhopes.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuHariiniModel implements Parcelable {

    String nama_poliklinik, nama_dokter, jam_praktek, pasien_terdaftar, pasien_terlayani, kuota_pasien, pasien_checkin;

    public String getNama_poliklinik() {
        return nama_poliklinik;
    }

    public void setNama_poliklinik(String nama_poliklinik) {
        this.nama_poliklinik = nama_poliklinik;
    }

    public String getNama_dokter() {
        return nama_dokter;
    }

    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    public String getJam_praktek() {
        return jam_praktek;
    }

    public void setJam_praktek(String jam_praktek) {
        this.jam_praktek = jam_praktek;
    }

    public String getPasien_terdaftar() {
        return pasien_terdaftar;
    }

    public void setPasien_terdaftar(String pasien_terdaftar) {
        this.pasien_terdaftar = pasien_terdaftar;
    }

    public String getPasien_terlayani() {
        return pasien_terlayani;
    }

    public void setPasien_terlayani(String pasien_terlayani) {
        this.pasien_terlayani = pasien_terlayani;
    }

    public String getKuota_pasien() {
        return kuota_pasien;
    }

    public void setKuota_pasien(String kuota_pasien) {
        this.kuota_pasien = kuota_pasien;
    }

    public String getPasien_checkin() {
        return pasien_checkin;
    }

    public void setPasien_checkin(String pasien_checkin) {
        this.pasien_checkin = pasien_checkin;
    }

    public MenuHariiniModel(String nama_poliklinik, String nama_dokter, String jam_praktek, String pasien_terdaftar, String pasien_terlayani, String kuota_pasien, String pasien_checkin) {
        this.nama_poliklinik = nama_poliklinik;
        this.nama_dokter = nama_dokter;
        this.jam_praktek = jam_praktek;
        this.pasien_terdaftar = pasien_terdaftar;
        this.pasien_terlayani = pasien_terlayani;
        this.kuota_pasien = kuota_pasien;
        this.pasien_checkin = pasien_checkin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama_poliklinik);
        dest.writeString(this.nama_dokter);
        dest.writeString(this.jam_praktek);
        dest.writeString(this.pasien_terdaftar);
        dest.writeString(this.pasien_terlayani);
        dest.writeString(this.kuota_pasien);
        dest.writeString(this.pasien_checkin);
    }

    protected MenuHariiniModel(Parcel in) {
        this.nama_poliklinik = in.readString();
        this.nama_dokter = in.readString();
        this.jam_praktek = in.readString();
        this.pasien_terdaftar = in.readString();
        this.pasien_terlayani = in.readString();
        this.kuota_pasien = in.readString();
        this.pasien_checkin = in.readString();
    }

    public static final Parcelable.Creator<MenuHariiniModel> CREATOR = new Parcelable.Creator<MenuHariiniModel>() {
        @Override
        public MenuHariiniModel createFromParcel(Parcel source) {
            return new MenuHariiniModel(source);
        }

        @Override
        public MenuHariiniModel[] newArray(int size) {
            return new MenuHariiniModel[size];
        }
    };
}
