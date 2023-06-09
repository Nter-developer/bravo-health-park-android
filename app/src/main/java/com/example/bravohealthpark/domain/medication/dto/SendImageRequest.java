package com.example.bravohealthpark.domain.medication.dto;

public class SendImageRequest {
    private String meme;

    public SendImageRequest(String meme) {
        this.meme = meme;
    }

    public String getMeme() {
        return meme;
    }

    @Override
    public String toString() {
        return "SendImageRequest{" +
                "meme='" + meme + '\'' +
                '}';
    }
}
