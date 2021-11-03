package com.models;

import lombok.Data;

@Data
public class PostChargeToCardRequest {
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
}
