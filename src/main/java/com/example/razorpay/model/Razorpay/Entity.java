package com.example.razorpay.model.Razorpay;

import lombok.Data;

import java.util.List;

@Data
public class Entity{
    public String id;
    public String entity;
    public int amount;
    public String currency;
    public int base_amount;
    public String status;
    public String order_id;
    public Object invoice_id;
    public boolean international;
    public String method;
    public int amount_refunded;
    public int amount_transferred;
    public Object refund_status;
    public boolean captured;
    public Object description;
    public Object card_id;
    public String bank;
    public Object wallet;
    public Object vpa;
    public String email;
    public String contact;
    public List<Object> notes;
    public int fee;
    public int tax;
    public Object error_code;
    public Object error_description;
    public Object error_source;
    public Object error_step;
    public Object error_reason;
    public int created_at;
}
