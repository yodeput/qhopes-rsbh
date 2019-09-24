package com.qtasnim.qhopes.models;

import android.os.Parcel;

import com.qtasnim.qhopes.models.response.JadwalDokter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class DokterGroup extends ExpandableGroup<JadwalDokter> {

    public DokterGroup(String title, List<JadwalDokter> items) {
        super(title, items);
    }


}
