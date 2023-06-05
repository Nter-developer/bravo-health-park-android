package com.example.bravohealthpark.domain.authority.dto;

public class LoginDto {

    private String loginId;
    private String phoneNumber;

    public LoginDto(String loginId, String phoneNumber) {
        this.loginId = loginId;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "loginId='" + loginId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
