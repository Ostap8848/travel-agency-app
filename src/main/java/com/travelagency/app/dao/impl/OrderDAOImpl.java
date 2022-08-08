package com.travelagency.app.dao.impl;

import com.travelagency.app.dao.OrderDAO;
import com.travelagency.app.dao.mapper.OrderMapper;
import com.travelagency.app.model.entity.Order;
import com.travelagency.app.model.entity.constant.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class implements OrderDAO interface and
 * implements all the methods needed to work with the database
 * Use singleton pattern
 */
public class OrderDAOImpl implements OrderDAO {
    static final Logger LOG = LogManager.getLogger(OrderDAOImpl.class);

    private static OrderDAOImpl instance;

    /**
     * Constructor is private
     */
    private OrderDAOImpl() {}

    public static synchronized OrderDAOImpl getInstance() {
        if (instance == null) {
            instance = new OrderDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean insertOrder(Connection connection, Order order) {
        try (//Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ConstantsQuery.INSERT_ORDER)) {
            setOrderParameters(order, preparedStatement);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error("Failed to insert order: ", e);
        }
        return false;
    }

    @Override
    public boolean deleteOrder(Connection connection, Order order) {
        try (//Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ConstantsQuery.DELETE_ORDER)) {
            preparedStatement.setInt(1, order.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error("Failed to delete order: ", e);
        }
        return false;
    }

    @Override
    public boolean updateOrder(Connection connection, Order order) {
        try (//Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ConstantsQuery.UPDATE_ORDER)) {
            setOrderParameters(order, preparedStatement);
            preparedStatement.setInt(4, order.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error("Failed to update order: ", e);
        }
        return false;
    }

    @Override
    public Order getOrderById(Connection connection, int orderId) {
        Optional<Order> optionalOrder = Optional.empty();

        try (//Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ConstantsQuery.GET_ORDER_BY_ID);) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            OrderMapper orderMapper = new OrderMapper();
            if (resultSet.next()) {
                optionalOrder = Optional.ofNullable(orderMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Failed to get order by id: ", e);
        }
        return optionalOrder.orElse(Order.newOrderBuilder().build());
    }

    @Override
    public List<Order> getOrdersByStatus(Connection connection, Status tourStatus) {
        List<Order> ordersByStatus = new ArrayList<>();

        try (//Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ConstantsQuery.GET_ORDERS_BY_TOUR_STATUS)) {
            preparedStatement.setString(1, String.valueOf(tourStatus));
            getOrderListExecute(ordersByStatus, preparedStatement);
        } catch (SQLException e) {
            LOG.error("Failed to get order by tour status: ", e);
        }
        return ordersByStatus;
    }

    @Override
    public List<Order> findAllOrders(Connection connection) {
        List<Order> orders = new ArrayList<>();

        try (//Connection con = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ConstantsQuery.FIND_ALL_ORDERS)) {
            return getOrderListExecute(orders, preparedStatement);
        } catch (SQLException e) {
            LOG.error("Failed to find all orders: ", e);
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
