package com.example.bravohealthpark.domain.user;

import java.math.BigInteger;

public class UserEntity {
    private long userId;
    private boolean activated;
    private String loginId;
    private String phoneNumber;
    private String userName;

    public UserEntity(long userId, boolean activated, String loginId, String phoneNumber, String userName) {
        this.userId = userId;
        this.activated = activated;
        this.loginId = loginId;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
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
}
