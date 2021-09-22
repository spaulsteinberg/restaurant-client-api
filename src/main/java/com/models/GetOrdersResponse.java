package com.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetOrdersResponse {

    public int status;
    public List<GetOrderServerResponse> data;

    public GetOrdersResponse(){}

    public GetOrdersResponse(int status){ this.status = status; this.data = new ArrayList<>(); }
}
