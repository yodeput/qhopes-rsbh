package com.qtasnim.qhopes.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.activities.MainActivity;
import com.qtasnim.qhopes.activities.MenuBeritaActivity;
import com.qtasnim.qhopes.activities.MenuHariIniActivity;
import com.qtasnim.qhopes.activities.MenuInfoActivity;
import com.qtasnim.qhopes.activities.MenuKontakActivity;
import com.qtasnim.qhopes.activities.MenuMingguiniActivity;
import com.qtasnim.qhopes.adapters.BeritaAdapter;
import com.qtasnim.qhopes.adapters.BeritaAdapterHorizontal;
import com.qtasnim.qhopes.models.BeritaModel;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeNavFragment extends Fragment {

    private int[] sampleImages = {R.drawable.jp1, R.drawable.jp2, R.drawable.jp3, R.drawable.jp4};
    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private RecyclerView.LayoutManager layoutManager;
    private Unbinder unbinder;
    private BeritaAdapterHorizontal mAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.carousel_view)
    CarouselView carousel_view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nav_home, container, false);
        unbinder = ButterKnife.bind(this,rootView);
        initView();

        return rootView;
    }

    private void initView(){
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ArrayList<BeritaModel> mModelData = new ArrayList<>();
        mModelData.add(new BeritaModel("Pelatihan Pemadaman Kebakaran", "15/05/2019", "foto_berita", "RS. Bhakti Husada Cikarang melakukan pelatihan pemadam kebakaran terhadap semua staff dan karyawan yang dilaksanakn setiap tahun dan bekerja sama dengan tim pemadam kebakaran Kab. Bekasi"));
        mModelData.add(new BeritaModel("ISPA", "10/10/17", "foto_berita2", "ISPA adalah kepanjanngan dari Infeksi Saluran Pernafasan Akut yang berarti terjadinya infeksi yang parah pada bagian sinus, tenggorokan, saluran udara, atau paru-paru. Kondisi ini menyebabkan fungsi pernafasan menjadi terganggu. Jika tidak segera ditangani, ISPA dapat menyebar ke seluruh sistem pernafasan tubuh bahkan dapat menyebabkan kematian."));
        mAdapter = new BeritaAdapterHorizontal(mModelData);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        carousel_view.setPageCount(sampleImages.length);
        carousel_view.setImageListener(imageListener);

    }


    private ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
