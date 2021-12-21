package com.java.firebaseclient.services;

import com.exceptions.order.OrderDoesNotExistException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebaseclient.repository.OrderRepository;
import com.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResult sendOrder(OrderRequest request) throws ExecutionException, InterruptedException
    {
        return orderRepository.sendOrder(request);
    }

    public OrderServerResult getOrders(String reference) throws ExecutionException, InterruptedException {
        DocumentSnapshot document = orderRepository.getOrders(reference);
        if (document.exists()){
            return new OrderServerResult(document.getData());
        } else {
            throw new OrderDoesNotExistException(String.format("Order for reference %s does not exist.", reference));
        }
    }
}
