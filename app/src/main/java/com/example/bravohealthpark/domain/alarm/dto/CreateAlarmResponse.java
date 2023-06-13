package com.example.bravohealthpark.domain.alarm.dto;

import com.example.bravohealthpark.domain.alarm.AlarmTime;

public class CreateAlarmResponse {
    private String alarmStatus;
    private String date;
    private String meal;
    private AlarmTime time;
    private String title;

    public CreateAlarmResponse(String alarmStatus, String date, String meal, AlarmTime time, String title) {
        this.alarmStatus = alarmStatus;
        this.date = date;
        this.meal = meal;
        this.time = time;
        this.title = title;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public String getDate() {
        return date;
    }

    public String getMeal() {
        return meal;
    }

    public AlarmTime getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }
}