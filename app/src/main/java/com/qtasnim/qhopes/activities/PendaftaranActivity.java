package com.qtasnim.qhopes.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.fragments.HomeNavFragment;
import com.qtasnim.qhopes.models.response.JadwalDokter;
import com.qtasnim.qhopes.utils.Apps;
import com.qtasnim.qhopes.utils.CustomDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PendaftaranActivity extends AppCompatActivity {

    private CustomDialog cd;
    private SimpleDateFormat simpleDateFormat;
    private TimePickerDialog timePickerDialog;
    private Calendar newCalendar = Calendar.getInstance();
    DatePickerDialog pickerDialog;
    JadwalDokterActivity jadwalDokterActivity = new JadwalDokterActivity();
    public final int POLI_CODE = 1;
    private Dialog dialog;
    private com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialog;
    private String src="";
    private JadwalDokter dokterData = new JadwalDokter();
    private int year, month, day;


    @BindView(R.id.input_rm)
    TextInputEditText input_rm;
    @BindView(R.id.input_poli)
    TextInputEditText input_poli;
    @BindView(R.id.input_dokter)
    TextInputEditText input_dokter;
    @BindView(R.id.input_tanggal)
    TextInputEditText input_tanggal;
    @BindView(R.id.input_jam)
    TextInputEditText input_jam;

    @BindView(R.id.lyt_input_poli)
    TextInputLayout lyt_input_poli;
    @BindView(R.id.lyt_input_dokter)
    TextInputLayout lyt_input_dokter;

    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.toolbarDaftar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran_form1);
        ButterKnife.bind(this);
        Intent i = getIntent();
        src = i.getStringExtra("src");
        if(src.equals("dokter")){
            dokterData = (JadwalDokter) i.getParcelableExtra("data");
            input_tanggal.setText(i.getStringExtra("tanggal"));
            input_poli.setText(dokterData.getPoli());
            input_dokter.setText(dokterData.getNamaDokter());
            dialogRM();
        } else {
            input_rm.setText(i.getStringExtra("NoMedrek"));
        }
        initClass();
        initToolbar();

        input_poli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Poli = new Intent(PendaftaranActivity.this, JadwalDokterActivity.class).putExtra("src","daftar");
                startActivityForResult(Poli, POLI_CODE);
            }
        });
        
        input_jam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jam();
            }
        });


        input_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!src.equals("dokter")) {
                    final Calendar cldr = Calendar.getInstance();
                    int year = cldr.get(Calendar.YEAR);
                    int month = cldr.get(Calendar.MONTH);
                    int day = cldr.get(Calendar.DAY_OF_MONTH);

                    pickerDialog = new DatePickerDialog(PendaftaranActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            input_tanggal.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, year, month, day);
                    pickerDialog.show();
                }
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KonfirmasiDaftar();
            }
        });
    }

    private void initClass(){
        cd = new CustomDialog(this);
    }

    private void initToolbar() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == POLI_CODE){
            if (resultCode == RESULT_OK){
                String poli = data.getExtras().getString("Poli");
                String dokter = data.getExtras().getString("Dokter");

                input_poli.setText(poli);
                lyt_input_poli.setHint("Poliklinik");
                input_dokter.setText(dokter);
                lyt_input_dokter.setHint("Dokter");

            }
        }
    }

    private void dialogRM() {
        if (dialog == null || !dialog.isShowing()) {
            dialog = new Dialog(PendaftaranActivity.this, R.style.DialogSlideAnim);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.view_dialog_medrek_form1);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER_HORIZONTAL;
            dialog.show();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setAttributes(lp);

            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new Dialog.OnKeyListener() {

                @Override
                public boolean onKey(DialogInterface arg0, int keyCode,
                                     KeyEvent event) {
                    // TODO Auto-generated method stub
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        dialog.dismiss();
                    }
                    return true;
                }
            });
            EditText editNoRm = (EditText) dialog.findViewById(R.id.editNoRm);
            EditText editTanggal = (EditText) dialog.findViewById(R.id.editTanggal);
            Button confirmMedrek = dialog.findViewById(R.id.btn_confirm_medrek);

            editTanggal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    datePickerDialog = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                            PendaftaranActivity.this.year = year;
                            PendaftaranActivity.this.month = monthOfYear + 1;
                            PendaftaranActivity.this.day = dayOfMonth;
                            String tgl = Integer.toString(dayOfMonth);
                            String bulan = Apps.getMonthNameByInt(monthOfYear + 1);
                            String tahun = Integer.toString(year);
                            editTanggal.setText(tgl + " " + bulan + " " + tahun);
                        }
                    }, year, month, day);
                    datePickerDialog.setThemeDark(false);
                    datePickerDialog.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));
                    datePickerDialog.showYearPickerFirst(true);
                    datePickerDialog.setTitle("Pilih Tanggal Lahir");
                    datePickerDialog.show(PendaftaranActivity.this.getFragmentManager(), "Tanggal Lagir");

                }
            });

            confirmMedrek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String NoMedrek = editNoRm.getText().toString();
                    String NoTanggalLahir = editTanggal.getText().toString();

                    if (TextUtils.isEmpty(NoMedrek) || TextUtils.isEmpty(NoTanggalLahir)) {
                        cd.toastWarning("Lengkapi data yang diperlukan");
                    } else {
                        input_rm.setText(NoMedrek);
                        dialog.dismiss();
                    }

                }
            });
        }
    }

    private void KonfirmasiDaftar() {

        String Poliklinik = input_poli.getText().toString();
        String Dokter = input_dokter.getText().toString();
        String Jam = input_jam.getText().toString();
        String Tanggal = input_tanggal.getText().toString();

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

                input_jam.setText(hour+":"+minute);

            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), DateFormat.is24HourFormat(this));

        timePickerDialog.show();

    }


}
