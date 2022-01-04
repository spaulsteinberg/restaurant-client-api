package com.java.firebaseclient.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.models.OrderRequest;
import com.models.OrderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@Configuration
@PropertySource("classpath:environment.properties")
public class OrderRepositoryImpl implements OrderRepository {

    @Value("${firestore.collections.orders}")
    private String orders;

    @Override
    public OrderResult sendOrder(OrderRequest request) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(orders).document();
        ApiFuture<WriteResult> writeResult = docRef.set(request);
        return new OrderResult(docRef.getId(), writeResult.get().getUpdateTime().toDate().toInstant().atZone(ZoneId.of("America/Chicago")));
    }

    @Override
    public DocumentSnapshot getOrders(String reference) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(orders).document(reference);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        return future.get();
    }
}
