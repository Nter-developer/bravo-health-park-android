package com.example.bravohealthpark.domain.medication.dto;

public class SendImageRequest {
    private String memo;

    public SendImageRequest(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return memo;
    }

    @Override
    public String toString() {
        return "SendImageRequest{" +
                "meme='" + memo + '\'' +
                '}';
    }
}
