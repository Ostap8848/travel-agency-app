package com.travelagency.app.dao;

import com.travelagency.app.model.entity.Order;

import java.util.List;


public interface OrderDAO {
    boolean insertOrder(Order order);
    boolean deleteOrder(Order order);
    boolean updateOrder(Order order);
    Order getOrderById(int orderId);
    List<Order> getOrdersByStatus(Order.Status tourStatus);
    List<Order> getAllOrders();
}
