package com.example.bravohealthpark.infra.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class IntentUtils {

    public static void startNewActivity(Context context, Class<? extends Activity> targetActivity, boolean clearTask) {
        Intent intent = new Intent(context, targetActivity);

        if (clearTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        context.startActivity(intent);

        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }
}
