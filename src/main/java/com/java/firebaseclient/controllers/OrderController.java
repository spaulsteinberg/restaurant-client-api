package com.java.firebaseclient.controllers;


import com.java.firebaseclient.services.OrderService;
import com.models.OrderRequest;
import com.models.PostOrderErrorResponse;
import com.models.PostOrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/send/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendOrder(@RequestBody OrderRequest orderRequest) throws InterruptedException, ExecutionException{
            if (orderRequest.credit.equals("0")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PostOrderErrorResponse(400, "Bad Request", "jus bad"));
            }
           PostOrderResponse response = orderService.sendOrder(orderRequest);
           return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthcheck(){
        return ResponseEntity.ok("Application is running!");
    }
}
