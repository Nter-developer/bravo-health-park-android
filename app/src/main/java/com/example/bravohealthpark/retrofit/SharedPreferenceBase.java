package com.example.bravohealthpark.retrofit;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SharedPreferenceBase {

    private static final String PREFERENCE_API = "APIPreference";
    private static SharedPreferences sharedPreferences;
    private static Context context;

    public static void setSharedPreference(Context context, String key, Set<String> value) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_API, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    public static Set<String> getSharedPreference(Context context, String key, Set<String> value) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_API, Context.MODE_PRIVATE);
        return sharedPreferences.getStringSet(key, value);
    }
}

