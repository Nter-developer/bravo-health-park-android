package com.example.bravohealthpark.retrofit;

import android.widget.EditText;

import com.example.bravohealthpark.R;
import com.example.bravohealthpark.RetrofitService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://10.0.2.2:4903";
    private static OkHttpClient.Builder httpClientBuilder;
    private static HttpLoggingInterceptor loggingInterceptor;

    public static RetrofitService getApiService() {
        return getInstance().create(RetrofitService.class);
    }

    private static Retrofit getInstance(){
        // OkHttpClient 빌더 생성
        httpClientBuilder = new OkHttpClient.Builder();

        // 로깅 인터셉터 추가
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(loggingInterceptor);

        // TLS 버전 지정
        httpClientBuilder.protocols(Collections.singletonList(Protocol.HTTP_1_1));

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build()) // OkHttpClient 설정 적용
                .build();
    }
}
