package com.example.bravohealthpark.infra.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    public static void showCustomToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
