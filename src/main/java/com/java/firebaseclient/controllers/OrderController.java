package com.java.firebaseclient.controllers;


import com.java.firebaseclient.services.OrderService;
import com.models.OrderRequest;
import com.models.OrderErrorResponse;
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
@SuppressWarnings("unused")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping(value = "/send/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendOrder(@Valid @RequestBody OrderRequest orderRequest, BindingResult result) throws InterruptedException, ExecutionException {
            if (result.hasErrors()){
                OrderErrorResponse response = new OrderErrorResponse(400, "Bad Request");
                for (FieldError error: result.getFieldErrors()){
                    response.details.add(error.getDefaultMessage());
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
           return ResponseEntity.status(HttpStatus.CREATED).body(orderService.sendOrder(orderRequest));
    }

    @GetMapping(value = "/get/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity retrieveOrders(@RequestHeader("credit") String credit, @RequestHeader("firstName") String firstName, @RequestHeader("lastName") String lastName) throws InterruptedException, ExecutionException {
        if (credit == null || credit.isBlank() || firstName == null || firstName.isBlank() || lastName == null || lastName.isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OrderErrorResponse(400, "Bad Request"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrders(firstName, lastName, credit));
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthcheck(){
        return ResponseEntity.ok("Application is running!");
    }
}
