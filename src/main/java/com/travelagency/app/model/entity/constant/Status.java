package com.travelagency.app.model.entity.constant;

public enum Status {
    REGISTERED("registered"),

    PAID("paid"),

    CANCELED("canceled");

    private final String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
