package com.qtasnim.qhopes.models;

public class profilpasienmodel {

    String name;
    String no_med_rec;


    public profilpasienmodel(String nameP, String No_medrec ) {
        this.name=nameP;
        this.no_med_rec=No_medrec;
    }

    public String getName() {
        return name;
    }

    public String getno_med_rec() {
        return no_med_rec;
    }


}
