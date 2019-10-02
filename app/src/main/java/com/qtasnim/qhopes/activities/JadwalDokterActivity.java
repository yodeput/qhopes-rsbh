package com.qtasnim.qhopes.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.adapters.JadwalDokterAdapter;
import com.qtasnim.qhopes.adapters.MenuHariiniAdapter;
import com.qtasnim.qhopes.adapters.TestExpandAdapter;
import com.qtasnim.qhopes.api.NetworkModule;
import com.qtasnim.qhopes.api.NetworkService;
import com.qtasnim.qhopes.models.DokterGroup;
import com.qtasnim.qhopes.models.MenuHariiniModel;
import com.qtasnim.qhopes.models.response.JadwalDokter;
import com.qtasnim.qhopes.models.response.JadwalDokterResponse;
import com.qtasnim.qhopes.utils.CustomDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.qtasnim.qhopes.utils.Apps.getDayName;


public class JadwalDokterActivity extends AppCompatActivity {

    private ArrayList<MenuHariiniModel> mModelData = new ArrayList<>();
    private CustomDialog cd;
    private TestExpandAdapter mAdapter;
    private List<JadwalDokter> dokterList;
    private NetworkService mNetworkService;

    private String src;
    private String tgl;

    private DatePickerDialog datePickerDialog;
    private int Year, Month, Day;

