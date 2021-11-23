package com.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class PostOrderResponse {
    public int status;
    public String message;
    public String reference;
    public ZonedDateTime createdAt;

    public PostOrderResponse(){}

    public PostOrderResponse(int status, String message, String reference, ZonedDateTime createdAt){
        this.status = status;
        this.message = message;
        this.reference = reference;
        this.createdAt = createdAt;
    }
}
