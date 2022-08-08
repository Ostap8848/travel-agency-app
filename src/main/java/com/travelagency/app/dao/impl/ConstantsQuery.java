package com.travelagency.app.dao.impl;

public class ConstantsQuery {

    public static final String INSERT_USER = "INSERT INTO users (first_name, last_name, email_login, password, instagram, phone_number, role, is_blocked) VALUES (?,?,?,?,?,?,?,?)";
    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    public static final String UPDATE_USER = "UPDATE users SET first_name = ?, last_name = ?, email_login = ?, password = ?, instagram = ?, phone_number = ?, role = ?, is_blocked = ? WHERE id = ?";
    public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE email_login = ?";
    public static final String FIND_ALL_USERS = "SELECT * FROM users";


    public static final String INSERT_TOUR = "INSERT INTO tours (tour_name_ukr, tour_name_eng, tour_type, tour_price, number_of_persons, hotel_type, is_tour_hot, discount) VALUES (?,?,?,?,?,?,?,?)";
    public static final String DELETE_TOUR = "DELETE FROM tours WHERE id = ?";
    public static final String UPDATE_TOUR = "UPDATE tours SET tour_name_ukr = ?, tour_name_eng = ?, tour_type = ?, tour_price = ?, number_of_persons = ?, hotel_type = ?, is_tour_hot = ?, discount = ? WHERE id = ?";
    public static final String GET_TOUR_BY_ID = "SELECT * FROM tours WHERE id = ?";
    public static final String GET_TOUR_BY_UKR_NAME = "SELECT * FROM tours WHERE tour_name_ukr = ?";
    public static final String GET_TOUR_BY_ENG_NAME = "SELECT * FROM tours WHERE tour_name_eng = ?";
    public static final String GET_TOURS_BY_TYPE = "SELECT * FROM tours WHERE tour_type = ? ORDER BY is_tour_hot DESC, id ASC";
    public static final String GET_TOURS_BY_PRICE = "SELECT * FROM tours WHERE tour_price = ? ORDER BY is_tour_hot DESC, tour_price ASC, id ASC";
    public static final String GET_TOURS_BY_NUMBER_OF_PERSONS = "SELECT * FROM tours WHERE number_of_persons = ? ORDER BY is_tour_hot DESC, number_of_persons ASC, id ASC";
    public static final String GET_TOURS_BY_HOTEL_TYPE = "SELECT * FROM tours WHERE hotel_type = ? ORDER BY is_tour_hot DESC, id ASC";
    public static final String GET_ALL_HOT_TOURS = "SELECT * FROM tours WHERE is_tour_hot = ? ORDER BY id ASC";
    public static final String FIND_ALL_TOURS = "SELECT * FROM tours ORDER BY is_tour_hot DESC, id ASC";


    public static final String INSERT_ORDER = "INSERT INTO orders (price, status, notes) VALUES (?,?,?)";
    public static final String DELETE_ORDER = "DELETE FROM orders WHERE id = ?";
    public static final String UPDATE_ORDER = "UPDATE orders SET price = ?, status = ?, notes = ? WHERE id = ?";
    public static final String GET_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
    public static final String GET_ORDERS_BY_TOUR_STATUS = "SELECT * FROM orders WHERE status = ?";
    public static final String FIND_ALL_ORDERS = "SELECT * FROM orders";
}
