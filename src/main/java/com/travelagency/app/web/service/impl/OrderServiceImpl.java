package com.travelagency.app.web.service.impl;

import com.travelagency.app.dao.OrderDAO;
import com.travelagency.app.dao.exception.DBException;
import com.travelagency.app.dao.impl.OrderDAOImpl;
import com.travelagency.app.model.entity.Order;
import com.travelagency.app.model.entity.constant.Status;
import com.travelagency.app.web.service.OrderService;
import com.travelagency.app.web.service.exception.ServiceException;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO = OrderDAOImpl.getInstance();

    public OrderServiceImpl() {
    }

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public boolean insert(int userId, int tourId, Order order) throws ServiceException {
        try {
            return orderDAO.insertOrder(userId, tourId, order);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Order order) throws ServiceException {
        try {
            return orderDAO.deleteOrder(order);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Order order) throws ServiceException {
        try {
            return orderDAO.updateOrder(order);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getOrderId(int tourId) throws ServiceException {
        try {
            return orderDAO.getOrderId(tourId);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOrdersByStatus(Status tourStatus) throws ServiceException {
        try {
            return orderDAO.getOrdersByStatus(tourStatus);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        try {
            return orderDAO.findAllOrders();
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }
}
