package com.travelagency.app.web.service.impl;

import com.travelagency.app.dao.UserDAO;
import com.travelagency.app.dao.exception.DBException;
import com.travelagency.app.dao.impl.UserDAOImpl;
import com.travelagency.app.model.entity.User;
import com.travelagency.app.web.service.UserService;
import com.travelagency.app.web.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = UserDAOImpl.getInstance();

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean insert(User user) throws ServiceException {
        try {
            return userDAO.insertUser(user);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(User user) throws ServiceException {
        try {
            return userDAO.deleteUser(user);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(User user) throws ServiceException {
        try {
            return userDAO.updateUser(user);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserById(int userId) throws ServiceException {
        try {
            return userDAO.getUserById(userId);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) throws ServiceException {
        try {
            return userDAO.getUserByLogin(login);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAllUsers(int offset) throws ServiceException {
        try {
            return userDAO.findAllUsers(countOffset(offset));
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void blockUser(User user) throws ServiceException {
        user.setBlocked(true);
        try {
            userDAO.updateUser(user);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void unblockUser(User user) throws ServiceException {
        user.setBlocked(false);
        try {
            userDAO.updateUser(user);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getNumberOfRecords() {
        return userDAO.getNumberOfRecords();
    }

    private int countOffset(int offset) {
        return offset * 10 - 10;
    }
}
