package com.example.bravohealthpark;

import com.example.bravohealthpark.SignupResult;
import com.example.bravohealthpark.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("/user/signup")
    Call<SignupResult> sendSignupRequest(@Body UserDto userDto);
}
