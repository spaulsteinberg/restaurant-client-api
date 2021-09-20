package com.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BeverageItem {
    public String category;
    @NotNull(message = "Beverage item cannot be null.")
    public String item;
    @NotNull(message = "Price cannot be null.")
    @Min(value = 0, message = "Price cannot be negative")
    public double price;

    public BeverageItem(){}

    public BeverageItem(String category, String item, double price){
        this.category = category;
        this.item = item;
        this.price = price;
    }
}
