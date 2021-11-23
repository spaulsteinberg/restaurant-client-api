package com.java.firebaseclient.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.models.*;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class OrderService {

    public PostOrderResponse sendOrder(OrderRequest orderRequest) throws ExecutionException, InterruptedException
    {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(System.getenv("FS_ORDER_COLLECTION")).document();
        ApiFuture<WriteResult> writeResult = docRef.set(orderRequest);
        return new PostOrderResponse(201, "Order Created successfully.", docRef.getId(), writeResult.get().getUpdateTime().toDate().toInstant().atZone(ZoneId.of("America/Chicago")));
    }

    public GetOrdersResponse getOrders(String firstName, String lastName, String credit) throws ExecutionException, InterruptedException{
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future =
                db.collection(System.getenv("FS_ORDER_COLLECTION"))
                        .whereEqualTo("credit", credit)
                        .whereEqualTo("firstName", firstName)
                        .whereEqualTo("lastName", lastName)
                        .get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        if (documents.size() == 0){
            return new GetOrdersResponse(404);
        }
        var response = new GetOrdersResponse(200);
        for (QueryDocumentSnapshot document: documents){
            response.data.add(document.toObject(GetOrderServerResponse.class));
        }
        return response;
    }
}
