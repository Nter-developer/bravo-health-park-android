package com.example.bravohealthpark.retrofit;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.HashSet;
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

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            // Preference에 cookies를 넣어주는 작업을 수행
            SharedPreferenceBase.setSharedPreference(context, APIPreferences.SHARED_PREFERENCE_NAME_COOKIE, cookies);
        }
        return originalResponse;
    }
}