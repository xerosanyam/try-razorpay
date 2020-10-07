package com.example.razorpay.controller;

import com.example.razorpay.model.ProductOrder;
import com.example.razorpay.model.Razorpay.Entity;
import com.example.razorpay.model.Razorpay.Event;
import com.example.razorpay.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController {
    private final Logger log = LoggerFactory.getLogger(com.example.razorpay.RazorpayApplication.class);
    @Autowired
    OrderRepository repository;

    /*
     Webhook to receive event from Razorpay
    */
    @PostMapping("/webhook")
    public ResponseEntity webhook(@RequestBody Event event) {
        log.info(event.toString());
        Entity entity = event.getPayload().getPayment().getEntity();
        if (event.entity.equals("event") && entity.captured) {
            try {
            ProductOrder order = repository.findById(entity.order_id);
            // Validate that amount is same.
            if(order.getAmount()==entity.amount){
                order.setCompleted(true);
                repository.save(order);
            }
            }catch (Exception e){
                log.error(e.toString());
            }
        }
        return ResponseEntity.ok("Thanks!");
    }

    @GetMapping("/all-orders.json")
    public Iterable<ProductOrder> allOrders(Model model) {
        Iterable<ProductOrder> orders = null;
        try {
            orders = repository.findAll();
            log.info(orders.toString());
        } catch (Exception e) {
            log.error(e.toString());
        }

        return orders;
    }
}
