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
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public OrderResult sendOrder(OrderRequest request) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(System.getenv("FS_ORDER_COLLECTION")).document();
        ApiFuture<WriteResult> writeResult = docRef.set(request);
        return new OrderResult(docRef.getId(), writeResult.get().getUpdateTime().toDate().toInstant().atZone(ZoneId.of("America/Chicago")));
    }

    @Override
    public DocumentSnapshot getOrders(String reference) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(System.getenv("FS_ORDER_COLLECTION")).document(reference);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        return future.get();
    }
}
