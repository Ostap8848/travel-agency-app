package com.travelagency.app.dao;

import com.travelagency.app.model.entity.User;

import java.util.List;

public interface UserDAO {
    boolean insertUser(User user);
    boolean deleteUser(User user);
    boolean updateUser(User user);
    User getUserById(int userId);
    User getUserByLogin(String login);
    List<User> findAllUsers();
}
