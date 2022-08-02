package com.travelagency.app.dao.impl;

import com.travelagency.app.dao.UserDAO;
import com.travelagency.app.dao.mapper.UserMapper;
import com.travelagency.app.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UserDAOImpl implements UserDAO {
    static final Logger LOG = LogManager.getLogger(UserDAOImpl.class);


    //private static final String CONNECTION_URL = getUrl("resources\\db\\travel-agency-db.properties");
    private static final String FULL_URL = "jdbc:mysql://localhost:3306/travel-agency-db?user=root&password=admin";
    private static UserDAOImpl instance;

    public static synchronized UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    private static String getUrl(String path) {
        String resultURL = "";
        try (InputStream fis = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(fis);
            resultURL = properties.getProperty("connection.url");
        } catch (IOException exception) {
            exception.printStackTrace();
            LOG.error(exception.getMessage());
        }
        return resultURL;
    }

    public boolean insertUser(User user) {
        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO users (first_name, last_name, email_login, password, instagram, " +
                     "phone_number, role, is_blocked) VALUES (?,?,?,?,?,?,?,?)")) {
            setUserParameters(user, preparedStatement);
           return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUserById(int userId) {
        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setInt(1, userId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateUser(int userId, User user) {
        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement("UPDATE users SET first_name=?, last_name=?, " +
                     "email_login=?, password=?, instagram=?, phone_number=?, role=?, is_blocked=? WHERE id=?")){
            setUserParameters(user, preparedStatement);
            preparedStatement.setInt(9, userId);
                return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return false;
    }

    @Override
    public User getUserById(int userId) {
        Optional<User> optionalUser = Optional.empty();

        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users WHERE id = ?");) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (resultSet.next()) {
                optionalUser = Optional.ofNullable(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return optionalUser.orElse(User.newUserBuilder().build());
    }

    @Override
    public User getUserByLogin(String login) {
        Optional<User> optionalUser = Optional.empty();

        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users WHERE email_login = ?")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (resultSet.next()) {
                optionalUser = Optional.ofNullable(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return optionalUser.orElse(User.newUserBuilder().build());
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement(" SELECT * FROM users ")) {
            return getUserListExecute(users, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
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
