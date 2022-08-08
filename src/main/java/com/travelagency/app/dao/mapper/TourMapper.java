package com.travelagency.app.dao.mapper;

import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.model.entity.constant.Hotel;
import com.travelagency.app.model.entity.constant.TourType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TourMapper implements ObjectMapper<Tour> {
    @Override
    public Tour extractFromResultSet(ResultSet resultSet) throws SQLException {
        Map<Integer, Tour> tours = new HashMap<>();
        Tour tour = Tour.newTourBuilder()
                .setId(resultSet.getInt("id"))
                .setNameUkr(resultSet.getString("tour_name_ukr"))
                .setNameEng(resultSet.getString("tour_name_eng"))
                .setTourType(TourType.valueOf(resultSet.getString("tour_type")))
                .setPrice(resultSet.getBigDecimal("tour_price"))
                .setNumberOfPersons(resultSet.getInt("number_of_persons"))
                .setHotelTypeByStars(Hotel.valueOf(resultSet.getString("hotel_type")))
                .setIsTourHot(resultSet.getBoolean("is_tour_hot"))
                .setDiscount(resultSet.getBigDecimal("discount"))
                .build();
        tours.put(tour.getId(), tour);
        tour = this.makeUnique(tours, tour);
        return tour;
    }

    @Override
    public Tour makeUnique(Map<Integer, Tour> cache, Tour tour) {
        cache.putIfAbsent(tour.getId(), tour);
        return cache.get(tour.getId());
    }
}
