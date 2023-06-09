package com.example.bravohealthpark.domain.medicine.domain.dto;

import com.example.bravohealthpark.domain.user.State;

public class SaveMediInfoResponse {
    private int days;
    private String endDate;
    private String itemName;
    private int medicationInfoId;
    private String memo;
    private String startDate;
    private State state;
    private int tablet;
    private int times;

    public SaveMediInfoResponse(int days, String endDate, String itemName, int medicationInfoId, String memo, String startDate, State state, int tablets, int times) {
        this.days = days;
        this.endDate = endDate;
        this.itemName = itemName;
        this.medicationInfoId = medicationInfoId;
        this.memo = memo;
        this.startDate = startDate;
        this.state = state;
        this.tablet = tablets;
        this.times = times;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setMedicationInfoId(int medicationInfoId) {
        this.medicationInfoId = medicationInfoId;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setTablet(int tablet) {
        this.tablet = tablet;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
