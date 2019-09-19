package com.qtasnim.qhopes.api;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.qtasnim.qhopes.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetworkModule {

    public static final String BASE_URL = BuildConfig.BASEURL;
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("ResponseTest")
                .addHeader("X-Client-Type", "Android")
                .enableAndroidStudio_v3_LogsHack(true)
                .build());

        OkHttpClient okHttpClient = client.build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}