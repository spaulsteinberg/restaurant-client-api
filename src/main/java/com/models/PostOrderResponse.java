package com.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostOrderResponse {
    public int status;
    public String message;
    public String reference;

    public PostOrderResponse(){}

    public PostOrderResponse(int status, String message, String reference){
        this.status = status;
        this.message = message;
        this.reference = reference;
    }
}
