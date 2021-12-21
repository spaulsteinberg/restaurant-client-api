package com.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetOrdersResponse {

    public int status;
    public OrderServerResult data;

    public GetOrdersResponse(){}

    public GetOrdersResponse(int status, OrderServerResult result){
        this.status = status;
        this.data = result;
    }
}
