package com.example.bravohealthpark.infra.retrofit;

import com.example.bravohealthpark.domain.user.dto.FindUserResponse;
import com.example.bravohealthpark.domain.user.dto.SignupResult;
import com.example.bravohealthpark.domain.user.dto.UserDto;
import com.example.bravohealthpark.domain.authority.dto.LoginDto;
import com.example.bravohealthpark.domain.authority.dto.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("/user/signup")
    Call<SignupResult> sendSignupRequest(@Body UserDto userDto);

    @POST("/user/login")
    Call<LoginResponse> sendLoginRequest(@Body LoginDto loginDto);

    @GET("/user/findUser")
    Call<FindUserResponse> sendFindMyUserRequest();
}
