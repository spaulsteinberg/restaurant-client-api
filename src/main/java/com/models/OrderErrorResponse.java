package com.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderErrorResponse {
    public int status;
    public String message;
    public List<String> details;

    public OrderErrorResponse(){}

    public OrderErrorResponse(int code, String message){
        this.status = code;
        this.message = message;
        this.details = new ArrayList<>();
    }

    public OrderErrorResponse(int code, String message, List<String> details){
        this.status = code;
        this.message = message;
        this.details = details;
    }

}
