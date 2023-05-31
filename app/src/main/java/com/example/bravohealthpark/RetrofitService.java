package com.example.bravohealthpark;

import com.example.bravohealthpark.SignupResult;
import com.example.bravohealthpark.UserDto;
import com.example.bravohealthpark.retrofit.LoginDto;
import com.example.bravohealthpark.retrofit.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("/user/signup")
    Call<SignupResult> sendSignupRequest(@Body UserDto userDto);

    @POST("/user/login")
    Call<LoginResponse> sendLoginRequest(@Body LoginDto loginDto);
}
