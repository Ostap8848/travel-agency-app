package com.travelagency.app.model.entity;

import com.travelagency.app.model.entity.constant.Role;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String instagram;
    private String phoneNumber;
    private Role role;
    private boolean isBlocked;

    public static UserBuilder newUserBuilder() {
        return new User().new UserBuilder();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", instagram='" + instagram + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", isBlocked=" + isBlocked +
                '}';
    }

    public class UserBuilder {
        private UserBuilder() {
        }

        public UserBuilder setId(int id) {
            User.this.id = id;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            User.this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            User.this.lastName = lastName;
            return this;
        }

        public UserBuilder setLogin(String login) {
            User.this.login = login;
            return this;
        }

        public UserBuilder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public UserBuilder setInstagram(String instagram) {
            User.this.instagram = instagram;
            return this;
        }

        public UserBuilder setPhoneNumber(String phoneNumber) {
            User.this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder setRole(Role role) {
            User.this.role = role;
            return this;
        }

        public UserBuilder setIsBlocked(boolean isBlocked) {
            User.this.isBlocked = isBlocked;
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
