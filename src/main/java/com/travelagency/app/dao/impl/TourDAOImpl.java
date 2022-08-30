package com.travelagency.app.dao.impl;

import com.travelagency.app.util.connection.DataSourceConnection;
import com.travelagency.app.dao.TourDAO;
import com.travelagency.app.dao.exception.DBException;
import com.travelagency.app.dao.mapper.TourMapper;
import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.model.entity.constant.Hotel;
import com.travelagency.app.model.entity.constant.TourType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class implements TourDAO interface and
 * implements all the methods needed to work with the database
 * Uses Singleton pattern
 */
public class TourDAOImpl implements TourDAO {
    static final Logger LOG = LogManager.getLogger(TourDAOImpl.class);

    private static TourDAOImpl instance;

    /**
     * Constructor is private
     */
    private TourDAOImpl() {
    }

    public static synchronized TourDAOImpl getInstance() {
        if (instance == null) {
            instance = new TourDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean insertTour(Tour tour) throws DBException {
        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.INSERT_TOUR)) {
            setTourParameters(tour, preparedStatement);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error("Failed to insert tour: ", e);
            throw new DBException(e);
        }
    }

    @Override
    public boolean deleteTour(Tour tour) throws DBException {
        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.DELETE_TOUR)) {
            preparedStatement.setInt(1, tour.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error("Failed to delete tour: ", e);
            throw new DBException(e);
        }
    }

    @Override
    public boolean updateTour(Tour tour) throws DBException {
        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.UPDATE_TOUR)) {
            setTourParameters(tour, preparedStatement);
            preparedStatement.setInt(10, tour.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error("Failed to update tour: ", e);
            throw new DBException(e);
        }
    }

    @Override
    public Tour getTourById(int tourId) throws DBException {
        Optional<Tour> optionalTour = Optional.empty();

        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.GET_TOUR_BY_ID)) {
            preparedStatement.setInt(1, tourId);
            ResultSet resultSet = preparedStatement.executeQuery();
            TourMapper tourMapper = new TourMapper();
            if (resultSet.next()) {
                optionalTour = Optional.ofNullable(tourMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Failed to get tour by id: ", e);
            throw new DBException(e);
        }
        return optionalTour.orElse(Tour.newTourBuilder().build());
    }

    @Override
    public Tour getTourByUkrName(String nameUkr) throws DBException {
        Optional<Tour> optionalTour = Optional.empty();

        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.GET_TOUR_BY_UKR_NAME)) {
            preparedStatement.setString(1, nameUkr);
            ResultSet resultSet = preparedStatement.executeQuery();
            TourMapper tourMapper = new TourMapper();
            if (resultSet.next()) {
                optionalTour = Optional.ofNullable(tourMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Failed to get tour by ukr name: ", e);
            throw new DBException(e);
        }
        return optionalTour.orElse(Tour.newTourBuilder().build());
    }

    @Override
    public Tour getTourByEngName(String nameEng) throws DBException {
        Optional<Tour> optionalTour = Optional.empty();

        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.GET_TOUR_BY_ENG_NAME)) {
            preparedStatement.setString(1, nameEng);
            ResultSet resultSet = preparedStatement.executeQuery();
            TourMapper tourMapper = new TourMapper();
            if (resultSet.next()) {
                optionalTour = Optional.ofNullable(tourMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Failed to get tour by eng name: ", e);
            throw new DBException(e);
        }
        return optionalTour.orElse(Tour.newTourBuilder().build());
    }

    @Override
    public List<Tour> getToursByType(TourType tourType, int offset) throws DBException {
        List<Tour> toursByType = new ArrayList<>();

        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.GET_TOURS_BY_TYPE)) {
            preparedStatement.setString(1, String.valueOf(tourType));
            preparedStatement.setInt(2, offset);
            getTourListExecute(toursByType, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Failed to get tours by type: ", e);
            throw new DBException(e);
        }
        return toursByType;
    }

    @Override
    public List<Tour> getToursByPrice(int offset) throws DBException {
        List<Tour> toursByPrice = new ArrayList<>();

        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.GET_TOURS_BY_PRICE)) {
            preparedStatement.setInt(1, offset);
            getTourListExecute(toursByPrice, preparedStatement);
        } catch (SQLException e) {
            LOG.error("Failed to get tours by price: ", e);
            throw new DBException(e);
        }
        return toursByPrice;
    }

    @Override
    public List<Tour> getToursByNumberOfPersons(int offset) throws DBException {
        List<Tour> toursByNumberOfPersons = new ArrayList<>();

        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.GET_TOURS_BY_NUMBER_OF_PERSONS)) {
            preparedStatement.setInt(1, offset);
            getTourListExecute(toursByNumberOfPersons, preparedStatement);
        } catch (SQLException e) {
            LOG.error("Failed to get tours by number of persons: ", e);
            throw new DBException(e);
        }
        return toursByNumberOfPersons;
    }

    @Override
    public List<Tour> getToursByHotelType(Hotel hotelType, int offset) throws DBException {
        List<Tour> toursByHotelType = new ArrayList<>();

        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.GET_TOURS_BY_HOTEL_TYPE)) {
            preparedStatement.setString(1, String.valueOf(hotelType));
            preparedStatement.setInt(2, offset);
            getTourListExecute(toursByHotelType, preparedStatement);
        } catch (SQLException e) {
            LOG.error("Failed to get tours by hotel type: ", e);
            throw new DBException(e);
        }
        return toursByHotelType;
    }

    @Override
    public List<Tour> getAllHotTours(int offset) throws DBException {
        List<Tour> hotTours = new ArrayList<>();

        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.GET_ALL_HOT_TOURS)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, offset);
            getTourListExecute(hotTours, preparedStatement);
        } catch (SQLException e) {
            LOG.error("Failed to get all hot tours: ", e);
            throw new DBException(e);
        }
        return hotTours;
    }

    @Override
    public List<Tour> findAllTours(int offset) throws DBException {
        List<Tour> tours = new ArrayList<>();

        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.FIND_ALL_TOURS)) {
            preparedStatement.setInt(1, offset);
            return getTourListExecute(tours, preparedStatement);
        } catch (SQLException e) {
            LOG.error("Failed to find all tours: ", e);
            throw new DBException(e);
        }
    }

    @Override
    public int getNumberOfRecords(){
        int totalCount = 0;
        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.NUMBER_OF_TOUR_RECORDS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            totalCount = resultSet.getInt(1);
            return totalCount;
        } catch (SQLException e) {
            LOG.error("Failed to find all tours: ", e);
        }
        return totalCount;
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
        preparedStatement.setString(9, tour.getDescription());
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



    private Connection connect() {
        return DataSourceConnection.getInstance().getConnection();
    }
    /*private Connection connect() {
        return DataBaseConnection.getInstance().getConnection();
    }*/
}
