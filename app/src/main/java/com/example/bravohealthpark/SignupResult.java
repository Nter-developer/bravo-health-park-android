package com.example.bravohealthpark;

import java.util.Set;


public class SignupResult {

    private Set<AuthorityDto> authorityDtoSet;
    private String loginId;
    private String phoneNumber;
    private String username;

    @Override
    public String toString() {
        return "SignupResult{" +
                "authorityDtoSet=" + authorityDtoSet +
                ", loginId='" + loginId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
