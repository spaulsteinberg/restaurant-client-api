package com.java.controllers;


import com.java.services.OrderService;
import com.models.OrderRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/send/order")
    public String sendOrder(@RequestBody OrderRequest orderRequest) throws InterruptedException, ExecutionException {
        return orderService.sendOrder(orderRequest);
    }
}
