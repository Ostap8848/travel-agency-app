package com.travelagency.app.web.service;

import com.travelagency.app.dao.UserDAO;
import com.travelagency.app.dao.exception.DBException;
import com.travelagency.app.model.entity.User;
import com.travelagency.app.web.service.exception.ServiceException;
import com.travelagency.app.web.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserDAO userDaoForTest = new UserDAO() {
        @Override
        public boolean insertUser(User user) throws DBException {
            return false;
        }

        @Override
        public boolean deleteUser(User user) throws DBException {
            return false;
        }

        @Override
        public boolean updateUser(User user) throws DBException {
            return false;
        }

        @Override
        public User getUserById(int userId) throws DBException {
            return null;
        }

        @Override
        public User getUserByLogin(String login) throws DBException {
            return null;
        }

        @Override
        public List<User> findAllUsers(int offset) throws DBException {
            return null;
        }

        @Override
        public int getNumberOfRecords() {
            return 0;
        }
    };

    UserService userService = new UserServiceImpl(userDaoForTest);

    @Test
    public void insert() {
        try {
            userService.insert(new User());
        } catch (ServiceException e) {
            fail("Fail to insert User");
        }
    }

    @Test
    public void delete() {
        try {
            userService.delete(new User());
        } catch (ServiceException e) {
            fail("Fail to delete User");
        }
    }

    @Test
    public void update() {
        try {
            userService.update(new User());
        } catch (ServiceException e) {
            fail("Fail to update User");
        }
    }

    @Test
    public void getUserById() {
        try {
            assertNull(userService.getUserById(new User().getId()));
            assertNull(userService.getUserById(5));
            assertNull(userService.getUserById(Integer.parseInt("8")));
        } catch (ServiceException e) {
            fail("Fail to get User by id");
        }
    }

    @Test
    public void getUserByLogin() {
        try {
            assertNull(userService.getUserByLogin(new User().getLogin()));
            assertNull(userService.getUserByLogin("someLogin@gmail.com"));
        } catch (ServiceException e) {
            fail("Fail to get User by login");
        }
    }

    @Test
    public void findAllUsers() {
        try {
            assertNull(userService.findAllUsers(0));
            assertNull(userService.findAllUsers(10));
            assertNull(userService.findAllUsers(20));
            assertNull(userService.findAllUsers(8));
        } catch (ServiceException e) {
            fail("Fail to find all Users");
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void invalidInputTest() {
        try {
            userService.insert(null);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
