package com.travelagency.app.web.service.impl;

import com.travelagency.app.dao.OrderDAO;
import com.travelagency.app.dao.impl.OrderDAOImpl;
import com.travelagency.app.model.entity.Order;
import com.travelagency.app.model.entity.constant.Status;
import com.travelagency.app.connection.DataSourceConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl {

    private OrderDAO orderDAO = OrderDAOImpl.getInstance();

    public OrderServiceImpl() {}

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public boolean insertOrder(Order order) {
        Connection con = connect();
        orderDAO.insertOrder(con, order);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteOrder(Order order) {
        Connection con = connect();
        orderDAO.deleteOrder(con, order);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean updateOrder(Order order) {
        Connection con = connect();
        orderDAO.updateOrder(con, order);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

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
