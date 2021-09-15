package com.java.firebaseclient.services;

import com.models.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String sendOrder(OrderRequest orderRequest){
        return "sent";
    }
}
