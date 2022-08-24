package com.travelagency.app.dao;

import com.travelagency.app.dao.exception.DBException;
import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.model.entity.constant.Hotel;
import com.travelagency.app.model.entity.constant.TourType;

import java.math.BigDecimal;
import java.util.List;

public interface TourDAO {
    boolean insertTour(Tour tour) throws DBException;

    boolean deleteTour(Tour tour) throws DBException;

    boolean updateTour(Tour tour) throws DBException;

    Tour getTourById(int tourId) throws DBException;

    Tour getTourByUkrName(String nameUkr) throws DBException;

    Tour getTourByEngName(String nameEng) throws DBException;

    List<Tour> getToursByType(TourType tourType, int offset) throws DBException;

    List<Tour> getToursByPrice(int offset) throws DBException;

    List<Tour> getToursByNumberOfPersons(int offset) throws DBException;

    List<Tour> getToursByHotelType(Hotel hotelType, int offset) throws DBException;

    List<Tour> getAllHotTours(int offset) throws DBException;

    List<Tour> findAllTours(int offset) throws DBException;

    int getNumberOfRecords();
}
