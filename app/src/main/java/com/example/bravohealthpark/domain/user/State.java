package com.example.bravohealthpark.domain.user;

public class State {
    private String authorityName;

    public State(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityName() {
        return authorityName;
    }
}