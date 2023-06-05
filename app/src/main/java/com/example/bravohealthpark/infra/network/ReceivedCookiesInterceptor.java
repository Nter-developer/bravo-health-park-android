package com.example.bravohealthpark.infra.network;

import android.content.Context;
import android.util.Log;

import com.example.bravohealthpark.infra.preferences.APIPreferences;
import com.example.bravohealthpark.infra.preferences.SharedPreferenceBase;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {

    private static Context context;

    public static void setContext(Context context) {
        ReceivedCookiesInterceptor.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        String token = originalResponse.headers("Authorization").toString();

        token = token.replace("[", "").replace("]", "");
        // Preference에 cookies를 넣어주는 작업을 수행
        SharedPreferenceBase.setSharedPreference(APIPreferences.SHARED_PREFERENCE_NAME_COOKIE, token);
        Log.i("SharedToken", token);
        return originalResponse;
    }
}