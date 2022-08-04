package com.travelagency.app.model.entity.constant;

public enum Role {
    GUEST("guest"),

    MANAGER("manager"),

    ADMINISTRATOR("administrator");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
