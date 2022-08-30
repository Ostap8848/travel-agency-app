package com.travelagency.app.model.entity;

import com.travelagency.app.model.entity.constant.Status;

import java.math.BigDecimal;

public class Order {

    private int id;
    private BigDecimal price;
    private Status status;
    private String notes;

    public static OrderBuilder newOrderBuilder() {
        return new Order().new OrderBuilder();
    }

    public int getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Status getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public class OrderBuilder {
        public OrderBuilder setId(int id) {
            Order.this.id = id;
            return this;
        }

        public OrderBuilder setPrice(BigDecimal price) {
            Order.this.price = price;
            return this;
        }

        public OrderBuilder setStatus(Status status) {
            Order.this.status = status;
            return this;
        }

        public OrderBuilder setNotes(String notes) {
            Order.this.notes = notes;
            return this;
        }

        public Order build() {
            return Order.this;
        }
    }
}
