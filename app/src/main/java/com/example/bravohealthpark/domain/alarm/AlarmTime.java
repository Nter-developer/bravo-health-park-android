package com.example.bravohealthpark.domain.alarm;

public class AlarmTime {
    private int hour;
    private int minute;
    private int nano;
    private int second;

    public AlarmTime(int hour, int minute, int nano, int second) {
        this.hour = hour;
        this.minute = minute;
        this.nano = nano;
        this.second = second;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setNano(int nano) {
        this.nano = nano;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
