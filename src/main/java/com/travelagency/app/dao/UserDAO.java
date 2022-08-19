package com.travelagency.app.dao;

import com.travelagency.app.dao.exception.DBException;
import com.travelagency.app.model.entity.User;

import java.util.List;

public interface UserDAO {
    boolean insertUser(User user) throws DBException;

    boolean deleteUser(User user) throws DBException;

    boolean updateUser(User user) throws DBException;

    User getUserById(int userId) throws DBException;

    User getUserByLogin(String login) throws DBException;

    List<User> findAllUsers() throws DBException;
}
