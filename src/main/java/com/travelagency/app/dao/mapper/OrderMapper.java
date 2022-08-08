package com.travelagency.app.dao.mapper;

import com.travelagency.app.model.entity.Order;
import com.travelagency.app.model.entity.constant.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderMapper implements ObjectMapper<Order> {
    @Override
    public Order extractFromResultSet(ResultSet resultSet) throws SQLException {
        Map<Integer, Order> orders = new HashMap<>();
        Order order = Order.newOrderBuilder()
                .setId(resultSet.getInt("id"))
                .setPrice(resultSet.getBigDecimal("price"))
                .setStatus(Status.valueOf(resultSet.getString("status")))
                .setNotes(resultSet.getString("notes"))
                .build();
        orders.put(order.getId(), order);
        order = this.makeUnique(orders, order);
        return order;
    }

    @Override
    public Order makeUnique(Map<Integer, Order> cache, Order order) {
        cache.putIfAbsent(order.getId(), order);
        return cache.get(order.getId());
    }
}
