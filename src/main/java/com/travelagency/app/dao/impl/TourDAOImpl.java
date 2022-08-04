package com.travelagency.app.dao.impl;

import com.travelagency.app.dao.TourDAO;
import com.travelagency.app.dao.mapper.TourMapper;
import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.util.DataBaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourDAOImpl implements TourDAO {
    static final Logger LOG = LogManager.getLogger(TourDAOImpl.class);

    private static TourDAOImpl instance;

    public static synchronized TourDAOImpl getInstance() {
        if (instance == null) {
            instance = new TourDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean insertTour(Tour tour) {
        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.INSERT_TOUR)) {
            setTourParameters(tour, preparedStatement);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error("Failed to insert tour: ", e);
        }
        return false;
    }

    @Override
    public boolean deleteTour(Tour tour) {
        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.DELETE_TOUR)) {
            preparedStatement.setInt(1, tour.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error("Failed to delete tour: ", e);
        }
        return false;
    }

    @Override
    public boolean updateTour(Tour tour) {
        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.UPDATE_TOUR)) {
            setTourParameters(tour, preparedStatement);
            preparedStatement.setInt(9, tour.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error("Failed to update tour: ", e);
        }
        return false;
    }

    @Override
    public Tour getTourById(int tourId) {
        Optional<Tour> optionalTour = Optional.empty();

        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_TOUR_BY_ID)) {
            preparedStatement.setInt(1, tourId);
            ResultSet resultSet = preparedStatement.executeQuery();
            TourMapper tourMapper = new TourMapper();
            if (resultSet.next()) {
                optionalTour = Optional.ofNullable(tourMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Failed to get tour by id: ", e);
        }
        return optionalTour.orElse(Tour.newTourBuilder().build());
    }

    @Override
    public Tour getTourByUkrName(String nameUkr) {
        Optional<Tour> optionalTour = Optional.empty();

        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_TOUR_BY_UKR_NAME)) {
            preparedStatement.setString(1, nameUkr);
            ResultSet resultSet = preparedStatement.executeQuery();
            TourMapper tourMapper = new TourMapper();
            if (resultSet.next()) {
                optionalTour = Optional.ofNullable(tourMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Failed to get tour by ukr name: ", e);
        }
        return optionalTour.orElse(Tour.newTourBuilder().build());
    }

    @Override
    public Tour getTourByEngName(String nameEng) {
        Optional<Tour> optionalTour = Optional.empty();

        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_TOUR_BY_ENG_NAME)) {
            preparedStatement.setString(1, nameEng);
            ResultSet resultSet = preparedStatement.executeQuery();
            TourMapper tourMapper = new TourMapper();
            if (resultSet.next()) {
                optionalTour = Optional.ofNullable(tourMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Failed to get tour by eng name: ", e);
        }
        return optionalTour.orElse(Tour.newTourBuilder().build());
    }

    @Override
    public List<Tour> getToursByType(Tour.TourType tourType) {
        List<Tour> toursByType = new ArrayList<>();

        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_TOURS_BY_TYPE)) {
            preparedStatement.setString(1, String.valueOf(tourType));
            getTourListExecute(toursByType, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Failed to get tours by type: ", e);
        }
        return toursByType;
    }

    @Override
    public List<Tour> getToursByPrice(BigDecimal price) {
        List<Tour> toursByPrice = new ArrayList<>();

        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_TOURS_BY_PRICE)) {
            preparedStatement.setBigDecimal(1, price);
            getTourListExecute(toursByPrice, preparedStatement);
        } catch (SQLException e) {
            LOG.error("Failed to get tours by price: ", e);
        }
        return toursByPrice;
    }

    @Override
    public List<Tour> getToursByNumberOfPersons(int numberOfPersons) {
        List<Tour> toursByNumberOfPersons = new ArrayList<>();

        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_TOURS_BY_NUMBER_OF_PERSONS)) {
            preparedStatement.setInt(1, numberOfPersons);
            getTourListExecute(toursByNumberOfPersons, preparedStatement);
        } catch (SQLException e) {
            LOG.error("Failed to get tours by number of persons: ", e);
        }
        return toursByNumberOfPersons;
    }

    @Override
    public List<Tour> getToursByHotelType(Tour.Hotel hotelType) {
        List<Tour> toursByHotelType = new ArrayList<>();

        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_TOURS_BY_HOTEL_TYPE)) {
            preparedStatement.setString(1, String.valueOf(hotelType));
            getTourListExecute(toursByHotelType, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Failed to get tours by hotel type: ", e);
        }
        return toursByHotelType;
    }

    @Override
    public List<Tour> getAllHotTours() {
        List<Tour> hotTours = new ArrayList<>();

        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_ALL_HOT_TOURS)) {
            preparedStatement.setBoolean(1, true);
            getTourListExecute(hotTours, preparedStatement);
        } catch (SQLException e) {
            LOG.error("Failed to get all hot tours: ", e);
        }
        return hotTours;
    }

    @Override
    public List<Tour> findAllTours() {
        List<Tour> tours = new ArrayList<>();

        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.FIND_ALL_TOURS)) {
            return getTourListExecute(tours, preparedStatement);
        } catch (SQLException e) {
            LOG.error("Failed to find all tours: ", e);
        }
        return tours;
    }

    private void setTourParameters(Tour tour, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, tour.getNameUkr());
        preparedStatement.setString(2, tour.getNameEng());
        preparedStatement.setString(3, tour.getTourType().getTourTypeName());
        preparedStatement.setBigDecimal(4, tour.getPrice());
        preparedStatement.setInt(5, tour.getNumberOfPersons());
        preparedStatement.setString(6, tour.getHotelTypeByStars().getHotelType());
        preparedStatement.setBoolean(7, tour.getIsTourHot());
        preparedStatement.setBigDecimal(8, tour.getDiscount());
    }

    private List<Tour> getTourListExecute(List<Tour> tours, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet1 = preparedStatement.executeQuery();

        TourMapper tourMapper = new TourMapper();

        while (resultSet1.next()) {
            Optional<Tour> tour = Optional.
                    ofNullable(tourMapper.extractFromResultSet(resultSet1));
            tour.ifPresent(tours::add);
        }
        return tours;
    }
}
