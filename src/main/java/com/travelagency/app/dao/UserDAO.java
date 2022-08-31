package com.travelagency.app.dao;

import com.travelagency.app.dao.exception.DBException;
import com.travelagency.app.model.entity.User;
import com.travelagency.app.model.entity.constant.Role;

import java.util.List;

public interface UserDAO {
    boolean insertUser(User user) throws DBException;

    boolean deleteUser(User user) throws DBException;

    boolean updateUser(User user) throws DBException;

    boolean updateUserRole(Role role, int userId) throws DBException;

    User getUserById(int userId) throws DBException;

    User getUserByLogin(String login) throws DBException;

    List<User> findAllUsers(int offset) throws DBException;

    int getNumberOfRecords();
}
