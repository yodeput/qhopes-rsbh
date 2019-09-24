package com.qtasnim.qhopes.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.fragments.HomeNavFragment;
import com.qtasnim.qhopes.models.response.JadwalDokter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PendaftaranActivity extends AppCompatActivity {

    private SimpleDateFormat simpleDateFormat;
    private TimePickerDialog timePickerDialog;
    private Calendar newCalendar = Calendar.getInstance();
    DatePickerDialog pickerDialog;
    JadwalDokterActivity jadwalDokterActivity = new JadwalDokterActivity();
    public final int POLI_CODE = 1;

    @BindView(R.id.input_pilih_tanggal1)
    TextInputEditText input_pilih_tanggal;
    @BindView(R.id.input_pilih_jam1)
    TextInputEditText input_pilih_jam1;
    @BindView(R.id.input_pilih_poliklinik1)
    TextInputEditText input_pilih_poliklinik1;
    @BindView(R.id.input_pilih_dokter1)
    TextInputEditText input_pilih_dokter1;
    @BindView(R.id.btn_confirm_pendaftaran1)
    Button btn_confirm_pendaftaran1;
    @BindView(R.id.toolbarDaftar)
    Toolbar toolbar;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == POLI_CODE){
            if (resultCode == RESULT_OK){
                String poli = data.getExtras().getString("Poli");
                String dokter = data.getExtras().getString("Dokter");

                input_pilih_poliklinik1.setText(poli);
                input_pilih_dokter1.setText(dokter);

            }
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran_form1);

        ButterKnife.bind(this);

        toolbarView();

        input_pilih_poliklinik1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Poli = new Intent(PendaftaranActivity.this, JadwalDokterActivity.class);
                startActivityForResult(Poli, POLI_CODE);
            }
        });
        
        input_pilih_jam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jam();
            }
        });


        input_pilih_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int year = cldr.get(Calendar.YEAR);
                int month = cldr.get(Calendar.MONTH);
                int day = cldr.get(Calendar.DAY_OF_MONTH);

                pickerDialog = new DatePickerDialog(PendaftaranActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        input_pilih_tanggal.setText(dayOfMonth+"-"+(monthOfYear+1)+"-"+year);

                    }
                }, year, month, day);
                pickerDialog.show();

            }
        });


        btn_confirm_pendaftaran1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KonfirmasiDaftar();
            }
        });
    }

    private void toolbarView() {
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources(). getString(R.string.title_daftar));
        if (getSupportActionBar()!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(getResources(). getString(R.string.title_daftar));
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.anim_back_slide_in_right, R.anim.anim_back_slide_out_left);
            }
        });

    }


    private void KonfirmasiDaftar() {

        String Poliklinik = input_pilih_poliklinik1.getText().toString();
        String Dokter = input_pilih_dokter1.getText().toString();
        String Jam = input_pilih_jam1.getText().toString();
        String Tanggal = input_pilih_tanggal.getText().toString();

        if (TextUtils.isEmpty(Poliklinik)||TextUtils.isEmpty(Dokter)||TextUtils.isEmpty(Jam)||TextUtils.isEmpty(Tanggal)){

            Dialog emptyDaftar = new Dialog(this);
            emptyDaftar.setContentView(R.layout.view_dialog_empty_pendaftaran);
            emptyDaftar.show();

        }
        else {
            Dialog successDaftar = new Dialog(this);
            successDaftar.setContentView(R.layout.view_dialog_confirm_pendaftaran1);
            TextView NamaDokter = successDaftar.findViewById(R.id.tv_nama_dokter1);
            TextView JamPeriksa = successDaftar.findViewById(R.id.tv_jam_periksa);
            TextView TanggalPeriksa = successDaftar.findViewById(R.id.tv_tanggal_periksa);
            Button Konfirmasi = successDaftar.findViewById(R.id.btn_confirm_pendaftaran1);

            Konfirmasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            NamaDokter.setText(Dokter);
            JamPeriksa.setText(Jam);
            TanggalPeriksa.setText(Tanggal);

            successDaftar.show();
        }

    }

    private void Jam() {

        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                input_pilih_jam1.setText(hour+":"+minute);

            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), DateFormat.is24HourFormat(this));

        timePickerDialog.show();

    }


}
