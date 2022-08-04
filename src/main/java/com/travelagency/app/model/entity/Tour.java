package com.travelagency.app.model.entity;

import com.travelagency.app.model.entity.constant.Hotel;
import com.travelagency.app.model.entity.constant.TourType;

import java.math.BigDecimal;
import java.util.List;

public class Tour {

    private int id;
    private String nameUkr;
    private String nameEng;
    private TourType tourType;
    private BigDecimal price;
    private int numberOfPersons;
    private Hotel hotelTypeByStars;
    private boolean isTourHot;
    private BigDecimal discount;
    private List<Tour> tours;

    public static TourBuilder newTourBuilder() {
        return new Tour().new TourBuilder();
    }

    public int getId() {
        return id;
    }

    public String getNameUkr() {
        return nameUkr;
    }

    public String getNameEng() {
        return nameEng;
    }

    public TourType getTourType() {
        return tourType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public Hotel getHotelTypeByStars() {
        return hotelTypeByStars;
    }

    public boolean getIsTourHot() {
        return isTourHot;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public List<Tour> getTours() {
        return tours;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", nameUkr='" + nameUkr + '\'' +
                ", nameEng='" + nameEng + '\'' +
                ", tourType=" + tourType +
                ", price=" + price +
                ", numberOfPersons=" + numberOfPersons +
                ", hotelTypeByStars=" + hotelTypeByStars +
                ", isTourHot=" + isTourHot +
                ", discount=" + discount +
                '}';
    }

    public class TourBuilder {

        public TourBuilder setId(int id) {
            Tour.this.id = id;
            return this;
        }

        public TourBuilder setNameUkr(String nameUkr) {
            Tour.this.nameUkr = nameUkr;
            return this;
        }

        public TourBuilder setNameEng(String nameEng) {
            Tour.this.nameEng = nameEng;
            return this;
        }

        public TourBuilder setTourType(TourType tourType) {
            Tour.this.tourType = tourType;
            return this;
        }

        public TourBuilder setPrice(BigDecimal price) {
            Tour.this.price = price;
            return this;
        }

        public TourBuilder setNumberOfPersons(int numberOfPersons) {
            Tour.this.numberOfPersons = numberOfPersons;
            return this;
        }

        public TourBuilder setHotelTypeByStars(Hotel hotelTypeByStars) {
            Tour.this.hotelTypeByStars = hotelTypeByStars;
            return this;
        }

        public TourBuilder setIsTourHot(boolean isTourHot) {
            Tour.this.isTourHot = isTourHot;
            return this;
        }

        public TourBuilder setDiscount(BigDecimal discount) {
            Tour.this.discount = discount;
            return this;
        }

        public TourBuilder setTours(List<Tour> tours) {
            Tour.this.tours = tours;
            return this;
        }

        public Tour build() {
            return Tour.this;
        }
    }
}
