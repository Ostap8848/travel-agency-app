package com.travelagency.app.model;

import com.travelagency.app.model.entity.Order;
import com.travelagency.app.model.entity.constant.Status;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderTest {

    Order order;

    @Test
    public void orderBuilderTest() {
        order = Order.newOrderBuilder()
                .setId(5)
                .setPrice(BigDecimal.valueOf(50.50))
                .setStatus(Status.CANCELED)
                .setNotes("Some order notes")
                .build();
        assertNotNull(order);
        assertEquals(5, order.getId());
        assertEquals(BigDecimal.valueOf(50.50), order.getPrice());
        assertEquals(Status.CANCELED, order.getStatus());
        assertEquals("Some order notes", order.getNotes());
    }
}
