package com.example.bravohealthpark.presentation.fragments;

import static com.example.bravohealthpark.infra.utils.ConvertUtils.convertBitmapToFile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.bravohealthpark.R;
import com.example.bravohealthpark.domain.medication.dto.SendImageResponse;
import com.example.bravohealthpark.domain.medication.services.MediInfoRetrofitService;
import com.example.bravohealthpark.infra.preferences.SharedPreferenceBase;
import com.example.bravohealthpark.infra.preferences.UserPreferences;
import com.example.bravohealthpark.infra.retrofit.RetrofitClient;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraFragment extends Fragment {

    private static final int REQUEST_MANAGE_EXTERNAL_STORAGE = 1001;
    private static Bitmap bitmapCapture;
    private Button captureButton;
    private ActivityResultLauncher<Intent> launcher;
    private CompletableFuture<Bitmap> future;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setupActivityResultLauncher();
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCameraActivity();
            }
        });
    }

    private void initCameraActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                requestManageExternalStoragePermission();
            }
            if (Environment.isExternalStorageManager()) {
                captureImage().thenAccept(bitmap -> {
                    bitmapCapture = bitmap;
                    initRetrofitServiceAndCall();
                });

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void requestManageExternalStoragePermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
        Uri uri = Uri.fromParts("package", requireActivity().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_MANAGE_EXTERNAL_STORAGE);
    }

    private CompletableFuture<Bitmap> captureImage() {
        launcher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        return future;
    }

    private void initComponents(View v) {
        captureButton = v.findViewById(R.id.capture_button);
    }

    private void initRetrofitServiceAndCall() {
        File file = convertBitmapToFile(bitmapCapture, getContext());

        RequestBody requestBody = RequestBody.create(file.getPath(), MediaType.parse("image/jpeg"));
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);
        MediInfoRetrofitService mediInfoRetrofitService = RetrofitClient.getApiService(MediInfoRetrofitService.class);
        RequestBody memo = RequestBody.create("memo_HI", MediaType.parse("text/plain"));
        String loginId = SharedPreferenceBase.getSharedPreference(UserPreferences.PREFERENCE_USER_LOGIN_ID);
        Call<List<SendImageResponse>> call = mediInfoRetrofitService.sendImageToPython(filePart, loginId, "");

        call.enqueue(new Callback<List<SendImageResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<SendImageResponse>> call, @NonNull Response<List<SendImageResponse>> response) {
            }

            @Override
            public void onFailure(Call<List<SendImageResponse>> call, Throwable t) {
            }
        });
    }

    private void setupActivityResultLauncher() {
        future = new CompletableFuture<>();
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                        Bundle extras = result.getData().getExtras();
                        if (extras != null) {
                            Bitmap bitmapCapture = (Bitmap) extras.get("data");
                            future.complete(bitmapCapture);
                        }
                    } else {
                        future.completeExceptionally(new RuntimeException("Camera capture failed"));
                    }
                }
        );
    }
}
