package com.example.razorpay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class RazorpayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RazorpayApplication.class, args);
    }

    @GetMapping("/")
    public String base(@RequestParam(value = "name", defaultValue = "World")String name){
        return String.format("Hello %s!",name);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World")String name){
        return String.format("Hello %s!",name);
    }

    private static final Logger log = LoggerFactory.getLogger(RazorpayApplication.class);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception{
        return  args -> {
            Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            log.info(quote.toString());
        };
    }
}
