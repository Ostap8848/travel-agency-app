package com.travelagency.app.dao.impl;

import com.travelagency.app.dao.OrderDAO;
import com.travelagency.app.dao.mapper.OrderMapper;
import com.travelagency.app.model.entity.Order;
import com.travelagency.app.util.DataBaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OrderDAOImpl implements OrderDAO {
    static final Logger LOG = LogManager.getLogger(OrderDAOImpl.class);

    private static final String FULL_URL = "jdbc:mysql://localhost:3306/travel-agency-db?user=root&password=admin";
    private static OrderDAOImpl instance;

    public static synchronized OrderDAOImpl getInstance() {
        if (instance == null) {
            instance = new OrderDAOImpl();
        }
        return instance;
    }
    @Override
    public boolean insertOrder(Order order) {
        //DataBaseConnection.getConnection();
        try /*(//Connection con = DriverManager.getConnection(FULL_URL);
             //DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO orders (price, status, notes) VALUES (?,?,?)"))*/ {
            PreparedStatement preparedStatement = DataBaseConnection.getConnection().prepareStatement("INSERT INTO orders (price, status, notes) VALUES (?,?,?)");
            setOrderParameters(order, preparedStatement);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM orders WHERE id = ?")) {
            preparedStatement.setInt(1, orderId);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateOrder(int orderId, Order order) {
        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement("UPDATE orders SET price=?, status=?, notes=?" +
                     " WHERE id=?")){
            setOrderParameters(order, preparedStatement);
            preparedStatement.setInt(4, orderId);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return false;
    }

    @Override
    public Order getOrderById(int orderId) {
        Optional<Order> optionalOrder = Optional.empty();

        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM orders WHERE id = ?");) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            OrderMapper orderMapper = new OrderMapper();
            if (resultSet.next()) {
                optionalOrder = Optional.ofNullable(orderMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return optionalOrder.orElse(Order.newOrderBuilder().build());
    }

    @Override
    public List<Order> getOrdersByStatus(Order.Status tourStatus) {
        List<Order> ordersByStatus = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(FULL_URL);
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM orders WHERE status = ? ")){
            preparedStatement.setString(1, String.valueOf(tourStatus));
            getOrderListExecute(ordersByStatus, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return ordersByStatus;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(FULL_URL);
             PreparedStatement preparedStatement = con.prepareStatement(" SELECT * FROM orders")) {
            return getOrderListExecute(orders, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return orders;
    }

    private void setOrderParameters(Order order, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setBigDecimal(1, order.getPrice());
        preparedStatement.setString(2, order.getStatus().getStatusName());
        preparedStatement.setString(3, order.getNotes());
    }

    private List<Order> getOrderListExecute(List<Order> orders, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet1 = preparedStatement.executeQuery();

        OrderMapper orderMapper = new OrderMapper();

        while (resultSet1.next()) {
            Optional<Order> order = Optional.
                    ofNullable(orderMapper.extractFromResultSet(resultSet1));
            order.ifPresent(orders::add);
        }
        return orders;
    }
}
