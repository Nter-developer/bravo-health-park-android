package com.example.bravohealthpark.infra.utils;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ConvertUtils {
    public static File convertBitmapToFile(Bitmap bitmap, Context context) {
        ensureFilesDirExists(context);
        String filePath = context.getFilesDir().getAbsolutePath() + File.separator + "imageFile.jpg";
        File file = new File(filePath);
        OutputStream outputStream = null;

        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static void ensureFilesDirExists(Context context) {
        if (!context.getFilesDir().exists()) {
            context.getFilesDir().mkdirs();
        }
    }
}
