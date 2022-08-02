package com.travelagency.app.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Order {

    public enum Status {
        REGISTERED("registered"),

        PAID("paid"),

        CANCELED("canceled");

        private final String statusName;

        Status(String statusName) {
            this.statusName = statusName;
        }

        public String getStatusName() {
            return statusName;
        }
    }

    private int id;
    private BigDecimal price;
    private Status status;
    private String notes;

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

    public static OrderBuilder newOrderBuilder() {
        return new Order().new OrderBuilder();
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(price, order.price) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, status);
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
}
