package com.qtasnim.qhopes.api;


import com.qtasnim.qhopes.models.response.BeritaResponse;
import com.qtasnim.qhopes.models.response.JadwalDokterResponse;
import com.qtasnim.qhopes.models.response.SliderResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface NetworkService {

    //======Jadwal Dokter======//
    @GET("N6ru7VgM")
    Call<JadwalDokterResponse> getDokter();

    @GET("Zwrr8RWC")
    Call<BeritaResponse> getBerita();

    @GET("3m7tCpqs")
    Call<SliderResponse> getSlider();

}