    @BindView(R.id.lytParent)
    LinearLayout lytParent;
    @BindView(R.id.lytError)
    RelativeLayout lytError;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.shimmer)
    ShimmerFrameLayout shimmer;
    @OnClick(R.id.but_retry) void retry(){
        errorGone();
        getDokter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_hariini);
        ButterKnife.bind(this);
        initClass();
        initToolbar();
        mNetworkService = NetworkModule.getClient().create(NetworkService.class);
        initDatePicker();

        Intent i = getIntent();
        src = i.getStringExtra("src");

    }

    void initClass(){
        cd = new CustomDialog(this);
    }

    void initToolbar() {
        lytError.setVisibility(View.GONE);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_dokter));
        if (getSupportActionBar() != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_dokter));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.anim_back_slide_in_right, R.anim.anim_back_slide_out_left);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.anim_back_slide_in_right, R.anim.anim_back_slide_out_left);
    }

    private void getDokter(){
        shimmer.startShimmer();
        Call<JadwalDokterResponse> getDokter = mNetworkService.getDokter();
        getDokter.enqueue(new Callback<JadwalDokterResponse>() {
            @Override
            public void onResponse(Call<JadwalDokterResponse> call, Response<JadwalDokterResponse> response) {
                if(response.isSuccessful()){
                    errorGone();
                    generateListView(response.body());
                } else {
                    errorVisible();
                }
            }

            @Override
            public void onFailure(Call<JadwalDokterResponse> call, Throwable t) {
               errorVisible();
            }
        });
    }

    private void generateListView(JadwalDokterResponse response){
        List<JadwalDokter> jadwal = response.getData();
        List<String> poli = new ArrayList<>();

        for(int i =0;i<jadwal.size();i++){
            poli.add(jadwal.get(i).getPoli());
        }
        Set<String> s = new HashSet<>();
        s.addAll(poli);
        poli.clear();
        poli.addAll(s);
        Collections.sort(poli);

        Map<String, List<JadwalDokter>> jadwalGrouped =
                jadwal.stream().collect(Collectors.groupingBy(w -> w.getPoli()));
        DokterGroup[] dd = new DokterGroup[jadwalGrouped.size()];

        for(int i =0;i<poli.size();i++){
            dd[i]=new DokterGroup("Poli "+poli.get(i), jadwalGrouped.get(poli.get(i)));
        }
        List<DokterGroup> dokterGroups= Arrays.asList(dd);
        mAdapter = new TestExpandAdapter(dokterGroups, jadwal);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnChildClickListener(new TestExpandAdapter.OnChildClickListener() {
            @Override
            public void onItemClick(View view, JadwalDokter obj, int position) {
                Log.e("Dokter",obj.getNamaDokter());
                if(src.equals("daftar")) {
                    Intent data = new Intent();
                    data.putExtra("Poli", obj.getPoli());
                    data.putExtra("Dokter", obj.getNamaDokter());
                    setResult(RESULT_OK, data);
                    finish();
                } else {
                    Intent i = new Intent(JadwalDokterActivity.this, PendaftaranActivity.class);
                    i.putExtra("src", "dokter");
                    i.putExtra("data", obj);
                    i.putExtra("tanggal", tgl);
                    startActivity(i);
                    finish();
                }

            }
        });
        shimmer.startShimmer();
        shimmer.setVisibility(View.GONE);
        lytParent.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


    }
    public static List<DokterGroup> makeJadwal() {
        return Arrays.asList(

        );
    }

    void errorVisible(){
        toolbar.setVisibility(View.GONE);
        lytError.setVisibility(View.VISIBLE);
    }

    void errorGone(){
        toolbar.setVisibility(View.VISIBLE);
        lytError.setVisibility(View.GONE);
    }

    public static DokterGroup makeDokter(String poli, List<JadwalDokter> list) {
        return new DokterGroup(poli,list);
    }

    void initDatePicker() {
        Calendar calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        Calendar min_date_c = Calendar.getInstance();
        min_date_c.set(Calendar.DAY_OF_MONTH, Day + 1);
        Calendar max_date_c = Calendar.getInstance();
        Log.e("wkwkwwk", "-----" + day + Calendar.SUNDAY);
        if (day == Calendar.SUNDAY) {
            Log.e("wkwkwwk", "01");
            max_date_c.set(Calendar.DAY_OF_MONTH, Day + 3);
            datePickerDialog.setMaxDate(max_date_c);
            showDatePicker(min_date_c, max_date_c);
        } else if (day + 1 == 8) {
            Log.e("wkwkwwk", "12");
            max_date_c.set(Calendar.DAY_OF_MONTH, Day + 4);
            showDatePicker(min_date_c, max_date_c);
        } else if (day + 2 == 8) {
            Log.e("wkwkwwk", "23");
            max_date_c.set(Calendar.DAY_OF_MONTH, Day + 5);
            showDatePicker(min_date_c, max_date_c);
        } else if (day + 3 == 8) {
            Log.e("wkwkwwk", "34");
            max_date_c.set(Calendar.DAY_OF_MONTH, Day + 5);
            showDatePicker(min_date_c, max_date_c);
        } else if (day + 4 == 8) {
            Log.e("wkwkwwk", "34");
            max_date_c.set(Calendar.DAY_OF_MONTH, Day + 3);
            showDatePicker(min_date_c, max_date_c);
        } else {
            max_date_c.set(Calendar.DAY_OF_MONTH, Day + 3);
            showDatePicker(min_date_c, max_date_c);
        }
    }

    private void showDatePicker(Calendar min_date_c, Calendar max_date_c) {

        datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(0);
                cal.set(year, monthOfYear, dayOfMonth);
                Date date = cal.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
                Date day = cal.getTime();
                String datetime = sdf.format(date);
                String dayName = getDayName(sdf_.format(day));
                tgl = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                getDokter();
            }
        }, Year, Month, Day);
        datePickerDialog.setThemeDark(false);
        datePickerDialog.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePickerDialog.showYearPickerFirst(false);
        datePickerDialog.setTitle("Pilih Tanggal Kunjungan");
        datePickerDialog.setMinDate(min_date_c);
        datePickerDialog.setMaxDate(max_date_c);

        for (Calendar loopdate = min_date_c; min_date_c.before(max_date_c); min_date_c.add(Calendar.DATE, 1), loopdate = min_date_c) {
            int dayOfWeek = loopdate.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SUNDAY) {
                Calendar[] disabledDays = new Calendar[1];
                disabledDays[0] = loopdate;
                datePickerDialog.setDisabledDays(disabledDays);
            }
        }

        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialogInterface) {
                datePickerDialog.dismiss();
                JadwalDokterActivity.this.finish();
            }
        });

        datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
    }



}