package com.example.razorpay.model.Razorpay;

import lombok.Data;

import java.util.List;
/*
 Used https://json2csharp.com/json-to-pojo to generate this from Razorpay Sample Response
*/
@Data
public class Event{
    public String entity;
    public String account_id;
    public String event;
    public List<String> contains;
    public Payload payload;
    public int created_at;
}