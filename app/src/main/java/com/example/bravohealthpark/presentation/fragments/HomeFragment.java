package com.example.bravohealthpark.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bravohealthpark.R;
import com.example.bravohealthpark.domain.user.dto.FindUserResponse;
import com.example.bravohealthpark.infra.preferences.SharedPreferenceBase;
import com.example.bravohealthpark.infra.preferences.UserPreferences;
import com.example.bravohealthpark.infra.retrofit.RetrofitClient;
import com.example.bravohealthpark.infra.retrofit.RetrofitService;
import com.example.bravohealthpark.presentation.activities.MedRegistrationActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RelativeLayout GoCalender;
    private TextView textViewHelloUser;
    private static Call<FindUserResponse> call;
    private static RetrofitService retrofitService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        textViewHelloUser = v.findViewById(R.id.textViewHelloUser);
        initRetrofitServiceAndCall();

        call.enqueue(new Callback<FindUserResponse>() {

            @Override
            public void onResponse(Call<FindUserResponse> call, Response<FindUserResponse> response) {
                if(response.isSuccessful()) {
                    SharedPreferenceBase.setSharedPreference(UserPreferences.SHARED_PREFERENCE_USER_NAME, response.body().getUsername());
                    textViewHelloUser.setText(SharedPreferenceBase.getSharedPreference(UserPreferences.SHARED_PREFERENCE_USER_NAME));
                }
            }

            @Override
            public void onFailure(Call<FindUserResponse> call, Throwable t) {

            }
        });

        GoCalender = v.findViewById(R.id.GoCalender_Btn);

        GoCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MedRegistrationActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    private void initRetrofitServiceAndCall() {
        retrofitService = RetrofitClient.getApiService(RetrofitService.class);
        call = retrofitService.sendFindMyUserRequest();
    }
}