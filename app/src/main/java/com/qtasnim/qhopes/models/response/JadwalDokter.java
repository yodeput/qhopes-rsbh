package com.qtasnim.qhopes.models.response;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JadwalDokter implements Parcelable {
    @Expose
    @SerializedName("id")
    private int id;

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
    @Expose
    @SerializedName("jadwal")
    private String jadwal;

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public JadwalDokter(int id, String antri, String terlayani, String daftar, String kuota, String poli, String namaDokter) {
        this.id = id;
        this.antri = antri;
        this.terlayani = terlayani;
        this.daftar = daftar;
        this.kuota = kuota;
        this.poli = poli;
        this.namaDokter = namaDokter;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.antri);
        dest.writeString(this.terlayani);
        dest.writeString(this.daftar);
        dest.writeString(this.kuota);
        dest.writeString(this.poli);
        dest.writeString(this.namaDokter);
    }

    public JadwalDokter() {
    }

    protected JadwalDokter(Parcel in) {
        this.id = in.readInt();
        this.antri = in.readString();
        this.terlayani = in.readString();
        this.daftar = in.readString();
        this.kuota = in.readString();
        this.poli = in.readString();
        this.namaDokter = in.readString();
    }

    public static final Creator<JadwalDokter> CREATOR = new Creator<JadwalDokter>() {
        @Override
        public JadwalDokter createFromParcel(Parcel source) {
            return new JadwalDokter(source);
        }

        @Override
        public JadwalDokter[] newArray(int size) {
            return new JadwalDokter[size];
        }
    };
}
