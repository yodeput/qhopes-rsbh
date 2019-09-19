package com.qtasnim.qhopes.api;


import com.qtasnim.qhopes.models.response.JadwalDokterResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface NetworkService {

    //======Jadwal Dokter======//
    @GET("N6ru7VgM")
    Call<JadwalDokterResponse> Penjamin();

}
