package com.example.bravohealthpark.domain.alarm;

public class AlarmItem {
    private String time;
    private String mediName;
    private DoseState state;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMediName() {
        return mediName;
    }

    public void setMediName(String mediName) {
        this.mediName = mediName;
    }

    public DoseState getState() {
        return state;
    }

    public void setState(DoseState state) {
        this.state = state;
    }

    public AlarmItem(String time, String mediName, DoseState state) {
        this.time = time;
        this.mediName = mediName;
        this.state = state;
    }
}
