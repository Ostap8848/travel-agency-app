package service_test;

import com.travelagency.app.dao.TourDAO;
import com.travelagency.app.dao.exception.DBException;
import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.model.entity.constant.Hotel;
import com.travelagency.app.model.entity.constant.TourType;
import com.travelagency.app.web.service.TourService;
import com.travelagency.app.web.service.exception.ServiceException;
import com.travelagency.app.web.service.impl.TourServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class TourServiceTest {

    TourDAO tourDaoForTest = new TourDAO() {
        @Override
        public boolean insertTour(Tour tour) throws DBException {
            return false;
        }

        @Override
        public boolean deleteTour(Tour tour) throws DBException {
            return false;
        }

        @Override
        public boolean updateTour(Tour tour) throws DBException {
            return false;
        }

        @Override
        public Tour getTourById(int tourId) throws DBException {
            return null;
        }

        @Override
        public Tour getTourByUkrName(String nameUkr) throws DBException {
            return null;
        }

        @Override
        public Tour getTourByEngName(String nameEng) throws DBException {
            return null;
        }

        @Override
        public List<Tour> getToursByType(TourType tourType, int offset) throws DBException {
            return null;
        }

        @Override
        public List<Tour> getToursByPrice(int offset) throws DBException {
            return null;
        }

        @Override
        public List<Tour> getToursByNumberOfPersons(int offset) throws DBException {
            return null;
        }

        @Override
        public List<Tour> getToursByHotelType(Hotel hotelType, int offset) throws DBException {
            return null;
        }

        @Override
        public List<Tour> getAllHotTours(int offset) throws DBException {
            return null;
        }

        @Override
        public List<Tour> findAllTours(int offset) throws DBException {
            return null;
        }

        @Override
        public int getNumberOfRecords() {
            return 0;
        }
    };

    TourService tourService = new TourServiceImpl(tourDaoForTest);

    @Test
    public void insert() {
        try {
            tourService.insert(new Tour());
        } catch (ServiceException e) {
            fail("Fail to insert Tour");
        }
    }

    @Test
    public void delete() {
        try {
            tourService.delete(new Tour());
        } catch (ServiceException e) {
            fail("Fail to delete Tour");
        }
    }

    @Test
    public void update() {
        try {
            tourService.delete(new Tour());
        } catch (ServiceException e) {
            fail("Fail to update Tour");
        }
    }

    @Test
    public void getTourById() {
        try {
            assertNull(tourService.getTourById(new Tour().getId()));
            assertNull(tourService.getTourById(7));
        } catch (ServiceException e) {
            fail("Fail to get Tour by id");
        }
    }

    @Test
    public void getTourByUkrName() {
        try {
            assertNull(tourService.getTourByUkrName(new Tour().getNameUkr()));
            assertNull(tourService.getTourByUkrName("Ім'я українською"));
        } catch (ServiceException e) {
            fail("Fail to get Tour by ukr name");
        }
    }

    @Test
    public void getTourByEngName() {
        try {
            assertNull(tourService.getTourByEngName(new Tour().getNameEng()));
            assertNull(tourService.getTourByEngName("English name"));
        } catch (ServiceException e) {
            fail("Fail to get Tour by eng name");
        }
    }

    @Test
    public void getToursByType() {
        try {
            assertNull(tourService.getToursByType(new Tour().getTourType(), 0));
            assertNull(tourService.getToursByType(new Tour().getTourType(), 10));
        } catch (ServiceException e) {
            fail("Fail to get Tour by type");
        }
    }

    @Test
    public void getToursByPrice() {
        try {
            assertNull(tourService.getToursByPrice(0));
            assertNull(tourService.getToursByPrice(10));
        } catch (ServiceException e) {
            fail("Fail to get Tour by price");
        }
    }

    @Test
    public void getToursByNumberOfPersons() {
        try {
            assertNull(tourService.getToursByNumberOfPersons(0));
            assertNull(tourService.getToursByNumberOfPersons(10));
        } catch (ServiceException e) {
            fail("Fail to get Tour by price");
        }
    }

    @Test
    public void getToursByHotelType() {
        try {
            assertNull(tourService.getToursByHotelType(new Tour().getHotelTypeByStars(), 0));
            assertNull(tourService.getToursByHotelType(new Tour().getHotelTypeByStars(), 10));
        } catch (ServiceException e) {
            fail("Fail to get Tour by price");
        }
    }

    @Test
    public void getAllHotTours() {
        try {
            assertNull(tourService.getAllHotTours(0));
            assertNull(tourService.getAllHotTours(10));
        } catch (ServiceException e) {
            fail("Fail to get Tour by price");
        }
    }

    @Test
    public void findAllTours() {
        try {
            assertNull(tourService.findAllTours(0));
            assertNull(tourService.findAllTours(10));
        } catch (ServiceException e) {
            fail("Fail to get Tour by price");
        }
    }
}
