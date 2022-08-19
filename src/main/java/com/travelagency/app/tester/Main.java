package com.travelagency.app.tester;

import com.travelagency.app.dao.exception.DBException;
import com.travelagency.app.dao.impl.OrderDAOImpl;
import com.travelagency.app.dao.impl.TourDAOImpl;
import com.travelagency.app.dao.impl.UserDAOImpl;
import com.travelagency.app.model.entity.Order;
import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.model.entity.User;
import com.travelagency.app.model.entity.constant.Hotel;
import com.travelagency.app.model.entity.constant.Role;
import com.travelagency.app.model.entity.constant.Status;
import com.travelagency.app.model.entity.constant.TourType;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws DBException {
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        TourDAOImpl tourDAO = TourDAOImpl.getInstance();
        OrderDAOImpl orderDAO = OrderDAOImpl.getInstance();
        User user = User.newUserBuilder()
                .setId(1)
                .setFirstName("Mistar")
                .setLastName("Guk")
                .setLogin("gukgukisthere@gmail.com")
                .setPassword("johny&capral")
                .setInstagram("vietnamguy")
                .setPhoneNumber("+84-988-988-98")
                .setRole(Role.GUEST)
                .setIsBlocked(false).build();

        User user1 = User.newUserBuilder()
                .setId(9)
                .setFirstName("Mia")
                .setLastName("Gu")
                .setLogin("gukere@gmail.com")
                .setPassword("joaal")
                .setInstagram("viamguy")
                .setPhoneNumber("+84-9888-98")
                .setRole(Role.GUEST)
                .setIsBlocked(false).build();

        User updated = User.newUserBuilder()
                .setId(8)
                .setFirstName("up")
                .setLastName("up")
                .setLogin("up")
                .setPassword("up")
                .setInstagram("up")
                .setPhoneNumber("up")
                .setRole(Role.GUEST)
                .setIsBlocked(false).build();

        Tour tour = Tour.newTourBuilder()
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

        Tour tour1 = Tour.newTourBuilder()
                .setId(2)
                .setNameUkr("Сонячна Іспія")
                .setNameEng("Sny Spain")
                .setTourType(TourType.SHOPPING)
                .setPrice(BigDecimal.valueOf(89.99))
                .setNumberOfPersons(8)
                .setHotelTypeByStars(Hotel.ONE_STAR)
                .setIsTourHot(false)
                .setDiscount(BigDecimal.valueOf(25.00))
                .build();

        Tour tour2 = Tour.newTourBuilder()
                .setId(3)
                .setNameUkr("Іспанія")
                .setNameEng("Spain")
                .setTourType(TourType.REST)
                .setPrice(BigDecimal.valueOf(699.99))
                .setNumberOfPersons(2)
                .setHotelTypeByStars(Hotel.FIVE_STAR)
                .setIsTourHot(true)
                .setDiscount(BigDecimal.valueOf(25.00))
                .build();

        Tour tour3 = Tour.newTourBuilder()
                .setId(4)
                .setNameUkr("онячна Іспанія")
                .setNameEng("n")
                .setTourType(TourType.EXCURSION)
                .setPrice(BigDecimal.valueOf(8.99))
                .setNumberOfPersons(3)
                .setHotelTypeByStars(Hotel.THREE_STAR)
                .setIsTourHot(true)
                .setDiscount(BigDecimal.valueOf(25.00))
                .build();

        Tour tour4 = Tour.newTourBuilder()
                .setId(4)
                .setNameUkr("онячна Ісія")
                .setNameEng("ndfdgfdgfd")
                .setTourType(TourType.EXCURSION)
                .setPrice(BigDecimal.valueOf(1488.99))
                .setNumberOfPersons(8)
                .setHotelTypeByStars(Hotel.FOUR_STAR)
                .setIsTourHot(false)
                .setDiscount(BigDecimal.valueOf(25.00))
                .build();

        Order order3 = Order.newOrderBuilder()
                .setId(5)
                .setPrice(BigDecimal.valueOf(50.50))
                .setStatus(Status.CANCELED)
                .setNotes("djfkdf")
                .build();

        //userDAO.createDefaultUser();
        //userDAO.insertUser(user);
        //userDAO.updateUser(18, user1);
        //System.out.println("Success");

        //tourDAO.insertTour(tour1);
        //tourDAO.insertTour(tour2);
        //tourDAO.insertTour(tour3);
        //tourDAO.insertTour(tour);
        //tourDAO.insertTour(tour4);

        //System.out.println(userDAO.getUserById(14).toString());
        //System.out.println(userDAO.getUserByLogin("up").toString());
        //System.out.println(userDAO.findAllUsers().toString());
        //System.out.println(userDAO.deleteUserById(18));
        //System.out.println(userDAO.findAllUsers().toString());
        //System.out.println(userDAO.deleteUser(2));
        System.out.println(userDAO.findAllUsers().toString());
        //System.out.println(tourDAO.updateTour(7, tour4));
        //System.out.println(tourDAO.findAllTours().toString());
        //System.out.println(tourDAO.getAllHotTours().toString());
        //System.out.println(tourDAO.insertTour(tour4));

        //System.out.println(tourDAO.getTourByUkrName("Сонячна Іспанія").toString());
        //System.out.println(tourDAO.getTourByEngName("Sunny Spain").toString());
        //System.out.println(tourDAO.getToursByType(Tour.TourType.REST).toString());
        //System.out.println("*********************************");
        //System.out.println(tourDAO.getToursByHotelType(Tour.Hotel.FIVE_STAR).toString());
        //System.out.println("*********************************");
        //System.out.println(tourDAO.getToursByNumberOfPersons(8).toString());
        //System.out.println("*********************************");
        //System.out.println(tourDAO.getToursByPrice(BigDecimal.valueOf(8.99)).toString());
        //System.out.println(orderDAO.insertOrder(order3));
        //System.out.println(orderDAO.deleteOrder(4));

    }
}
