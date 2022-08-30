package com.travelagency.app.model;

import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.model.entity.constant.Hotel;
import com.travelagency.app.model.entity.constant.TourType;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;

public class TourTest {
    Tour tour;

    @Test
    public void tourBuilderTest() {
        tour = Tour.newTourBuilder()
                .setId(1)
                .setNameUkr("Сонячна Іспанія")
                .setNameEng("Sunny Spain")
                .setTourType(TourType.REST)
                .setPrice(BigDecimal.valueOf(899.99))
                .setNumberOfPersons(6)
                .setHotelTypeByStars(Hotel.FIVE_STAR)
                .setIsTourHot(true)
                .setDiscount(BigDecimal.valueOf(10.00))
                .setDescription("Start date - '18.08.22', finish date - '18.09.22'")
                .build();
        assertNotNull(tour);
        assertEquals(1, tour.getId());
        assertEquals("Сонячна Іспанія", tour.getNameUkr());
        assertEquals("Sunny Spain", tour.getNameEng());
        assertEquals(TourType.REST, tour.getTourType());
        assertEquals(BigDecimal.valueOf(899.99), tour.getPrice());
        assertEquals(6, tour.getNumberOfPersons());
        assertEquals(Hotel.FIVE_STAR, tour.getHotelTypeByStars());
        assertTrue(tour.getIsTourHot());
        assertEquals(BigDecimal.valueOf(10.00), tour.getDiscount());
        assertEquals("Start date - '18.08.22', finish date - '18.09.22'", tour.getDescription());
    }
}
