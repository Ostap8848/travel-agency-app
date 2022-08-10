package com.travelagency.app.web.service.impl;

import com.travelagency.app.dao.UserDAO;
import com.travelagency.app.dao.impl.UserDAOImpl;
import com.travelagency.app.model.entity.User;
import com.travelagency.app.connection.DataSourceConnection;
import com.travelagency.app.web.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = UserDAOImpl.getInstance();

    public UserServiceImpl() {}

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean insert(User user) {
        Connection con = connect();
        userDAO.insertUser(con, user);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(User user) {
        Connection con = connect();
        userDAO.deleteUser(con, user);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        Connection con = connect();
        userDAO.updateUser(con, user);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public User getUserById(int userId) {
        Connection con = connect();
        User user = userDAO.getUserById(con, userId);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        Connection con = connect();
        User user = userDAO.getUserByLogin(con, login);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        Connection con = connect();
        List<User> users = userDAO.findAllUsers(con);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private Connection connect() {
        return DataSourceConnection.getInstance().getConnection();
    }
}