package com.example.bravohealthpark.retrofit;

public class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
