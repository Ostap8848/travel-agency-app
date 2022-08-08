package com.travelagency.app.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * The class, that represents Connection Pool.
 */
public class DataSourceConnection {

    static final Logger LOG = LogManager.getLogger(DataSourceConnection.class);
    private static DataSourceConnection instance;

    /**
     * The constructor is private.
     */
    private DataSourceConnection() {
    }

    /**
     * Method used to get ConnectionPool instance.
     *
     * @return ConnectionPool instance
     */
    public static DataSourceConnection getInstance() {
        if (instance == null)
            instance = new DataSourceConnection();
        return instance;
    }
    /**
     * Method used to get connection from ConnectionPool.
     *
     * @return DB connection
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            DataSource ds = null;
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/TestDB");
            conn = ds.getConnection();

        } catch (SQLException | NamingException e) {
            LOG.error("Failed to create connection.");
        }
        return conn;
    }
}
