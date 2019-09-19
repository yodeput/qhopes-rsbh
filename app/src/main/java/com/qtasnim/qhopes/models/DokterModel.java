package com.qtasnim.qhopes.models;

public class DokterModel {
    String username,password;
    int NIP,NIDK;

    public DokterModel(String username, String password, int NIP, int NIDK) {
        this.username = username;
        this.password = password;
        this.NIP = NIP;
        this.NIDK = NIDK;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNIP() {
        return NIP;
    }

    public void setNIP(int NIP) {
        this.NIP = NIP;
    }

    public int getNIDK() {
        return NIDK;
    }

    public void setNIDK(int NIDK) {
        this.NIDK = NIDK;
    }
}

