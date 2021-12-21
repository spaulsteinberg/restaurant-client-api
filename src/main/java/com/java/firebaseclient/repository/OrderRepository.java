package com.java.firebaseclient.repository;

import com.google.cloud.firestore.DocumentSnapshot;
import com.models.OrderRequest;
import com.models.OrderResult;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
public interface OrderRepository {
    DocumentSnapshot getOrders(String reference) throws ExecutionException, InterruptedException;
    OrderResult sendOrder(OrderRequest request) throws ExecutionException, InterruptedException;
}
