package com.example.razorpay.model.Razorpay;

import lombok.Data;

import java.util.List;

@Data
public class Event{
    public String entity;
    public String account_id;
    public String event;
    public List<String> contains;
    public Payload payload;
    public int created_at;
}