package com.example.bravohealthpark.domain.medicine.domain.dto;

public class SaveMediInfoRequest {
    private int days;
    private String itemName;
    private int tablet;
    private int times;

    public SaveMediInfoRequest(int days, String itemName, int tablet, int times) {
        this.days = days;
        this.itemName = itemName;
        this.tablet = tablet;
        this.times = times;
    }
}
