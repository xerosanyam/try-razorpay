package com.example.razorpay.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

/*
Razorpay mandatory fields for order creation -> {amount, currency}.
 */
@Data
public class OrderForm {

    @NotNull
    @Min(100)
    private int amount;
    private final String currency="INR";
}