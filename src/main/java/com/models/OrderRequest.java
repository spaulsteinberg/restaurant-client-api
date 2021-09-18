package com.models;

import com.google.cloud.firestore.FieldValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    public String credit;
    private FieldValue date;
    public String firstName;
    public String lastName;
    public String email;
    public double totalCost;
    public Order order;

    public void setDate(FieldValue val){
        this.date = FieldValue.serverTimestamp();
    }

    public OrderRequest(){}
}
