package com.travelagency.app.web.service.impl;

import com.travelagency.app.dao.OrderDAO;
import com.travelagency.app.dao.impl.OrderDAOImpl;
import com.travelagency.app.model.entity.Order;
import com.travelagency.app.model.entity.constant.Status;
import com.travelagency.app.connection.DataSourceConnection;
import com.travelagency.app.web.service.OrderService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO = OrderDAOImpl.getInstance();

    public OrderServiceImpl() {}

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public boolean insert(Order order) {
        Connection con = connect();
        orderDAO.insertOrder(con, order);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(Order order) {
        Connection con = connect();
        orderDAO.deleteOrder(con, order);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Order order) {
        Connection con = connect();
        orderDAO.updateOrder(con, order);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Order getOrderById(int orderId) {
        Connection con = connect();
        Order order = orderDAO.getOrderById(con, orderId);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> getOrdersByStatus(Status tourStatus) {
        Connection con = connect();
        List<Order> orders = orderDAO.getOrdersByStatus(con, tourStatus);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> findAllOrders() {
        Connection con = connect();
        List<Order> orders = orderDAO.findAllOrders(con);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Connection connect() {
        return  DataSourceConnection.getInstance().getConnection();
    }
}
