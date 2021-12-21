package com.models;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class OrderResult {
    private String reference;
    private ZonedDateTime createdAt;

    public OrderResult(){}

    public OrderResult(String reference, ZonedDateTime createdAt) {
        this.reference = reference;
        this.createdAt = createdAt;
    }
}
