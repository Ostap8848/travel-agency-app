package com.travelagency.app.dao;

import com.travelagency.app.model.entity.User;

import java.util.List;

public interface UserDAO {
    boolean insertUser(User user);
    boolean deleteUserById(int userId);
    boolean updateUser(int userId, User user);
    User getUserById(int userId);
    User getUserByLogin(String login);
    List<User> findAllUsers();
}
