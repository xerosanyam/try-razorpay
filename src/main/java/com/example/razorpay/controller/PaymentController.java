package com.example.razorpay.controller;

import com.example.razorpay.model.ProductOrder;
import com.example.razorpay.repository.OrderRepository;
import com.example.razorpay.validation.OrderForm;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    OrderRepository repository;

    PaymentController() {
        try {
            razorpayClient = new RazorpayClient("rzp_test_cjiZ7kyueJCUGb", "fG0zEeXUiJKNbggEKRhsJf0k");
        } catch (RazorpayException e) {
            e.printStackTrace();
        }
    }

    /*
    Shows the index page; Starting point of the app
     */
    @GetMapping("/")
    public String showIndexPage(Model model) {
        OrderForm orderForm = new OrderForm();
        model.addAttribute("orderForm", orderForm);
        return "index";
    }

    /*
    Once user fills the index page form, the values are validated.
     */
    @PostMapping("/create-order")
    public String createOrder(@Valid OrderForm orderForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        log.info(orderForm.toString());

        JSONObject options = new JSONObject();
        options.put("amount", orderForm.getAmount());
        options.put("currency", orderForm.getCurrency());

        Order order;
        try {
            order = razorpayClient.Orders.create(options);
            log.info(order.toString());
        } catch (RazorpayException e) {
            e.printStackTrace();
            return String.format("Unable to create order!");
        }

        try {
            repository.save(new ProductOrder(order.get("id"), order.get("amount")));
            log.info("Written to DB!");
        } catch (Exception e) {
            log.error(e.toString());
        }

        model.addAttribute("amount", order.get("amount"));
        model.addAttribute("orderId", order.get("id"));
        return "payment";
    }

    @PostMapping("/success")
    public String success(@RequestParam MultiValueMap success, Model model) {
        model.addAttribute("result", success);
        return "success";
    }
}