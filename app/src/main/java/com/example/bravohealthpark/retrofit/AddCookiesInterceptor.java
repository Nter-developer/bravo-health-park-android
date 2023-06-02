package com.example.bravohealthpark.retrofit;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        // Preference에서 token를 가져오는 작업을 수행
        String token =  SharedPreferenceBase.getSharedPreference(
                APIPreferences.SHARED_PREFERENCE_NAME_COOKIE, new String());

        builder.addHeader("Authorization", token);

        // Web,Android,iOS 구분을 위해 User-Agent세팅
        builder.removeHeader("User-Agent").addHeader("User-Agent", "Android");

        return chain.proceed(builder.build());
    }
}