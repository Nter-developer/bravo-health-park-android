package com.example.bravohealthpark.domain.alarm.service;

import com.example.bravohealthpark.domain.alarm.dto.CreateAlarmResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AlarmRetrofitService {
    @POST("/alarm/{loginId}")
    Call<List<CreateAlarmResponse>> SendCreateAlarm(
            @Path("loginId") String loginId,
            @Query("meal") String meal,
            @Query("times") List<String> times);
}
