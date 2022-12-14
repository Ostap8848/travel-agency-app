package com.travelagency.app.web.service;

import com.travelagency.app.model.entity.User;
import com.travelagency.app.model.entity.constant.Role;
import com.travelagency.app.web.service.exception.ServiceException;

import java.util.List;

public interface UserService {

    boolean insert(User user) throws ServiceException;

    boolean delete(User user) throws ServiceException;

    boolean update(User user) throws ServiceException;

    boolean updateUserRole(Role role, int userId) throws ServiceException;

    User getUserById(int userId) throws ServiceException;

    User getUserByLogin(String login) throws ServiceException;

    List<User> findAllUsers(int offset) throws ServiceException;

    int getNumberOfRecords();

    void blockUser(User user) throws ServiceException;

    void unblockUser(User user) throws ServiceException;
}
