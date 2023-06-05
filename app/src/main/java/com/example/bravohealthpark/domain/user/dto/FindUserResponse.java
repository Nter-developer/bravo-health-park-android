package com.example.bravohealthpark.domain.user.dto;

import com.example.bravohealthpark.domain.authority.dto.AuthorityDto;
import java.util.HashSet;
import java.util.Set;

public class FindUserResponse {
    public Set<AuthorityDto> getAuthorityDtoSet() {
        return authorityDtoSet;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    private Set<AuthorityDto> authorityDtoSet;
    private String loginId;
    private String phoneNumber;
    private String username;

    public FindUserResponse(HashSet<AuthorityDto> authorityDtoSet, String loginId, String phoneNumber, String username) {
        this.authorityDtoSet = authorityDtoSet;
        this.loginId = loginId;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
