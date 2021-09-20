package com.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FoodAddition {
    @NotNull(message = "Food addition item name cannot be null")
    public String item;
    @NotNull(message = "Food addition price cannot be null")
    @Min(value = 0, message = "Food addition price cannot be negative")
    public double price;

    public FoodAddition(){}

    public FoodAddition(String item, double price){
        this.item = item;
        this.price = price;
    }
}
