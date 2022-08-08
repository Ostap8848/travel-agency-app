package com.travelagency.app.dao;

import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.model.entity.constant.Hotel;
import com.travelagency.app.model.entity.constant.TourType;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public interface TourDAO {
    boolean insertTour(Connection connection, Tour tour);
    boolean deleteTour(Connection connection, Tour tour);
    boolean updateTour(Connection connection, Tour tour);
    Tour getTourById(Connection connection, int tourId);
    Tour getTourByUkrName(Connection connection, String nameUkr);
    Tour getTourByEngName(Connection connection, String nameEng);
    List<Tour> getToursByType(Connection connection, TourType tourType);
    List<Tour> getToursByPrice(Connection connection, BigDecimal price);
    List<Tour> getToursByNumberOfPersons(Connection connection, int numberOfPersons);
    List<Tour> getToursByHotelType(Connection connection, Hotel hotelType);
    List<Tour> getAllHotTours(Connection connection);
    List<Tour> findAllTours(Connection connection);
}
