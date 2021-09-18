package com.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostOrderResponse {
    private int status;
    private String message;
    private String reference;

    public PostOrderResponse(int status, String message, String reference){
        this.status = status;
        this.message = message;
        this.reference = reference;
    }
}
