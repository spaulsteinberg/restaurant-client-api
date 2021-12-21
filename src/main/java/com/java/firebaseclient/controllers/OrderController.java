package com.java.firebaseclient.controllers;


import com.exceptions.order.OrderNotCreatedException;
import com.java.firebaseclient.services.OrderService;
import com.models.OrderRequest;
import com.models.OrderErrorResponse;
import com.models.OrderResult;
import com.models.PostOrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/client")
@CrossOrigin
@SuppressWarnings("unused")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping(value = "/send/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendOrder(@Valid @RequestBody OrderRequest orderRequest) throws InterruptedException, ExecutionException {
        OrderResult result = orderService.sendOrder(orderRequest);
        if (result.getReference() == null || result.getReference().isBlank()){
            throw new OrderNotCreatedException("Order was not created.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new PostOrderResponse(201, "Order Created successfully.", result));
    }

    @GetMapping(value = "/get/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity retrieveOrders(@RequestHeader("credit") String credit, @RequestHeader("firstName") String firstName, @RequestHeader("lastName") String lastName) throws InterruptedException, ExecutionException {
        if (credit == null || credit.isBlank() || firstName == null || firstName.isBlank() || lastName == null || lastName.isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OrderErrorResponse(400, "Bad Request"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrders(firstName, lastName, credit));
    }
}
