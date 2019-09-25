package com.qtasnim.qhopes.activities;

import android.app.Dialog;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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


public class JadwalDokterActivity extends AppCompatActivity {

    private ArrayList<MenuHariiniModel> mModelData = new ArrayList<>();
    private CustomDialog cd;
    private TestExpandAdapter mAdapter;
    private List<JadwalDokter> dokterList;
    private NetworkService mNetworkService;

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
        getDokter();
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
                Intent data = new Intent();
                data.putExtra("Poli", obj.getPoli());
                data.putExtra("Dokter", obj.getNamaDokter());
                setResult(RESULT_OK, data);
                finish();
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

}