package com.example.bravohealthpark.domain.medication;

public class State {
    private String name;

    public State(String authorityName) {
        this.name = authorityName;
    }

    public String getName() {
        return name;
    }
}