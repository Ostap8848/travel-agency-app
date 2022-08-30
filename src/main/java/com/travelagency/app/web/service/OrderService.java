package com.travelagency.app.web.service;

import com.travelagency.app.model.entity.Order;
import com.travelagency.app.model.entity.constant.Status;
import com.travelagency.app.web.service.exception.ServiceException;

import java.util.List;

public interface OrderService {

    boolean insert(int userId, int tourId, Order order) throws ServiceException;

    boolean delete(Order order) throws ServiceException;

    boolean update(Order order) throws ServiceException;

    int getOrderId(int tourId) throws ServiceException;

    List<Order> getOrdersByStatus(Status tourStatus) throws ServiceException;

    List<Order> findAllOrders() throws ServiceException;
}
