package com.example.bravohealthpark.domain.user;

public class UserEntity {
    private String loginId;
    private String phoneNumber;
    private String userName;

    public UserEntity(String loginId, String phoneNumber, String userName) {
        this.loginId = loginId;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "loginId='" + loginId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
