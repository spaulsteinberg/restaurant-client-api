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
    public List<FoodAddition> additions;
    public List<String> subtractions;

    public FoodItem(){}

    public FoodItem(String category, String main, double price){
        this.category = category;
        this.main = main;
        this.price = price;
    }

    public FoodItem(String category, String main, double price, List<FoodAddition> additions, List<String> subtractions){
        this.category = category;
        this.main = main;
        this.price = price;
        this.additions = additions;
        this.subtractions = subtractions;
    }
}
