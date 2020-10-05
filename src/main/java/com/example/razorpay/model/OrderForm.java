package com.example.razorpay.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderForm {

    @NotNull
    @Min(100)
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderForm{" +
                "amount=" + amount +
                '}';
    }
}