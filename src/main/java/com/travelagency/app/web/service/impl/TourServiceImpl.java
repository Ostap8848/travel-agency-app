package com.travelagency.app.web.service.impl;

import com.travelagency.app.dao.TourDAO;
import com.travelagency.app.dao.impl.TourDAOImpl;
import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.model.entity.constant.Hotel;
import com.travelagency.app.model.entity.constant.TourType;
import com.travelagency.app.connection.DataSourceConnection;
import com.travelagency.app.web.service.TourService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TourServiceImpl implements TourService {

    private TourDAO tourDAO = TourDAOImpl.getInstance();

    public TourServiceImpl() {}

    public TourServiceImpl(TourDAO tourDAO) {
        this.tourDAO = tourDAO;
    }

    @Override
    public boolean insert(Tour tour) {
        Connection con = connect();
        tourDAO.insertTour(con, tour);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(Tour tour) {
        Connection con = connect();
        tourDAO.deleteTour(con, tour);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Tour tour) {
        Connection con = connect();
        tourDAO.updateTour(con, tour);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Tour getTourById(int tourId) {
        Connection con = connect();
        Tour tour = tourDAO.getTourById(con, tourId);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tour;
    }

    @Override
    public Tour getTourByUkrName(String nameUkr) {
        Connection con = connect();
        Tour tour = tourDAO.getTourByUkrName(con, nameUkr);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tour;
    }

    @Override
    public Tour getTourByEngName(String nameEng) {
        Connection con = connect();
        Tour tour = tourDAO.getTourByEngName(con, nameEng);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tour;
    }

    @Override
    public List<Tour> getToursByType(TourType tourType) {
        Connection con = connect();
        List<Tour> tours = tourDAO.getToursByType(con, tourType);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
    }

    @Override
    public List<Tour> getToursByPrice(BigDecimal price) {
        Connection con = connect();
        List<Tour> tours = tourDAO.getToursByPrice(con, price);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
    }

    @Override
    public List<Tour> getToursByNumberOfPersons(int numberOfPersons) {
        Connection con = connect();
        List<Tour> tours = tourDAO.getToursByNumberOfPersons(con, numberOfPersons);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
    }

    @Override
    public List<Tour> getToursByHotelType(Hotel hotelType) {
        Connection con = connect();
        List<Tour> tours = tourDAO.getToursByHotelType(con, hotelType);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
    }

    @Override
    public List<Tour> getAllHotTours() {
        Connection con = connect();
        List<Tour> tours = tourDAO.getAllHotTours(con);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
    }

    @Override
    public List<Tour> findAllTours() {
        Connection con = connect();
        List<Tour> tours = tourDAO.findAllTours(con);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
    }

    private Connection connect() {
        return  DataSourceConnection.getInstance().getConnection();
    }
}