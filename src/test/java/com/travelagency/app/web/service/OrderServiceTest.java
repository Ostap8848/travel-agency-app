package com.travelagency.app.web.service;

import com.travelagency.app.dao.OrderDAO;
import com.travelagency.app.dao.exception.DBException;
import com.travelagency.app.model.entity.Order;
import com.travelagency.app.model.entity.constant.Status;
import com.travelagency.app.web.service.exception.ServiceException;
import com.travelagency.app.web.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.fail;

public class OrderServiceTest {

    OrderDAO orderDaoForTest = new OrderDAO() {
        @Override
        public boolean insertOrder(int userId, int tourId, Order order) throws DBException {
            return false;
        }

        @Override
        public boolean deleteOrder(Order order) throws DBException {
            return false;
        }

        @Override
        public boolean updateOrder(Order order) throws DBException {
            return false;
        }

        @Override
        public int getOrderId(int tourId) throws DBException {
            return 0;
        }

        @Override
        public Order getOrderById(int orderId) throws DBException {
            return null;
        }

        @Override
        public List<Order> getOrdersByStatus(Status tourStatus) throws DBException {
            return null;
        }

        @Override
        public List<Order> findAllOrders() throws DBException {
            return null;
        }
    };


    OrderService orderService = new OrderServiceImpl(orderDaoForTest);

    @Test
    public void delete() {
        try {
            orderService.delete(new Order());
        } catch (ServiceException e) {
            fail("Fail to delete Order");
        }
    }

    @Test
    public void update() {
        try {
            orderService.update(new Order());
        } catch (ServiceException e) {
            fail("Fail to update Order");
        }
    }
}
