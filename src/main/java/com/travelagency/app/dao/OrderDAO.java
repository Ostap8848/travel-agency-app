package com.travelagency.app.dao;

import com.travelagency.app.model.entity.Order;
import com.travelagency.app.model.entity.constant.Status;

import java.sql.Connection;
import java.util.List;


public interface OrderDAO {
    boolean insertOrder(Connection connection, Order order);
    boolean deleteOrder(Connection connection, Order order);
    boolean updateOrder(Connection connection, Order order);
    Order getOrderById(Connection connection, int orderId);
    List<Order> getOrdersByStatus(Connection connection, Status tourStatus);
    List<Order> findAllOrders(Connection connection);
}
