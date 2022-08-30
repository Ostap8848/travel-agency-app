package com.travelagency.app.dao.impl;

import com.travelagency.app.util.connection.DataSourceConnection;
import com.travelagency.app.dao.exception.DBException;
import com.travelagency.app.dao.UserDAO;
import com.travelagency.app.dao.mapper.UserMapper;
import com.travelagency.app.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class implements UserDAO interface and
 * implements all the methods needed to work with the database
 * Uses Singleton pattern
 */
public class UserDAOImpl implements UserDAO {
    static final Logger LOG = LogManager.getLogger(UserDAOImpl.class);

    private static UserDAOImpl instance;

    /**
     * Constructor is private
     */
    private UserDAOImpl() {
    }

    public static synchronized UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean insertUser(User user) throws DBException {
        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.INSERT_USER)) {
            setUserParameters(user, preparedStatement);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error("Failed to insert user: ", e);
            throw new DBException(e);
        }
    }

    @Override
    public boolean deleteUser(User user) throws DBException {
        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.DELETE_USER)) {
            preparedStatement.setInt(1, user.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error("Failed to delete user: ", e);
            throw new DBException(e);
        }
    }

    @Override
    public boolean updateUser(User user) throws DBException {
        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.UPDATE_USER)) {
            setUserParameters(user, preparedStatement);
            preparedStatement.setInt(9, user.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Failed to update user: ", e);
            throw new DBException(e);
        }
    }


    @Override
    public User getUserById(int userId) throws DBException {
        Optional<User> optionalUser = Optional.empty();

        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.GET_USER_BY_ID)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (resultSet.next()) {
                optionalUser = Optional.ofNullable(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Failed to get user by id: ", e);
            throw new DBException(e);
        }
        return optionalUser.orElse(User.newUserBuilder().build());
    }

    @Override
    public User getUserByLogin(String login) throws DBException {
        Optional<User> optionalUser = Optional.empty();

        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.GET_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (resultSet.next()) {
                optionalUser = Optional.ofNullable(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Failed to get user by login: ", e);
            throw new DBException(e);
        }
        return optionalUser.orElse(User.newUserBuilder().build());
    }

    @Override
    public List<User> findAllUsers(int offset) throws DBException {
        List<User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.FIND_ALL_USERS)) {
            preparedStatement.setInt(1, offset);
            return getUserListExecute(users, preparedStatement);
        } catch (SQLException e) {
            LOG.error("Failed to find all users: ", e);
            throw new DBException(e);
        }
    }

    @Override
    public int getNumberOfRecords(){
        int totalCount = 0;
        try (PreparedStatement preparedStatement = connect().prepareStatement(ConstantsQuery.NUMBER_OF_USER_RECORDS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            totalCount = resultSet.getInt(1);
            return totalCount;
        } catch (SQLException e) {
            LOG.error("Failed to find all users: ", e);
        }
        return totalCount;
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

    private Connection connect() {
        return DataSourceConnection.getInstance().getConnection();
    }

    /*private Connection connect() {
        return DataBaseConnection.getInstance().getConnection();
    }*/
}
