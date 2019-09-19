package com.qtasnim.qhopes.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.adapters.ViewPagerAdapter;
import com.qtasnim.qhopes.misc.BottomNavigationViewHelper;
import com.qtasnim.qhopes.models.DokterModel;
import com.qtasnim.qhopes.fragments.AppointmentNavFragment;
import com.qtasnim.qhopes.fragments.DiagnosisNavFragment;
import com.qtasnim.qhopes.fragments.HomeNavFragment;
import com.qtasnim.qhopes.utils.CustomDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivityPasien extends AppCompatActivity {

    private CustomDialog cd;
    private ArrayList<DokterModel> mModelData = new ArrayList<>();
    private Dialog dialog = null;
    private Boolean doubleBackToExitPressedOnce = false;
    private HomeNavFragment homeFragment;
    private AppointmentNavFragment appointmentFragment;
    private DiagnosisNavFragment diagnosisFragment;
    private int[] navId = {R.id.navigation_appointment,R.id.navigation_home,R.id.navigation_diagnosis};

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.fab_home)
    FloatingActionButton fab_home;
    @OnClick(R.id.fab_home) void home() {
        viewPager.setCurrentItem(1);
        fab_home.setSelected(true);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pasien);
        ButterKnife.bind(this);
        initClass();
        initView();
        initData();
    }
    private void initClass(){
        cd = new CustomDialog(this);
    }

    private void initView(){
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_appointment:
                        viewPager.setCurrentItem(0);
                        fab_home.setSelected(false);
                        return true;
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(1);
                        fab_home.setSelected(true);
                        return true;
                    case R.id.navigation_diagnosis:
                        viewPager.setCurrentItem(2);
                        fab_home.setSelected(false);
                        return true;
                }
                return false;
            }
        });
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        appointmentFragment =  new AppointmentNavFragment();
        homeFragment = new HomeNavFragment();
        diagnosisFragment =  new DiagnosisNavFragment();
        adapter.addFragment(appointmentFragment);
        adapter.addFragment(homeFragment);
        adapter.addFragment(diagnosisFragment);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
               navigation.setSelectedItemId(navId[position]);
           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });
        viewPager.setCurrentItem(1);
        navigation.setSelectedItemId(R.id.navigation_home);
        fab_home.setSelected(true);
    }

    private void initData() {
        mModelData.add(new DokterModel("yana","12345", 123, 123));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        cd.toastWarning("Tekan kembali untuk keluar");

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}
