package com.java.firebaseclient.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.ServerValue;
import com.models.OrderRequest;
import com.models.PostOrderResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@Service
public class OrderService {

    public PostOrderResponse sendOrder(OrderRequest orderRequest) throws ExecutionException, InterruptedException
    {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> future =
                db.collection(System.getenv("FS_ORDER_COLLECTION"))
                        .add(orderRequest);
        return new PostOrderResponse(201, "Order Created successfully.", future.get().getId());
    }
}
