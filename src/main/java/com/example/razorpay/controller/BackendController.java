package com.example.razorpay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController {
    private final Logger log = LoggerFactory.getLogger(com.example.razorpay.RazorpayApplication.class);

    /*
     Webhook to receive event from Razorpay
    */
    @PostMapping("/webhook")
    public String webhook(@RequestParam MultiValueMap event) {
        log.info(event.toString());
        return "Thanks!";
    }
}
