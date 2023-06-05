package com.example.bravohealthpark.infra.retrofit;

import com.example.bravohealthpark.infra.network.AddCookiesInterceptor;
import com.example.bravohealthpark.infra.network.ReceivedCookiesInterceptor;

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
    private static Retrofit retrofitClient;

    public static RetrofitService getApiService() {
        return getInstance().create(RetrofitService.class);
    }

    private static Retrofit getInstance(){
        if(retrofitClient == null) {
            loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.addInterceptor(new AddCookiesInterceptor());
            httpClientBuilder.addInterceptor(new ReceivedCookiesInterceptor());
            httpClientBuilder.addInterceptor(loggingInterceptor);
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
