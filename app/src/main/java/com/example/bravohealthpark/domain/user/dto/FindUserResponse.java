package com.example.bravohealthpark.domain.user.dto;

import com.example.bravohealthpark.domain.authority.dto.AuthorityDto;
import com.example.bravohealthpark.domain.user.UserEntity;

import java.util.List;

public class FindUserResponse {

    private String loginId;
    private String username;
    private List<AuthorityDto> authorityDtoSet;

    public FindUserResponse(String loginId, String username, List<AuthorityDto> authorityDtoSet) {
        this.loginId = loginId;
        this.username = username;
        this.authorityDtoSet = authorityDtoSet;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getUsername() {
        return username;
    }

    public List<AuthorityDto> getAuthorityDtoSet() {
        return authorityDtoSet;
    }

    public UserEntity toEntity() {
        return new UserEntity(this.loginId, "01043329542", this.username);
    }

    @Override
    public String toString() {
        return "FindUserResponse{" +
                "loginId='" + loginId + '\'' +
                ", username='" + username + '\'' +
                ", authorityDtoSet=" + authorityDtoSet +
                '}';
    }
}
