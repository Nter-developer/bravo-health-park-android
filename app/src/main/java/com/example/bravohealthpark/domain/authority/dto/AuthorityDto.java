package com.example.bravohealthpark.domain.authority.dto;

public class AuthorityDto {
    private String authorityName;

    public AuthorityDto(String authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public String toString() {
        return "AuthorityDto{" +
                "authorityName='" + authorityName + '\'' +
                '}';
    }
}
