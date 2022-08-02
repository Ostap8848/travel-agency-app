package com.travelagency.app.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

    public static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel-agency-db?user=root&password=admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
