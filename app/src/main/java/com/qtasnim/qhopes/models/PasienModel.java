package com.qtasnim.qhopes.models;

public class PasienModel {
    String username, useremail, userphone, medrec;

    public PasienModel(String username, String useremail, String userphone, String medrec) {
        this.username = username;
        this.useremail = useremail;
        this.userphone = userphone;
        this.medrec = medrec;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getMedrek() {
        return medrec;
    }

    public void setMedrek(String medrec) {
        this.medrec = medrec;
    }
}
