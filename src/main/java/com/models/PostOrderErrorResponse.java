package com.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostOrderErrorResponse {
    public int code;
    public String message;
    public List<String> details;

    public PostOrderErrorResponse(int code, String message){
        this.code = code;
        this.message = message;
        this.details = new ArrayList<>();
    }

    public PostOrderErrorResponse(int code, String message, List<String> details){
        this.code = code;
        this.message = message;
        this.details = details;
    }
}
