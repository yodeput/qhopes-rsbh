package com.qtasnim.qhopes.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.utils.CustomDialog;

public class LandingActivity extends AppCompatActivity {

    private Button mBtnDokter,mBtnPasien;
    private TextInputEditText mInputNipDokter;
    private TextView tvRegister;
    private Dialog dialog;
    private Button mBtnLoginDokter;
    private CustomDialog cd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // buat fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);
        cd = new CustomDialog(this);

        mBtnPasien = findViewById(R.id.btn_pasien);
        mBtnDokter = findViewById(R.id.btn_dokter);

        mBtnDokter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DokterDialog();
            }
        });

        mBtnPasien.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cd.show_p_Dialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                       cd.hide_p_Dialog();
                        Intent jumpActivity= new Intent(LandingActivity.this, MainActivityPasien.class);
                        startActivity(jumpActivity);
                        finish();
                    }
                }, 1000);


            }
        });
    }
    public void showKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void closeKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
    private void DokterDialog(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.view_dialog_dokter_form);
        mInputNipDokter = dialog.findViewById(R.id.input_nip_dokter_form);
        mInputNipDokter.requestFocus();
        mInputNipDokter.setFocusableInTouchMode(true);
        showKeyboard();
        mBtnLoginDokter = dialog.findViewById(R.id.btn_login_dokter);
        tvRegister = dialog.findViewById(R.id.tv_register_dokter);
        tvRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                closeKeyboard();
                Intent intent = new Intent(LandingActivity.this, RegisterDokterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mBtnLoginDokter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String isNip = mInputNipDokter.getText().toString();
                if (isNip.matches("12345")){
                    closeKeyboard();
                    showGreetingAlert();
                    dialog.dismiss();
                }else{
                    showNipAlert();
                }
            }
        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                closeKeyboard();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    protected void showNipAlert() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LandingActivity.this);
        alertDialogBuilder.setTitle("Alert");
        alertDialogBuilder.setMessage("NIP Tidak Ditemukan!");
        alertDialogBuilder.setCancelable(true)
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
    }
    protected void showGreetingAlert() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LandingActivity.this);
        alertDialogBuilder.setTitle("Selamat Datang");
        alertDialogBuilder.setMessage("Dokter Ayana \nNIP : 12345");
        alertDialogBuilder.setCancelable(true)
                .setPositiveButton("Masuk",  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent jumpActivity = new Intent(LandingActivity.this, MainActivity.class);
                        startActivity(jumpActivity);
                        finish();
                    }
                });

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
    }
}
