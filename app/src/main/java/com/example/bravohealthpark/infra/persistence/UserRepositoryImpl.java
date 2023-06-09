package com.example.bravohealthpark.infra.persistence;

import static com.example.bravohealthpark.infra.preferences.SharedPreferenceBase.setSharedPreference;

import androidx.annotation.NonNull;

import com.example.bravohealthpark.domain.user.UserEntity;
import com.example.bravohealthpark.domain.user.UserRepository;
import com.example.bravohealthpark.domain.user.dto.FindUserResponse;
import com.example.bravohealthpark.domain.user.service.UserRetrofitService;
import com.example.bravohealthpark.infra.preferences.UserPreferences;
import com.example.bravohealthpark.infra.retrofit.RetrofitClient;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {
    private final UserRetrofitService userRetrofitService;

    public UserRepositoryImpl() {
        this.userRetrofitService = RetrofitClient.getApiService(UserRetrofitService.class);
    }

    @Override
    public CompletableFuture<UserEntity> find() {
        CompletableFuture<UserEntity> future = new CompletableFuture<>();

        Call<FindUserResponse> call = userRetrofitService.sendFindMyUserRequest();
        call.enqueue(new Callback<FindUserResponse>() {
            @Override
            public void onResponse(@NonNull Call<FindUserResponse> call, @NonNull Response<FindUserResponse> response) {
                if (response.isSuccessful()) {
                    FindUserResponse findUserResponse = response.body();
                    if (findUserResponse != null) {
                        setSharedPreference(UserPreferences.PREFERENCE_USER_NAME, findUserResponse.toEntity().getUserName());
                        future.complete(findUserResponse.toEntity());
                    } else {
                        future.completeExceptionally(new Exception("Response body is null"));
                    }
                } else {
                    future.completeExceptionally(new Exception("Response is not successful"));
                }
            }

            @Override
            public void onFailure(Call<FindUserResponse> call, Throwable t) {
                future.completeExceptionally(new Exception(t));
            }
        });

        return future;
    }

    @Override
    public void save(UserEntity user) {
        // 저장 로직 구현
    }

    @Override
    public void delete(String userId) {
        // 삭제 로직 구현
    }
}