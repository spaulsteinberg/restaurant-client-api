package com.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostOrderErrorResponse {
    public int code;
    public String message;
    public String detail;

    public PostOrderErrorResponse(int code, String message, String detail){
        this.code = code;
        this.message = message;
        this.detail = detail;
    }
}
