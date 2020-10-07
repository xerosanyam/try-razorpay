package com.example.razorpay.model;

import lombok.Data;

import javax.persistence.*;

/*
 Our Model for product_order table
*/
@Data
@Entity
public class ProductOrder {
    @Id
    private String id;
    private int amount;

    @Column(columnDefinition = "boolean default false")
    private boolean completed;

    protected ProductOrder() {

    }

    public ProductOrder(String id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}
