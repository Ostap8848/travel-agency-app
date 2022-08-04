package com.travelagency.app.model.entity.constant;

public enum TourType {
    REST("rest"),

    EXCURSION("excursion"),

    SHOPPING("shopping");

    private final String tourTypeName;

    TourType(String tourTypeName) {
        this.tourTypeName = tourTypeName;
    }

    public String getTourTypeName() {
        return tourTypeName;
    }
}
