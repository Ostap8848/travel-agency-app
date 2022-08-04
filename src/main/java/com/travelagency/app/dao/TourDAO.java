package com.travelagency.app.dao;

import com.travelagency.app.model.entity.Tour;

import java.math.BigDecimal;
import java.util.List;

public interface TourDAO {
    boolean insertTour(Tour tour);
    boolean deleteTour(Tour tour);
    boolean updateTour(Tour tour);
    Tour getTourById(int tourId);
    Tour getTourByUkrName(String nameUkr);
    Tour getTourByEngName(String nameEng);
    List<Tour> getToursByType(Tour.TourType tourType);
    List<Tour> getToursByPrice(BigDecimal price);
    List<Tour> getToursByNumberOfPersons(int numberOfPersons);
    List<Tour> getToursByHotelType(Tour.Hotel hotelType);
    List<Tour> getAllHotTours();
    List<Tour> findAllTours();
}
