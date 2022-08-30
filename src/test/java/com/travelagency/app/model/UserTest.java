package com.travelagency.app.model;

import com.travelagency.app.model.entity.User;
import com.travelagency.app.model.entity.constant.Role;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    User user;
    @Test
    public void userBuilderTest() {
        user = User.newUserBuilder()
                .setId(1)
                .setFirstName("John")
                .setLastName("Doe")
                .setLogin("Login")
                .setPassword("password")
                .setInstagram("instLink")
                .setPhoneNumber("+380963333333")
                .setRole(Role.GUEST)
                .setIsBlocked(false)
                .build();
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("Login", user.getLogin());
        assertEquals("password", user.getPassword());
        assertEquals("instLink", user.getInstagram());
        assertEquals("+380963333333", user.getPhoneNumber());
        assertEquals(Role.GUEST, user.getRole());
        assertFalse(user.getIsBlocked());
    }
}
