package com.example.razorpay.controller;

import com.example.razorpay.model.ProductOrder;
import com.example.razorpay.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonitorController {
    private final Logger log = LoggerFactory.getLogger(com.example.razorpay.RazorpayApplication.class);
    @Autowired
    OrderRepository repository;

    @GetMapping("/all-orders")
    public String allOrders(Model model) {
        Iterable<ProductOrder> orders = null;
        try{
            orders = repository.findAll();
            log.info(orders.toString());
        }catch (Exception e){
            log.error(e.toString());
        }

        model.addAttribute("orders",orders);
        return "allOrders";
    }

    @GetMapping("/clear")
    public String clearDB(Model model) {
        try{
            long count = repository.count();
            repository.deleteAll();
            log.info("Deleted %s",count);
            model.addAttribute("count",count);
        }catch (Exception e){
            log.error(e.toString());
        }
        return "clear";
    }
}
