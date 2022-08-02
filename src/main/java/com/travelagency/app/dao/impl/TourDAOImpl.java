package com.travelagency.app.dao.impl;

import com.travelagency.app.dao.TourDAO;
import com.travelagency.app.dao.mapper.TourMapper;
import com.travelagency.app.model.entity.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourDAOImpl implements TourDAO {
    static final Logger LOG = LogManager.getLogger(TourDAOImpl.class);

    private static final String FULL_URL = "jdbc:mysql://localhost:3306/travel-agency-db?user=root&password=admin";
    private static TourDAOImpl instance;

    public static synchronized TourDAOImpl getInstance() {
        if (instance == null) {
            instance = new TourDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean insertTour(Tour tour) {
        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO tours (tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, " +
                     "hotel_type, is_tour_hot, discount) VALUES (?,?,?,?,?,?,?,?)")) {
            setTourParameters(tour, preparedStatement);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteTour(int tourId) {
        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM tours WHERE id = ?")) {
            preparedStatement.setInt(1, tourId);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateTour(int tourId, Tour tour) {
        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement("UPDATE tours SET tour_name_ukr=?, tour_name_eng=?, tour_type=?, tour_price=?, number_of_persons=?, " +
                     "hotel_type=?, is_tour_hot=?, discount=? WHERE id=?")){
            setTourParameters(tour, preparedStatement);
            preparedStatement.setInt(9, tourId);
                return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return false;
    }

    @Override
    public Tour getTourById(int tourId) {
        Optional<Tour> optionalTour = Optional.empty();

        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM tours WHERE id = ?");) {
            preparedStatement.setInt(1, tourId);
            ResultSet resultSet = preparedStatement.executeQuery();
            TourMapper tourMapper = new TourMapper();
            if (resultSet.next()) {
                optionalTour = Optional.ofNullable(tourMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return optionalTour.orElse(Tour.newTourBuilder().build());
    }

    @Override
    public Tour getTourByUkrName(String nameUkr) {
        Optional<Tour> optionalTour = Optional.empty();

        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM tours WHERE tour_name_ukr = ?")) {
            preparedStatement.setString(1, nameUkr);
            ResultSet resultSet = preparedStatement.executeQuery();
            TourMapper tourMapper = new TourMapper();
            if (resultSet.next()) {
                optionalTour = Optional.ofNullable(tourMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return optionalTour.orElse(Tour.newTourBuilder().build());
    }

    @Override
    public Tour getTourByEngName(String nameEng) {
        Optional<Tour> optionalTour = Optional.empty();

        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM tours WHERE tour_name_eng = ?")) {
            preparedStatement.setString(1, nameEng);
            ResultSet resultSet = preparedStatement.executeQuery();
            TourMapper tourMapper = new TourMapper();
            if (resultSet.next()) {
                optionalTour = Optional.ofNullable(tourMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return optionalTour.orElse(Tour.newTourBuilder().build());
    }

    @Override
    public List<Tour> getToursByType(Tour.TourType tourType) {
        List<Tour> toursByType = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(FULL_URL);
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM tours WHERE tour_type = ? " +
                    "ORDER BY is_tour_hot DESC, id ASC")){
            preparedStatement.setString(1, String.valueOf(tourType));
            getTourListExecute(toursByType, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return toursByType;
    }

    @Override
    public List<Tour> getToursByPrice(BigDecimal price) {
        List<Tour> toursByPrice = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(FULL_URL);
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM tours WHERE tour_price = ? " +
                    "ORDER BY is_tour_hot DESC, tour_price ASC, id ASC ")){
            preparedStatement.setBigDecimal(1, price);
            getTourListExecute(toursByPrice, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return toursByPrice;
    }

    @Override
    public List<Tour> getToursByNumberOfPersons(int numberOfPersons) {
        List<Tour> toursByNumberOfPersons = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(FULL_URL);
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM tours WHERE number_of_persons = ? " +
                    "ORDER BY is_tour_hot DESC, number_of_persons ASC, id ASC ")){
            preparedStatement.setInt(1, numberOfPersons);
            getTourListExecute(toursByNumberOfPersons, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return toursByNumberOfPersons;
    }

    @Override
    public List<Tour> getToursByHotelType(Tour.Hotel hotelType) {
        List<Tour> toursByHotelType = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(FULL_URL);
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM tours WHERE hotel_type = ? " +
                    "ORDER BY is_tour_hot DESC, id ASC")){
            preparedStatement.setString(1, String.valueOf(hotelType));
            getTourListExecute(toursByHotelType, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return toursByHotelType;
    }

    @Override
    public List<Tour> getAllTours() {
        List<Tour> tours = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement(" SELECT * FROM tours ORDER BY is_tour_hot DESC, id ASC")) {
            return getTourListExecute(tours, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
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
