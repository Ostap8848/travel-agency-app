package com.travelagency.app.dao.impl;

import com.travelagency.app.dao.UserDAO;
import com.travelagency.app.dao.mapper.UserMapper;
import com.travelagency.app.model.entity.User;
import com.travelagency.app.util.DataBaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    static final Logger LOG = LogManager.getLogger(UserDAOImpl.class);

    private static UserDAOImpl instance;

    public static synchronized UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean insertUser(User user) {
        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.INSERT_USER)) {
            setUserParameters(user, preparedStatement);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error("Failed to insert user: ", e);
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.DELETE_USER)) {
            preparedStatement.setInt(1, user.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error("Failed to delete user: ", e);
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.UPDATE_USER)) {
            setUserParameters(user, preparedStatement);
            preparedStatement.setInt(9, user.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Failed to update user: ", e);
        }
        return false;
    }

    @Override
    public User getUserById(int userId) {
        Optional<User> optionalUser = Optional.empty();

        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_USER_BY_ID);) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (resultSet.next()) {
                optionalUser = Optional.ofNullable(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Failed to get user by id: ", e);
        }
        return optionalUser.orElse(User.newUserBuilder().build());
    }

    @Override
    public User getUserByLogin(String login) {
        Optional<User> optionalUser = Optional.empty();

        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (resultSet.next()) {
                optionalUser = Optional.ofNullable(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Failed to get user by login: ", e);
        }
        return optionalUser.orElse(User.newUserBuilder().build());
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.FIND_ALL_USERS)) {
            return getUserListExecute(users, preparedStatement);
        } catch (SQLException e) {
            LOG.error("Failed to find all users: ", e);
        }
        return users;
    }

    private void setUserParameters(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getLogin());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getInstagram());
        preparedStatement.setString(6, user.getPhoneNumber());
        preparedStatement.setString(7, user.getRole().getRoleName());
        preparedStatement.setBoolean(8, user.getIsBlocked());
    }

    private List<User> getUserListExecute(List<User> users, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet1 = preparedStatement.executeQuery();
        UserMapper userMapper = new UserMapper();
        while (resultSet1.next()) {
            Optional<User> user = Optional.
                    ofNullable(userMapper.extractFromResultSet(resultSet1));
            user.ifPresent(users::add);
        }
        return users;
    }
}
