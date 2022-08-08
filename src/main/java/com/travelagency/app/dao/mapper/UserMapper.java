package com.travelagency.app.dao.mapper;

import com.travelagency.app.model.entity.User;
import com.travelagency.app.model.entity.constant.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

    public class UserMapper implements ObjectMapper<User> {

        @Override
        public User extractFromResultSet(ResultSet resultSet) throws SQLException {
            Map<Integer, User> users = new HashMap<>();
            User user = User.newUserBuilder().
                    setId(resultSet.getInt("id"))
                    .setFirstName(resultSet.getString("first_name"))
                    .setLastName(resultSet.getString("last_name"))
                    .setLogin(resultSet.getString("email_login"))
                    .setPassword(resultSet.getString("password"))
                    .setInstagram(resultSet.getString("instagram"))
                    .setPhoneNumber(resultSet.getString("phone_number"))
                    .setRole(Role.valueOf(resultSet.getString("role")))
                    .setIsBlocked(resultSet.getBoolean("is_blocked"))
                    .build();
            users.put(user.getId(), user);
            user = this.makeUnique(users, user);
            return user;
        }

        @Override
        public User makeUnique(Map<Integer, User> cache, User user) {
            cache.putIfAbsent(user.getId(), user);
            return cache.get(user.getId());
        }
    }
