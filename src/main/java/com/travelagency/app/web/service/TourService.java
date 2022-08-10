package com.travelagency.app.web.service;

import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.model.entity.constant.Hotel;
import com.travelagency.app.model.entity.constant.TourType;

import java.math.BigDecimal;
import java.util.List;

public interface TourService {

    boolean insert(Tour tour);

    boolean delete(Tour tour);

    boolean update(Tour tour);

    Tour getTourById(int tourId);

    Tour getTourByUkrName(String nameUkr);

    Tour getTourByEngName(String nameEng);

    List<Tour> getToursByType(TourType tourType);

    List<Tour> getToursByPrice(BigDecimal price);

    List<Tour> getToursByNumberOfPersons(int numberOfPersons);

    List<Tour> getToursByHotelType(Hotel hotelType);

    List<Tour> getAllHotTours();

    List<Tour> findAllTours();
}
