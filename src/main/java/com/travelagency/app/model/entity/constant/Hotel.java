package com.travelagency.app.model.entity.constant;

public enum Hotel {
    ONE_STAR("one_star"),

    TWO_STAR("two_star"),

    THREE_STAR("three_star"),

    FOUR_STAR("four_star"),

    FIVE_STAR("five_star");

    private final String hotelType;

    Hotel(String hotelType) {
        this.hotelType = hotelType;
    }

    public String getHotelType() {
        return hotelType;
    }
}
