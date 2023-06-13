package com.example.bravohealthpark.domain.alarm.service;

import com.example.bravohealthpark.domain.alarm.dto.CreateAlarmRequest;
import com.example.bravohealthpark.domain.alarm.dto.CreateAlarmResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AlarmRetrofitService {
    @POST("/alarm/{loginId}")
    Call<CreateAlarmResponse> SendCreateAlarm(
            @Path("loginId") String loginId,
            @Body CreateAlarmRequest createAlarmRequest);
}
