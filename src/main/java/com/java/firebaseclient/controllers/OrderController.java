package com.java.firebaseclient.controllers;


import com.java.firebaseclient.services.OrderService;
import com.models.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
@SuppressWarnings("unused")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/send/order")
    public String sendOrder(@RequestBody OrderRequest orderRequest) throws InterruptedException, ExecutionException {
        return orderService.sendOrder(orderRequest);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthcheck(){
        return ResponseEntity.ok("Application is running!");
    }
}
