package com.example.bravohealthpark.domain.medication.services;

import com.example.bravohealthpark.domain.medication.dto.SaveMediInfoRequest;
import com.example.bravohealthpark.domain.medication.dto.SaveMediInfoResponse;
import com.example.bravohealthpark.domain.medication.dto.SendImageResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface MediInfoRetrofitService {
    @POST("/medicationInfo/{loginId}")
    Call<SaveMediInfoResponse> saveMedicationInfo(
            @Path("loginId") String loginId,
            @Body SaveMediInfoRequest saveMediInfoRequest);

    @Multipart
    @POST("/medicationInfo/image/{loginId}")
    Call<List<SendImageResponse>> sendImageToPython(
            @Part MultipartBody.Part imageFile,
            @Path("loginId") String loginId,
            @Part("memo") String memo);
}
