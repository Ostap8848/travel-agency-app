package com.travelagency.app.dao;

import com.travelagency.app.dao.exception.DBException;
import com.travelagency.app.model.entity.Order;
import com.travelagency.app.model.entity.constant.Status;

import java.util.List;


public interface OrderDAO {
    boolean insertOrder(Order order) throws DBException;

    boolean deleteOrder(Order order) throws DBException;

    boolean updateOrder(Order order) throws DBException;

    Order getOrderById(int orderId) throws DBException;

    List<Order> getOrdersByStatus(Status tourStatus) throws DBException;

    List<Order> findAllOrders() throws DBException;
}
