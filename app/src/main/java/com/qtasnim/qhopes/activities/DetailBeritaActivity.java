package com.qtasnim.qhopes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.fragments.HomeNavFragment;
import com.qtasnim.qhopes.models.response.Berita;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailBeritaActivity extends AppCompatActivity {

    @BindView(R.id.TextContentDetailBerita)
    TextView TextContentDetailBerita;
    @BindView(R.id.TextTitleDetailBerita)
    TextView TextTitleDetailBerita;
    @BindView(R.id.ImageDetailBerita)
    ImageView ImageDetailBerita;
    @BindView(R.id.TextDateTimeDetailBerita)
    TextView TextDateTimeDetailBerita;
    @BindView(R.id.ToolbarDetailBerita)
    Toolbar ToolbarDetailBerita;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        ButterKnife.bind(this);

        Intent kunci = getIntent();
        Berita berita = kunci.getParcelableExtra("id");
        TextDateTimeDetailBerita.setText(berita.getDatetime());
        Glide.with(this).load(berita.getImage()).into(ImageDetailBerita);
        TextTitleDetailBerita.setText(berita.getTitle());
        TextContentDetailBerita.setText(berita.getContent());

        toolbar();
    }

    private void toolbar() {
        this.setSupportActionBar(ToolbarDetailBerita);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_berita));
        if (getSupportActionBar()!=null){
            setSupportActionBar(ToolbarDetailBerita);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(getResources(). getString(R.string.title_daftar));
        }

        ToolbarDetailBerita.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.anim_back_slide_in_right, R.anim.anim_back_slide_out_left);
            }
        });
    }
}
