package com.qtasnim.qhopes.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.models.KontakModel;

import java.util.ArrayList;
import java.util.Objects;


public class MenuKontakActivity extends AppCompatActivity {


    Button btn_menu_info_todaftar;
    TextView mTvTitleKontak,mTvAlamatKontak, mTvEmailKontak, mTvPhone1Kontak, mTvPhone2Kontak;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //                // Create new fragment and transaction
//                Fragment newFragment = new HomeNavFragment();
//                // consider using Java coding conventions (upper first char class names!!!)
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//                // Replace whatever is in the fragment_container view with this fragment,
//                // and add the transaction to the back stack
//                transaction.replace(R.id.containerViewPager, newFragment);
//                transaction.addToBackStack(null);
//
//                // Commit the transaction
//                transaction.commit();


        return super.onOptionsItemSelected(item);
    }

    private void setActionBar() {
        Objects.requireNonNull(getSupportActionBar()).show();
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.tbtn_menu_kontak));
        ArrayList<View> textViews = new ArrayList<>();
        getWindow().getDecorView().findViewsWithText(textViews, getTitle(), View.FIND_VIEWS_WITH_TEXT);
        if (textViews.size() > 0) {
            AppCompatTextView appCompatTextView = null;
            if (textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get(0);
            } else {
                for (View v : textViews) {
                    if (v.getParent() instanceof Toolbar) {
                        appCompatTextView = (AppCompatTextView) v;
                        break;
                    }
                }
            }
            if (appCompatTextView != null) {
                ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                appCompatTextView.setLayoutParams(params);
                appCompatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kontak);
        //setActionBar();

        KontakModel mModel = new KontakModel("Contac Us","Jl. RE. Martadinata Bypass Cikarang","admin@rsbhaktihusada.com","+62 21 890 0530","+62 21 890 0570");
        mTvTitleKontak = findViewById(R.id.tv_title_kontak);
        mTvTitleKontak.setText(mModel.getTitle());
        mTvAlamatKontak = findViewById(R.id.tv_alamat_kontak);
        mTvAlamatKontak.setText(mModel.getAlamat());
        mTvEmailKontak = findViewById(R.id.tv_email_kontak);
        mTvEmailKontak.setText(mModel.getEmail());
        mTvPhone1Kontak = findViewById(R.id.tv_phone1_kontak);
        mTvPhone1Kontak.setText(mModel.getNomor1());
        mTvPhone2Kontak = findViewById(R.id.tv_phone2_kontak);
        mTvPhone2Kontak.setText(mModel.getNomor2());

    }
}