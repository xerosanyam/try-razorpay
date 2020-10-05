package com.example.razorpay.controller;

import com.example.razorpay.model.OrderForm;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class PaymentController {
    private final Logger log = LoggerFactory.getLogger(com.example.razorpay.RazorpayApplication.class);
    RazorpayClient razorpayClient;

    PaymentController() {
        try {
            razorpayClient = new RazorpayClient("rzp_test_cjiZ7kyueJCUGb", "fG0zEeXUiJKNbggEKRhsJf0k");
        } catch (RazorpayException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public String showIndexPage(Model model) {
        OrderForm orderForm = new OrderForm();
        model.addAttribute("orderForm", orderForm);
        return "index";
    }


    @PostMapping("/create-order")
    public String createOrder(@Valid OrderForm orderForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        log.info(orderForm.toString());

        int amount = orderForm.getAmount();
        JSONObject options = new JSONObject();
        options.put("amount", amount);
        options.put("currency", "INR"); // mandatory field in Razorpay API

        Order order;
        try {
            order = razorpayClient.Orders.create(options);
            log.info(order.toString());
        } catch (RazorpayException e) {
            e.printStackTrace();
            return String.format("Unable to create order!");
        }
        model.addAttribute("amount", amount);
        model.addAttribute("orderId", order.get("id"));
        return "payment";
    }

    @PostMapping("/success")
    public String success(@RequestParam MultiValueMap success, Model model) {
        model.addAttribute("result", success);
        return "success";
    }

    @PostMapping("/error")
    public String error(@RequestParam MultiValueMap error, Model model) {
        model.addAttribute("result", error);
        return "success";
    }
}