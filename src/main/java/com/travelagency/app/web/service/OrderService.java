package com.travelagency.app.web.service;

import com.travelagency.app.model.entity.Order;
import com.travelagency.app.model.entity.constant.Status;

import java.util.List;

public interface OrderService {

    boolean insert(Order order);

    boolean delete(Order order);

    boolean update(Order order);

    Order getOrderById(int orderId);

    List<Order> getOrdersByStatus(Status tourStatus);

    List<Order> findAllOrders();
}
