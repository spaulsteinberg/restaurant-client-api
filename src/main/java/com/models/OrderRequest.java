package com.models;

import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderRequest {
    @NotNull(message = "Credit card number cannot be null.")
    @NotBlank(message = "Credit card cannot be blank.")
    public String credit;
    @NotNull(message = "First name cannot be null.")
    @NotBlank(message = "First name cannot be blank.")
    public String firstName;
    @NotNull(message = "Last name cannot be null.")
    @NotBlank(message = "Last name cannot be blank.")
    public String lastName;
    public String email;
    @NotNull(message = "Order cost cannot be null.")
    @Min(value = 0, message = "Order value cannot be negative.")
    @Max(value = Integer.MAX_VALUE, message = "Invalid order value.")
    public double totalCost;
    @NotNull(message = "Order cannot be null.")
    public Order order;

    private FieldValue date;
    public void setDate(FieldValue val){
        this.date = FieldValue.serverTimestamp();
    }

    public OrderRequest(){}
}
