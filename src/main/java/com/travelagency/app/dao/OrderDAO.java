package com.travelagency.app.dao;

import com.travelagency.app.model.entity.Order;

import java.util.List;


public interface OrderDAO {
    boolean insertOrder(Order order);
    boolean deleteOrder(int orderId);
    boolean updateOrder(int orderId, Order order);
    Order getOrderById(int orderId);
    List<Order> getOrdersByStatus(Order.Status tourStatus);
    List<Order> getAllOrders();
}
