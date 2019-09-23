package com.qtasnim.qhopes.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.activities.MainActivity;
import com.qtasnim.qhopes.activities.MenuBeritaActivity;
import com.qtasnim.qhopes.activities.MenuHariIniActivity;
import com.qtasnim.qhopes.activities.MenuInfoActivity;
import com.qtasnim.qhopes.activities.MenuKontakActivity;
import com.qtasnim.qhopes.activities.MenuMingguiniActivity;
import com.qtasnim.qhopes.activities.PendaftaranActivity;
import com.qtasnim.qhopes.adapters.BeritaAdapter;
import com.qtasnim.qhopes.adapters.BeritaAdapterHorizontal;
import com.qtasnim.qhopes.adapters.SliderAdapter;
import com.qtasnim.qhopes.api.NetworkModule;
import com.qtasnim.qhopes.api.NetworkService;
import com.qtasnim.qhopes.models.BeritaModel;
import com.qtasnim.qhopes.models.response.Berita;
import com.qtasnim.qhopes.models.response.BeritaResponse;
import com.qtasnim.qhopes.models.response.Slider;
import com.qtasnim.qhopes.models.response.SliderResponse;
import com.smarteist.autoimageslider.SliderView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    private NetworkService mNetworkService;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.sliderView)
    SliderView sliderView;
    @BindView(R.id.btn_antrian1)
    FloatingActionButton btn_antrian1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nav_home, container, false);
        unbinder = ButterKnife.bind(this,rootView);
        mNetworkService = NetworkModule.getClient().create(NetworkService.class);
        getBerita();
        getSlider();

        btn_antrian1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        return rootView;
    }

    private void showDialog() {

        Dialog pasien = new Dialog(getActivity());
        pasien.setContentView(R.layout.view_dialog_popup_pilihan);
        Button pasien_lama = pasien.findViewById(R.id.btn_pasien_lama);
        Button pasien_baru = pasien.findViewById(R.id.btn_pasien_baru);

        pasien_lama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasien.dismiss();

                Dialog Medrek = new Dialog(getActivity());
                Medrek.setContentView(R.layout.view_dialog_medrek_form1);
                TextInputEditText noMedrek = Medrek.findViewById(R.id.input_medrek1);
                TextInputEditText noTanggal = Medrek.findViewById(R.id.input_tanggal);
                Button confirmMedrek = Medrek.findViewById(R.id.btn_confirm_medrek);

                confirmMedrek.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String NoMedrek = noMedrek.getText().toString();
                        String NoTanggalLahir = noTanggal.getText().toString();

                        if (TextUtils.isEmpty(NoMedrek)&&TextUtils.isEmpty(NoTanggalLahir)){

                            Dialog emptyMedrek = new Dialog(getActivity());
                            emptyMedrek.setContentView(R.layout.view_dialog_medrek_empty);
                            emptyMedrek.show();

                        }else {

                            Intent konfirmasiMedrek = new Intent(getActivity(), PendaftaranActivity.class);
                            startActivity(konfirmasiMedrek);

                        }

                    }
                });

                Medrek.show();
            }
        });

        pasien.show();

    }

    private void getBerita(){
        Call<BeritaResponse> beritaReq = mNetworkService.getBerita();
        beritaReq.enqueue(new Callback<BeritaResponse>() {
            @Override
            public void onResponse(Call<BeritaResponse> call, Response<BeritaResponse> response) {
                if(response.isSuccessful()){
                    generateBeritaView(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<BeritaResponse> call, Throwable t) {

            }
        });
    }

    private void getSlider(){
        Call<SliderResponse> sliderReq = mNetworkService.getSlider();
        sliderReq.enqueue(new Callback<SliderResponse>() {
            @Override
            public void onResponse(Call<SliderResponse> call, Response<SliderResponse> response) {
                if(response.isSuccessful()){
                    generateSliderView(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<SliderResponse> call, Throwable t) {

            }
        });
    }

    private void generateBeritaView(List<Berita> beritaList){
        mAdapter = new BeritaAdapterHorizontal(beritaList);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new BeritaAdapterHorizontal(beritaList);
        mAdapter.setOnItemClickListener(new BeritaAdapterHorizontal.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Berita obj, int position) {
                Log.e("berita click", obj.getTitle());
            }
        });
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }



    private void generateSliderView(List<Slider> sliderList){
        SliderAdapter sliderAdapter = new SliderAdapter(sliderList);
        sliderAdapter.setOnItemClickListener(new SliderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Slider obj, int position) {
                Log.e("slider click", obj.getDesc());
            }
        });
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorSelectedColor(getActivity().getResources().getColor(R.color.colorPrimary));
        sliderView.setIndicatorUnselectedColor(getActivity().getResources().getColor(R.color.white));
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }



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
