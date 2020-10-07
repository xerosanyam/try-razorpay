package com.example.razorpay.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController {
    private final Logger log = LoggerFactory.getLogger(com.example.razorpay.RazorpayApplication.class);

    /*
     Webhook to receive event from Razorpay
    */
    @PostMapping("/webhook")
    public String webhook(@RequestBody JSONObject event) {
        log.info(event.toString());
        return "Thanks!";
    }
}
