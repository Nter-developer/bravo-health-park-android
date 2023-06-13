package com.example.bravohealthpark.domain.alarm.dto;

import java.util.List;

public class CreateAlarmRequest {
    private String meal;
    private List<String> times;

    public CreateAlarmRequest(String meal, List<String> times) {
        this.meal = meal;
        this.times = times;
    }

    public String getMeal() {
        return meal;
    }

    public List<String> getTimes() {
        return times;
    }
}
