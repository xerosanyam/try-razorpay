package com.example.razorpay;

import com.example.razorpay.controller.BackendController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private BackendController controller;

    @Test
    public void contextLoads() throws Exception{
        Assert.assertNotNull(controller);
    }
}
