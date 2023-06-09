package com.example.bravohealthpark.domain.medication.services;

import com.example.bravohealthpark.domain.medication.domain.dto.SaveMediInfoRequest;
import com.example.bravohealthpark.domain.medication.domain.dto.SaveMediInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MedicationInfoService {
    @POST("/medicationInfo/{userId}")
    Call<SaveMediInfoResponse> saveMedicationInfo(
            @Path("userId") String loginId,
            @Body SaveMediInfoRequest saveMediInfoRequest);
}
