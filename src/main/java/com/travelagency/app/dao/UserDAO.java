package com.travelagency.app.dao;

import com.travelagency.app.model.entity.User;

import java.sql.Connection;
import java.util.List;

public interface UserDAO {
    boolean insertUser(Connection connection, User user);
    boolean deleteUser(Connection connection, User user);
    boolean updateUser(Connection connection, User user);
    User getUserById(Connection connection, int userId);
    User getUserByLogin(Connection connection, String login);
    List<User> findAllUsers(Connection connection);
}
