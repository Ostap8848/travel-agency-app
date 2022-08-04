package com.travelagency.app.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DataBaseConnection {
    private static final String CONNECTION_URL = getUrl(PropertyPath.MY_DB_PATH);
    private static DataBaseConnection instance;

    private Connection connection;

    private DataBaseConnection() {
    }

    public static DataBaseConnection getInstance() {
        if (instance == null)
            instance = new DataBaseConnection();
        return instance;
    }

    private static String getUrl(String path) {
        String resultUrl = "";
        try (InputStream fis = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(fis);
            resultUrl = properties.getProperty("connection.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultUrl;
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(CONNECTION_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
