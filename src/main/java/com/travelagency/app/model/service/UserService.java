package com.travelagency.app.model.service;

import com.travelagency.app.dao.UserDAO;
import com.travelagency.app.dao.impl.UserDAOImpl;
import com.travelagency.app.model.entity.User;
import com.travelagency.app.util.DataSourceConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {


    public boolean insertUser(User user) {
        Connection con = connect();
        UserDAO userDAO = UserDAOImpl.getInstance();
        userDAO.insertUser(con, user);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteUser(User user) {
        Connection con = connect();
        UserDAO userDAO = UserDAOImpl.getInstance();
        userDAO.deleteUser(con, user);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean updateUser(User user) {
        Connection con = connect();
        UserDAO userDAO = UserDAOImpl.getInstance();
        userDAO.updateUser(con, user);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public User getUserById(int userId) {
        Connection con = connect();
        UserDAO userDAO = UserDAOImpl.getInstance();
        User user = userDAO.getUserById(con, userId);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByLogin(String login) {
        Connection con = connect();
        UserDAO userDAO = UserDAOImpl.getInstance();
        User user = userDAO.getUserByLogin(con, login);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> findAllUsers() {
        Connection con = connect();
        UserDAO userDAO = UserDAOImpl.getInstance();
        List<User> users = userDAO.findAllUsers(con);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private Connection connect() {
        return  DataSourceConnection.getInstance().getConnection();
    }
}
