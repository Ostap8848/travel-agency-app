package com.travelagency.app.web.service;

import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.model.entity.constant.Hotel;
import com.travelagency.app.model.entity.constant.TourType;
import com.travelagency.app.web.service.exception.ServiceException;

import java.util.List;

public interface TourService {

    boolean insert(Tour tour) throws ServiceException;

    boolean delete(Tour tour) throws ServiceException;

    boolean update(Tour tour) throws ServiceException;

    Tour getTourById(int tourId) throws ServiceException;

    Tour getTourByUkrName(String nameUkr) throws ServiceException;

    Tour getTourByEngName(String nameEng) throws ServiceException;

    List<Tour> getToursByType(TourType tourType, int offset) throws ServiceException;

    List<Tour> getToursByPrice(int offset) throws ServiceException;

    List<Tour> getToursByNumberOfPersons(int offset) throws ServiceException;

    List<Tour> getToursByHotelType(Hotel hotelType, int offset) throws ServiceException;

    List<Tour> getAllHotTours(int offset) throws ServiceException;

    List<Tour> findAllTours(int offset) throws ServiceException;
}
