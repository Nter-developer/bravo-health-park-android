package com.example.bravohealthpark.domain.medicine.domain.dto;

import java.util.List;

public class SaveMediInfoResponse {
    private int days;
    private String endDate;
    private String itemName;
    private int medicationInfoId;
    private String memo;
    private String startDate;
    private List<String> state;
    private int tablets;
    private int times;

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

    public void setState(List<String> state) {
        this.state = state;
    }

    public void setTablets(int tablets) {
        this.tablets = tablets;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
