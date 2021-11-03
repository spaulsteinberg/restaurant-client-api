package com.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class FoodItem {
    @NotNull(message = "Item price cannot be null.")
    @Min(value = 0, message = "Price cannot be negative")
    public double price;
    public String category;
    @NotNull(message = "Item main cannot be null.")
    public String main;
    @NotNull(message = "Item quantity cannot be null")
    @Min(value = 1, message = "Item quantity cannot be less than 1")
    public int quantity;
    public List<FoodAddition> additions;
    public List<String> subtractions;

    public FoodItem(){}

    public FoodItem(String category, String main, double price, int quantity){
        this.category = category;
        this.main = main;
        this.price = price;
        this.quantity = quantity;
    }

    public FoodItem(String category, String main, double price, int quantity, List<FoodAddition> additions, List<String> subtractions){
        this.category = category;
        this.main = main;
        this.price = price;
        this.quantity = quantity;
        this.additions = additions;
        this.subtractions = subtractions;
    }
}
