package com.travelagency.app.web.service;

import com.travelagency.app.model.entity.User;
import com.travelagency.app.web.service.exception.ServiceException;

import java.util.List;

public interface UserService {

    boolean insert(User user) throws ServiceException;

    boolean delete(User user) throws ServiceException;

    boolean update(User user) throws ServiceException;

    User getUserById(int userId) throws ServiceException;

    User getUserByLogin(String login) throws ServiceException;

    List<User> findAllUsers() throws ServiceException;
}
