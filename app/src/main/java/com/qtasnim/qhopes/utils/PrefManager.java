package com.qtasnim.qhopes.utils;

/*
 * ******************************************************
 *  * Copyright (c) 2019. All Rights Reserved
 *  * Unauthorized copying of this file, via any medium is strictly prohibited
 *  * Proprietary and confidential
 *  * Created by: Yogi Dewansyah<yodeput@gmail.com>
 *  ******************************************************
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PrefManager {
    private static final String PREF_NAME = "pref";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_IS_PENJAMIN_SEL = "isPenjamin";

    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void clearData() {
        editor.clear().commit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.commit();
    }

    public void setPenjamin(boolean isSelected) {
        editor.putBoolean(KEY_IS_PENJAMIN_SEL, isSelected);
        editor.commit();
    }

    public boolean isPenjaminSel() {
        return pref.getBoolean(KEY_IS_PENJAMIN_SEL, false);
    }

    public void addPref(String tag, String value) {
        editor.putString(tag, value);
        editor.commit();
    }

    public void addPenjaminPos(int value) {
        editor.putInt("penjaminPos", value);
        editor.commit();
    }

    public int getPenjaminPos() {
        return pref.getInt("penjaminPos", 0);
    }



    public String getPref(String tag) {
        return pref.getString(tag, "");
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setPINWrong(Boolean pin) {
        editor.putBoolean("PINWrong", pin);
    }


}
