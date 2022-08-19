package com.travelagency.app.web.service.impl;

import com.travelagency.app.dao.TourDAO;
import com.travelagency.app.dao.exception.DBException;
import com.travelagency.app.dao.impl.TourDAOImpl;
import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.model.entity.constant.Hotel;
import com.travelagency.app.model.entity.constant.TourType;
import com.travelagency.app.web.service.TourService;
import com.travelagency.app.web.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public class TourServiceImpl implements TourService {

    private TourDAO tourDAO = TourDAOImpl.getInstance();

    public TourServiceImpl() {
    }

    public TourServiceImpl(TourDAO tourDAO) {
        this.tourDAO = tourDAO;
    }

    @Override
    public boolean insert(Tour tour) throws ServiceException {
        try {
            return tourDAO.insertTour(tour);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Tour tour) throws ServiceException {
        try {
            return tourDAO.deleteTour(tour);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Tour tour) throws ServiceException {
        try {
            return tourDAO.updateTour(tour);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Tour getTourById(int tourId) throws ServiceException {
        try {
            return tourDAO.getTourById(tourId);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Tour getTourByUkrName(String nameUkr) throws ServiceException {
        try {
            return tourDAO.getTourByUkrName(nameUkr);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Tour getTourByEngName(String nameEng) throws ServiceException {
        try {
            return tourDAO.getTourByEngName(nameEng);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tour> getToursByType(TourType tourType) throws ServiceException {
        try {
            return tourDAO.getToursByType(tourType);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tour> getToursByPrice(BigDecimal price) throws ServiceException {
        try {
            return tourDAO.getToursByPrice(price);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tour> getToursByNumberOfPersons(int numberOfPersons) throws ServiceException {
        try {
            return tourDAO.getToursByNumberOfPersons(numberOfPersons);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tour> getToursByHotelType(Hotel hotelType) throws ServiceException {
        try {
            return tourDAO.getToursByHotelType(hotelType);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tour> getAllHotTours() throws ServiceException {
        try {
            return tourDAO.getAllHotTours();
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tour> findAllTours(int offset) throws ServiceException {
        try {
            return tourDAO.findAllTours(offset);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }
}
