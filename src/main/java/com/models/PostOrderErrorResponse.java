package com.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostOrderErrorResponse {
    public int status;
    public String message;
    public List<String> details;

    public PostOrderErrorResponse(){}

    public PostOrderErrorResponse(int code, String message){
        this.status = code;
        this.message = message;
        this.details = new ArrayList<>();
    }

    public PostOrderErrorResponse(int code, String message, List<String> details){
        this.status = code;
        this.message = message;
        this.details = details;
    }

}
