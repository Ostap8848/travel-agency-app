package com.travelagency.app.web.service;

import com.travelagency.app.model.entity.User;

import java.util.List;

public interface UserService {

    boolean insert(User user);

    boolean delete(User user);

    boolean update(User user);

    User getUserById(int userId);

    User getUserByLogin(String login);

    List<User> findAllUsers();
}
