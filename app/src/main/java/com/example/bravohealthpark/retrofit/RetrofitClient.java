package com.example.bravohealthpark.retrofit;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bravohealthpark.RetrofitService;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://10.0.2.2:4903";
    private static OkHttpClient.Builder httpClientBuilder;
    private static HttpLoggingInterceptor loggingInterceptor;
    private static Retrofit retrofitClient;

    public static RetrofitService getApiService() {
        return getInstance().create(RetrofitService.class);
    }

    private static Retrofit getInstance(){
        if(retrofitClient == null) {
            loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.addInterceptor(loggingInterceptor);
            httpClientBuilder.addInterceptor(new AddCookiesInterceptor());
            httpClientBuilder.addInterceptor(new ReceivedCookiesInterceptor());
            httpClientBuilder.protocols(Collections.singletonList(Protocol.HTTP_1_1));

            retrofitClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClientBuilder.build()) // OkHttpClient 설정 적용
                    .build();
        }
        return retrofitClient;
    }
}
